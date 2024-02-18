//package claimer.app.service
//
//import claimer.app.entity.Berachain
//
//import org.springframework.stereotype.Service
//import reactor.core.publisher.Mono
//
//
//@Service
//class PenumbraClaimer(private val discordService: DiscordService) {
//
//    fun claim(berachain: Berachain): Mono<Berachain> {
//        return discordService
//            .sendMessageToChannel(berachain.discordChannelId, berachain.discordUserToken, berachain.wallet)
//            .thenReturn(berachain)
//    }
//}
