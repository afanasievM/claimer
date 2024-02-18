package claimer.app.repository

import claimer.app.entity.SshCredentials
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface SshCredentialsRepository : ReactiveCrudRepository<SshCredentials, UUID> {
    override fun findAll(): Flux<SshCredentials>
}
