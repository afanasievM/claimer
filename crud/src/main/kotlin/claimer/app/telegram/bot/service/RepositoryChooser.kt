package claimer.app.telegram.bot.service

import claimer.app.entity.Berachain
import claimer.app.entity.Penumbra
import claimer.app.entity.Project
import claimer.app.entity.Shardeum
import claimer.app.service.ProjectService
import kotlin.reflect.KClass
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class RepositoryChooser(
    private final val penumbraService: ProjectService<Penumbra>,
    private final val shardeumService: ProjectService<Shardeum>,
    private final val berachainService: ProjectService<Berachain>
) {
    private val repositoryMap = mapOf(
        Penumbra::class.java to penumbraService,
        Shardeum::class.java to shardeumService,
        Berachain::class.java to berachainService
    )

    fun choose(project: Project): ProjectService<out Project> {
        return repositoryMap.getOrElse(project.javaClass) { throw IllegalArgumentException("project not found") }
    }

    fun getAllRepositories(): List<ProjectService<out Project>> {
        return repositoryMap.values.toList()
    }
}
