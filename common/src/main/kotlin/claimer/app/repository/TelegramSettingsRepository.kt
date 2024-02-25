package claimer.app.repository

import claimer.app.entity.TelegramSettings
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface TelegramSettingsRepository : ReactiveCrudRepository<TelegramSettings, UUID> {
    override fun findAll(): Flux<TelegramSettings>
}
