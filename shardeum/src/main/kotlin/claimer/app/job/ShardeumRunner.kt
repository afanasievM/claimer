package claimer.app.job

import claimer.app.service.ShardeumClaimer
import claimer.app.service.ShardeumMongoService
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

//@Component
class ShardeumRunner(
    private val claimer: ShardeumClaimer,
    private val mongoService: ShardeumMongoService
) {

//    @Scheduled(cron = CRON_EXPRESSION)
    fun run() {
        LOG.info("Started Shardeum job")
        mongoService.findAllEnabled()
            .flatMap { claimer.claim(it) }
            .onErrorResume {
                LOG.error("Shardeum job finished with error\n" + it.message)
                LOG.error(it.stackTraceToString())
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
