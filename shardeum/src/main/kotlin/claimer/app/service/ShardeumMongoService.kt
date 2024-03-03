package claimer.app.service

import claimer.app.entity.Shardeum
import claimer.app.repository.ShardeumRepository
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ShardeumMongoService(private val repository: ShardeumRepository): ProjectService<Shardeum> {

    override fun findById(id: UUID): Mono<Shardeum> {
        return repository.findById(id)
    }

    override fun findAll(): Flux<Shardeum> {
        return repository.findAll()
    }

    override fun save(project: Shardeum): Mono<Shardeum> {
        return repository.save(project)
    }

    override fun findAllEnabled(): Flux<Shardeum> {
        return repository.findAllByActiveIsTrue()
    }

    override fun findAllDisabled(): Flux<Shardeum> {
        return repository.findAllByActiveIsFalse()
    }

    override fun findByName(name: String): Mono<Shardeum> {
        return repository.findByProjectName(name)
    }
}
