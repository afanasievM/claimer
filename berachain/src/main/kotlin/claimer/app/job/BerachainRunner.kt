package claimer.app.job

import claimer.app.service.BerachainClaimService
import claimer.app.service.BerachainMongoService
import java.time.LocalDateTime

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class BerachainRunner(
    private val mongoService: BerachainMongoService,
    private val claimService: BerachainClaimService
) {

    @Scheduled(fixedDelay = HOURS_DELAY_MS, initialDelay = START_UP_DELAY_MS)
    fun run() {
        LOG.info("Started Berachain job")

        mongoService.findAllEnabled()
            .flatMap { claimService.claim(it) }
            .map {}
            .onErrorResume {
                LOG.error("Berachain job finished with error\n" + it.message)
                Mono.just(Unit)
            }
            .doOnComplete {
                LOG.info("Job successfully finished!")
                LOG.info(
                    "Next execution of Berachain job at: ${
                        LocalDateTime.now().plusSeconds(HOURS_DELAY_MS / 1000)
                    }"
                )
            }
            .blockLast()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
        private const val HOURS_DELAY_MS = 1000 * 60 * 60 * 8L
        private const val START_UP_DELAY_MS = 5000L
    }
}
