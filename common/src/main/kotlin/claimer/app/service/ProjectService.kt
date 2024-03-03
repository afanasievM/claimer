package claimer.app.service

import claimer.app.entity.Project
import java.util.UUID
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProjectService<T> {
    fun findById(id: UUID): Mono<T>

    fun findAll(): Flux<T>

    fun save(project: T): Mono<T>
    fun save(project: Project): Mono<T>

    fun findAllEnabled(): Flux<T>

    fun findAllDisabled(): Flux<T>

    fun findByName(name: String): Mono<T>
}
