package claimer.app.service

import claimer.app.entity.Penumbra
import claimer.app.repository.PenumbraRepository
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PenumbraMongoService(private val repository: PenumbraRepository) {

    fun findById(id: UUID): Mono<Penumbra> {
        return repository.findById(id)
    }

    fun findAll(): Flux<Penumbra> {
        return repository.findAll()
    }

    fun save(penumbra: Penumbra): Mono<Penumbra> {
        return repository.save(penumbra)
    }

    fun findAllActive(): Flux<Penumbra> {
        return repository.findAllByActiveIsTrue()
    }
}
