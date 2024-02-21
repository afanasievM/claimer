package claimer.app.job

import claimer.app.service.PenumbraClaimer
import claimer.app.service.PenumbraMongoService
import claimer.app.service.PenumbraSshService
import java.time.Duration
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PenumbraRunner(private val claimer: PenumbraClaimer,
                     private val sshService: PenumbraSshService,
                     private val mongoService: PenumbraMongoService
) {

    @Scheduled(fixedDelay = HOURS_DELAY_MS, initialDelay = START_UP_DELAY_MS)
    fun run() {
        LOG.info("Started Penumbra job")
        mongoService.findAllActive()
            .flatMap { claimer.claim(it) }
            .delayElements(Duration.ofSeconds(120))
            .flatMap { sshService.runSshCommand(it) }
            .doOnError { LOG.error("Penumbra job finished with error\n" + it.message) }
            .subscribe { LOG.info("Job successfully finished!") }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
        private const val HOURS_DELAY_MS = 1000 * 60 * 60 * 24L
        private const val START_UP_DELAY_MS = 6000L
    }
}
