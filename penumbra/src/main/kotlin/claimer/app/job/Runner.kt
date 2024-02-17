package claimer.app.job

import claimer.app.service.PenumbraClaimer
import claimer.app.service.PenumbraSshService
import java.time.Duration
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Runner(private val claimer: PenumbraClaimer, private val sshService: PenumbraSshService) {

    @Scheduled(cron = "CRON_EXPRESSION")
    fun run() {
        LOG.info("Started Penumbra job")
        claimer.claim()
            .delayElement(Duration.ofSeconds(120))
            .flatMap { sshService.runSshCommand() }
            .subscribe { LOG.info("Job successfully finished!") }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
        private val CRON_EXPRESSION = "0 0 21 * * *"
    }
}
