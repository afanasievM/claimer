package claimer.app.service

import claimer.app.dto.SendMessageRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono


@Service
class DiscordService(private val webClient: WebClient) {


    fun sendMessageToChannel(channelId: String, token: String, message: String): Mono<String> {
        return webClient.post()
            .uri(String.format(DISCORD_SEND_MESSAGE_URL, channelId))
            .headers { it.add(AUTHORIZATION_HEADER_KEY, token) }
            .body(BodyInserters.fromValue(SendMessageRequest(message)))
            .retrieve()
            .bodyToMono<String>()
            .doOnError { LOG.error("Error during sending message to discord channel id=$channelId \n${it.message}") }
    }

    companion object {
        private const val AUTHORIZATION_HEADER_KEY = "Authorization"
        private const val DISCORD_SEND_MESSAGE_URL = "https://discord.com/api/v9/channels/%S/messages"
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
