package claimer.app.service

import claimer.app.dto.TurnstileTask
import claimer.app.dto.TurnstileTaskProxylessCreateTaskRequest
import claimer.app.dto.TurnstileTaskProxylessCreateTaskResponse
import claimer.app.dto.TwoCapchGetTaskResultRequest
import claimer.app.dto.TwoCapchGetTaskResultResponse
import claimer.app.dto.TwoCapchGetTaskResultResponse.TurnstileTaskSolution
import claimer.app.enums.CapchaTaskType
import claimer.app.repository.TwoCapchaCredentialsRepository
import jakarta.annotation.PostConstruct
import java.time.Duration
import java.util.concurrent.TimeoutException
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class CapchaService(private val webClient: WebClient, private val repository: TwoCapchaCredentialsRepository) {

    private lateinit var apiKey: String

    fun solveCloudflareTurnstile(url: String, key: String): Mono<TurnstileTaskSolution> {
        var taskId = 0L
        return createTask(buildTurnstileTaskRequest(url, key, apiKey))
            .doOnNext { taskId = it.taskId }
            .handle { response, sink ->
                if (response.errorId > 0) {
                    sink.error(IllegalArgumentException("create task responce with error\nresponse=$response"))
                } else {
                    sink.next(response)
                }
            }
            .delayElement(Duration.ofSeconds(RESOLVING_TASK_TIMEOUT))

            .flatMap { getTaskResult(TwoCapchGetTaskResultRequest(apiKey, taskId)) }
            .publishOn(Schedulers.boundedElastic())
            .handle { response, sink ->
                println(response)
                if (response.solution != null) {
                    sink.next(response)
                } else {
                    LOG.info("getTaskResult second try")
                    Thread.sleep(Duration.ofSeconds(RESOLVING_TASK_TIMEOUT))
                    sink.next(getTaskResult(TwoCapchGetTaskResultRequest(apiKey, taskId)).block())
                }
            }
            .handle { response, sink ->
                if (response.solution != null) {
                    sink.next(response)
                } else {
                    sink.error(TimeoutException("getTaskResult second try failed"))
                }
            }
            .mapNotNull { it.solution }
    }

    private fun buildTurnstileTaskRequest(url: String, key: String, apiKey: String) =
        TurnstileTaskProxylessCreateTaskRequest(
            clientKey = apiKey,
            task = TurnstileTask(
                type = CapchaTaskType.TURNSTILE_TASK_TYPE.type,
                websiteURL = url,
                websiteKey = key
            )
        )

    private fun createTask(request: TurnstileTaskProxylessCreateTaskRequest): Mono<TurnstileTaskProxylessCreateTaskResponse> {
        return webClient.post()
            .uri(CREATE_TASK_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .bodyToMono<TurnstileTaskProxylessCreateTaskResponse>()
    }

    private fun getTaskResult(request: TwoCapchGetTaskResultRequest) =
        webClient.post()
            .uri(GET_RESULT_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .bodyToMono<TwoCapchGetTaskResultResponse>()

    @PostConstruct
    private fun getTwoCapchaApiKey() {
        apiKey = repository.findAll().next().block().apiKey
    }

    companion object {
        private const val CREATE_TASK_URL = "https://api.2captcha.com/createTask"
        private const val GET_RESULT_URL = "https://api.2captcha.com/getTaskResult"
        private const val RESOLVING_TASK_TIMEOUT = 120L
        private val LOG = LoggerFactory.getLogger(Companion::class.java)

    }
}
