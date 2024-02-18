package claimer.app.service

import claimer.app.entity.Penumbra
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PenumbraSshService(private val sshService: SshService) {

    fun runSshCommand(penumbra: Penumbra): Mono<Unit> {
        return sshService.sendCommand(penumbra.sshCommand)
    }
}
