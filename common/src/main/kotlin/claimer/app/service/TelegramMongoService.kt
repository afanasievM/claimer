package claimer.app.service

import claimer.app.entity.TelegramSettings
import claimer.app.repository.TelegramSettingsRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class TelegramMongoService(private val repository: TelegramSettingsRepository) {
    fun findSettings(): Mono<TelegramSettings> {
        return repository.findAll().next()
    }
}
