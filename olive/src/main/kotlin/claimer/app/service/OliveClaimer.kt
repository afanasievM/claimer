package claimer.app.service

import claimer.app.entity.Olive
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class OliveClaimer(private val discordService: DiscordService) {

    fun claim(olive: Olive): Mono<Olive> {
        return discordService
            .sendMessageToChannel(olive.discordChannelId, olive.discordUserToken, olive.referral)
            .thenReturn(olive)
    }
}
