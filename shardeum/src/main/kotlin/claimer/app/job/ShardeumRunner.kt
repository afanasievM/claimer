package claimer.app.job

import claimer.app.service.ShardeumClaimer
import claimer.app.service.ShardeumMongoService
import java.time.Duration
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ShardeumRunner(
    private val claimer: ShardeumClaimer,
    private val mongoService: ShardeumMongoService
) {

    //    @Scheduled(cron = CRON_EXPRESSION)
    @Scheduled(fixedDelay = 1000 * 60 * 60, initialDelay = 5000)
    fun run() {
        LOG.info("Started Shardeum job")
        mongoService.findAllActive()
            .flatMap { claimer.claim(it) }
            .onErrorResume {
                LOG.error("Penumbra job finished with error\n" + it.message)
                Mono.just(Unit)
            }
            .doOnComplete { LOG.info("Job successfully finished!") }
            .blockLast()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
        private const val CRON_EXPRESSION = "0 0 21 * * *"
    }
}