package claimer.app.telegram.bot.service

import claimer.app.entity.Project
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProjectsCRUDService(val repositoryChooser: RepositoryChooser) {

    fun findAllProjects(): Flux<out Project> {
        return Flux.fromIterable(repositoryChooser.getAllRepositories()).flatMap { it.findAll() }
    }

    fun findAllEnabledProjects(): Flux<out Project> {
        return Flux.fromIterable(repositoryChooser.getAllRepositories()).flatMap { it.findAllEnabled() }
    }

    fun findAllDisabledProjects(): Flux<out Project> {
        return Flux.fromIterable(repositoryChooser.getAllRepositories()).flatMap { it.findAllDisabled() }
    }

    fun findFirstProjectByName(name: String): Mono<out Project> {
        return Flux.fromIterable(repositoryChooser.getAllRepositories()).flatMap { it.findByName(name) }.next()
    }

    fun save(project: Project): Mono<out Project> {
        return repositoryChooser.choose(project)
            .save(project)
            .doOnNext { LOG.info("Saved project: $it") }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
