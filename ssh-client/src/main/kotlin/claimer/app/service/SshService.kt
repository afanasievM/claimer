package claimer.app.service

import com.sshtools.client.SshClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SshService {

    @Value("\${ssh.host}")
    lateinit var host: String

    @Value("\${ssh.port}")
    lateinit var port: String

    @Value("\${ssh.username}")
    lateinit var username: String

    @Value("\${ssh.password}")
    lateinit var password: String

    fun sendCommand(command: String): Mono<Unit> {
        return Mono.just(SshClient(host, port.toInt(), username, password.toCharArray()))
            .flatMap { Mono.just(it.executeCommandWithResult(command)) }
            .doOnNext { LOG.info("Command executed:\n command=$command \nresult=$it") }
            .doOnError { LOG.error("Ssh command= $command failed \n ${it.message}") }
            .thenReturn(Unit)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
