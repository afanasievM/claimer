package claimer.app.service

import claimer.app.entity.Penumbra

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class PenumbraClaimer(private val discordService: DiscordService) {

    fun claim(penumbra: Penumbra): Mono<Penumbra> {
        return discordService
            .sendMessageToChannel(penumbra.discordChannelId, penumbra.discordUserToken, penumbra.wallet)
            .thenReturn(penumbra)
    }
}
