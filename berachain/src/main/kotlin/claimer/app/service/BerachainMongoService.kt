package claimer.app.service

import claimer.app.entity.Berachain
import claimer.app.repository.BerachainRepository
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BerachainMongoService(private val repository: BerachainRepository) : ProjectService<Berachain> {

    override fun findById(id: UUID): Mono<Berachain> {
        return repository.findById(id)
    }

    override fun findAll(): Flux<Berachain> {
        return repository.findAll()
    }

    override fun save(project: Berachain): Mono<Berachain> {
        return repository.save(project)
    }

    override fun findAllEnabled(): Flux<Berachain> {
        return repository.findAllByActiveIsTrue()
    }

    override fun findAllDisabled(): Flux<Berachain> {
        return repository.findAllByActiveIsFalse()
    }

    override fun findByName(name: String): Mono<Berachain> {
        return repository.findByProjectName(name)
    }
}
