package claimer.app.service

import claimer.app.entity.Shardeum
import claimer.app.telegram.TelegramClient

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


//@Service
class ShardeumClaimer(private val telegramClient: TelegramClient) {

    fun claim(shardeum: Shardeum): Mono<Unit> {
        return telegramClient
            .sendMessageToChat(CLAIM_COMMAND + shardeum.wallet, shardeum.telegramChannelId.toLong())
            .thenReturn(Unit)
    }

    companion object {
        private const val CLAIM_COMMAND = "/claim "
    }
}
