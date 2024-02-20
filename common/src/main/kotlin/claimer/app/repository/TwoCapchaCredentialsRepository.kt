package claimer.app.repository

import claimer.app.entity.TwoCapchaCredentials
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface TwoCapchaCredentialsRepository : ReactiveCrudRepository<TwoCapchaCredentials, UUID> {
    override fun findAll(): Flux<TwoCapchaCredentials>
}
