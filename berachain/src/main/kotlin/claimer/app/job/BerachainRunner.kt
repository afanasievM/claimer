package claimer.app.job

import claimer.app.service.BerachainClaimService
import claimer.app.service.BerachainMongoService

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BerachainRunner(
    private val mongoService: BerachainMongoService,
    private val claimService: BerachainClaimService
) {

    @Scheduled(cron = CRON_EXPRESSION)
    fun run() {
        LOG.info("Started Berachain job")

        mongoService.findAllActive()
            .flatMap { claimService.claim(it) }
            .doOnError { LOG.error("Berachain job finished with error\n" + it.message) }
            .subscribe { LOG.info("Job successfully finished!") }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
        private const val CRON_EXPRESSION = "0 0 1/8 * * *"
    }
}
