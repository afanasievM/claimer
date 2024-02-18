package claimer.app.service

import claimer.app.repository.SshCredentialsRepository
import com.sshtools.client.SshClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SshService(private val sshCredentialsRepository: SshCredentialsRepository) {

    fun sendCommand(command: String): Mono<Unit> {
        return sshCredentialsRepository.findAll()
            .next()
            .doOnNext { LOG.info("Credentials= $it") }
            .map { SshClient(it.host, it.port.toInt(), it.username, it.password.toCharArray()) }
            .flatMap { Mono.just(it.executeCommandWithResult(command)) }
            .doOnNext { LOG.info("Command executed:\n command=$command \nresult=$it") }
            .doOnError { LOG.error("Ssh command= $command failed \n ${it.message}") }
            .thenReturn(Unit)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
