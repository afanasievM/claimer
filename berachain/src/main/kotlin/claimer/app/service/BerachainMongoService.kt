package claimer.app.service

import claimer.app.entity.Berachain
import claimer.app.repository.BerachainRepository
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BerachainMongoService(private val repository: BerachainRepository) {

    fun findById(id: UUID): Mono<Berachain> {
        return repository.findById(id)
    }

    fun findAll(): Flux<Berachain> {
        return repository.findAll()
    }

    fun save(berachain: Berachain): Mono<Berachain> {
        return repository.save(berachain)
    }

    fun findAllActive(): Flux<Berachain> {
        return repository.findAllByActiveIsTrue()
    }
}
