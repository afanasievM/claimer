package claimer.app.telegram.bot.command

import claimer.app.entity.Project
import claimer.app.entity.TelegramSettings
import claimer.app.telegram.bot.enums.Emoji
import claimer.app.telegram.bot.service.ProjectsCRUDService
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Update
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ShowDisabledCommandHandler(
    telegramSettings: TelegramSettings,
    private val projectsCRUDService: ProjectsCRUDService
) : CommandHandler(telegramSettings) {

    override val command: String
        get() = COMMAND

    override suspend fun handleUpdate(bot: Bot, update: Update) {
        projectsCRUDService.findAllDisabledProjects().collectList().subscribe {
            val message = createMessage(it)
            bot.sendMessageToOwner(message)
        }
    }

    private fun createMessage(projects: List<Project>): String {
        return when (projects.isEmpty()) {
            true -> MESSAGE_EMPTY
            false -> MESSAGE +
                    projects.joinToString(separator = "\n") { "${it.projectName} --> ${Emoji.RED_CIRCLE.unicode}" }
        }
    }


    companion object {
        private const val COMMAND = "/show_disabled"
        private const val MESSAGE = "Disabled projects:\n"
        private const val MESSAGE_EMPTY = "No disabled projects"
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
