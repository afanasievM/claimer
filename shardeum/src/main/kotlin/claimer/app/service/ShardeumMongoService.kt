package claimer.app.service

import claimer.app.entity.Shardeum
import claimer.app.repository.ShardeumRepository
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ShardeumMongoService(private val repository: ShardeumRepository) {

    fun findById(id: UUID): Mono<Shardeum> {
        return repository.findById(id)
    }

    fun findAll(): Flux<Shardeum> {
        return repository.findAll()
    }

    fun save(penumbra: Shardeum): Mono<Shardeum> {
        return repository.save(penumbra)
    }

    fun findAllActive(): Flux<Shardeum> {
        return repository.findAllByActiveIsTrue()
    }
}
