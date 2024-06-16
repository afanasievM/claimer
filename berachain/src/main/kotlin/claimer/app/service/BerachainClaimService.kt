package claimer.app.service

import claimer.app.dto.BerachainClaimRequest
import claimer.app.dto.TwoCapchGetTaskResultResponse.TurnstileTaskSolution
import claimer.app.entity.Berachain
import org.springframework.http.MediaType

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class BerachainClaimService(private val capchaService: CapchaService, private val webClient: WebClient) {

    fun claim(berachain: Berachain): Mono<Berachain> {
        return capchaService.solveCloudflareTurnstile(CAPCHA_URL, SITE_KEY)
            .map { buildHeaders(it) }
            .flatMap { claim(it, BerachainClaimRequest(berachain.wallet)) }
            .thenReturn(berachain)
    }

    private fun buildHeaders(solution: TurnstileTaskSolution) = mapOf(
        "accept" to "*/*",
        "accept-language" to "en-GB,en-US;q=0.9,en;q=0.8",
        "accept-Encoding" to "gzip, deflate, br",
        "connection" to "keep-alive",
        "authorization" to "Bearer ${solution.token}",
        "user-agent" to solution.userAgent
    )

    private fun claim(headers: Map<String, String>, request: BerachainClaimRequest) = webClient.post()
        .uri(CLAIMING_URL, request)
        .contentType(MediaType.APPLICATION_JSON)
        .headers { headers.forEach { (k, v) -> it.add(k, v) } }
        .bodyValue(request)
        .retrieve()
        .bodyToMono<String>()

    companion object {
        private const val CLAIMING_URL = "https://bartio-faucet.berachain-devnet.com/api/claim"
        private const val CAPCHA_URL = "https://bartio.faucet.berachain.com/"
        private const val SITE_KEY = "0x4AAAAAAARdAuciFArKhVwt"

    }
}
