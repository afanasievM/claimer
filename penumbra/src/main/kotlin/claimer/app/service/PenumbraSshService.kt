package claimer.app.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PenumbraSshService(private val sshService: SshService) {
    @Value("\${penumbra.ssh.command}")
    lateinit var command: String

    fun runSshCommand(): Mono<Unit> {

        return sshService.sendCommand(command)
    }
}
