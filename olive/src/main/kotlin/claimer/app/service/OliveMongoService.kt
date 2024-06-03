package claimer.app.service

import claimer.app.entity.Olive
import claimer.app.repository.OliveRepository
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OliveMongoService(private val repository: OliveRepository) : ProjectService<Olive> {

    override fun findById(id: UUID): Mono<Olive> {
        return repository.findById(id)
    }

    override fun findAll(): Flux<Olive> {
        return repository.findAll()
    }

    override fun save(project: Olive): Mono<Olive> {
        return repository.save(project)
    }

    override fun findAllEnabled(): Flux<Olive> {
        return repository.findAllByActiveIsTrue()
    }

    override fun findAllDisabled(): Flux<Olive> {
        return repository.findAllByActiveIsFalse()
    }

    override fun findByName(name: String): Mono<Olive> {
        return repository.findByProjectName(name)
    }
}
