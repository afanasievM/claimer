package claimer.app.repository

import claimer.app.entity.Olive
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface OliveRepository : ReactiveCrudRepository<Olive, UUID> {
    override fun findById(id: UUID): Mono<Olive>
    override fun findAll(): Flux<Olive>
    fun findAllByActiveIsTrue(): Flux<Olive>
    fun findAllByActiveIsFalse(): Flux<Olive>
    fun save(olive: Olive): Mono<Olive>
    fun findByProjectName(name: String): Mono<Olive>
}
