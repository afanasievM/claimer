package claimer.app.repository

import claimer.app.entity.Penumbra
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PenumbraRepository : ReactiveCrudRepository<Penumbra, UUID> {
    override fun findById(id: UUID): Mono<Penumbra>
    override fun findAll(): Flux<Penumbra>
    fun findAllByActiveIsTrue(): Flux<Penumbra>
    fun save(penumbra: Penumbra): Mono<Penumbra>
}
