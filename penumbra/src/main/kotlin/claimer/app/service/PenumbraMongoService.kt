package claimer.app.service

import claimer.app.entity.Penumbra
import claimer.app.entity.Project
import claimer.app.repository.PenumbraRepository
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PenumbraMongoService(private val repository: PenumbraRepository) : ProjectService<Penumbra> {

    override fun findById(id: UUID): Mono<Penumbra> {
        return repository.findById(id)
    }

    override fun findAll(): Flux<Penumbra> {
        return repository.findAll()
    }

    override fun save(project: Project): Mono<Penumbra> {
        return save(project as Penumbra)
    }

    override fun save(project: Penumbra): Mono<Penumbra> {
        return repository.save(project)
    }

    override fun findAllEnabled(): Flux<Penumbra> {
        return repository.findAllByActiveIsTrue()
    }

    override fun findAllDisabled(): Flux<Penumbra> {
        return repository.findAllByActiveIsFalse()
    }

    override fun findByName(name: String): Mono<Penumbra> {
        return repository.findByProjectName(name)
    }
}
