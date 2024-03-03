package claimer.app.repository

import claimer.app.entity.Berachain
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BerachainRepository : ReactiveCrudRepository<Berachain, UUID> {
    override fun findById(id: UUID): Mono<Berachain>
    override fun findAll(): Flux<Berachain>
    fun findAllByActiveIsTrue(): Flux<Berachain>
    fun findAllByActiveIsFalse(): Flux<Berachain>
    fun save(berachain: Berachain): Mono<Berachain>
    fun findByProjectName(name: String): Mono<Berachain>

}
