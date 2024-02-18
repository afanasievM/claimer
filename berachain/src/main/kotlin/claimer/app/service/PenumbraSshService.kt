//package claimer.app.service
//
//import claimer.app.entity.Berachain
//import org.springframework.stereotype.Service
//import reactor.core.publisher.Mono
//
//@Service
//class PenumbraSshService(private val sshService: SshService) {
//
//    fun runSshCommand(berachain: Berachain): Mono<Unit> {
//        return sshService.sendCommand(berachain.sshCommand)
//    }
//}
