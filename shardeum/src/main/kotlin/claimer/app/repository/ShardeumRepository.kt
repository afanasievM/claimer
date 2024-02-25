package claimer.app.repository

import claimer.app.entity.Shardeum
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ShardeumRepository : ReactiveCrudRepository<Shardeum, UUID> {
    override fun findById(id: UUID): Mono<Shardeum>
    override fun findAll(): Flux<Shardeum>
    fun findAllByActiveIsTrue(): Flux<Shardeum>
    fun save(penumbra: Shardeum): Mono<Shardeum>
}
