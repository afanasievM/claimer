package claimer.app.service

import org.springframework.beans.factory.annotation.Value

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class PenumbraClaimer(private val discordService: DiscordService): Claimer {

    @Value("\${penumbra.wallet.address}")
    private lateinit var walletAddress: String

    @Value("\${penumbra.discord.channel.id}")
    private lateinit var channelId: String

    override fun claim(): Mono<Unit> {
        return discordService.sendMessageToChannel(channelId, walletAddress).thenReturn(Unit)
    }
}
