package claimer.app.job

import claimer.app.service.PenumbraClaimer
import claimer.app.service.PenumbraSshService
import java.time.Duration
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class Runner(private val claimer: PenumbraClaimer, private val sshService: PenumbraSshService) {

    @Scheduled(fixedRate = 1000000, initialDelay = 0)
    fun run() {
        LOG.info("Started Penumbra job")
        claimer.claim()
            .delayElement(Duration.ofSeconds(120))
            .flatMap { sshService.runSshCommand() }
            .subscribe { LOG.info("Job successfully finished!") }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
