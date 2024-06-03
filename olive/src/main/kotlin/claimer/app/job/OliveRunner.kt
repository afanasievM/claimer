package claimer.app.job

import claimer.app.service.OliveClaimer
import claimer.app.service.OliveMongoService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OliveRunner(
    private val claimer: OliveClaimer,
    private val mongoService: OliveMongoService
) {

    @Scheduled(fixedDelay = HOURS_DELAY_MS, initialDelay = START_UP_DELAY_MS)
    fun run() {
        LOG.info("Started Olive job")
        mongoService.findAllEnabled()
            .flatMap { claimer.claim(it) }
            .doOnError {
                LOG.error("Olive job finished with error\n" + it.message)
            }
            .doOnComplete { LOG.info("Job successfully finished!") }
            .blockLast()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
        private const val HOURS_DELAY_MS = 1000 * 60 * 60 * 6L
        private const val START_UP_DELAY_MS = 10000L
    }
}
