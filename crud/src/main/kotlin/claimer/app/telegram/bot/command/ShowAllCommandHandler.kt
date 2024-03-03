package claimer.app.telegram.bot.command

import claimer.app.entity.Project
import claimer.app.entity.TelegramSettings
import claimer.app.telegram.bot.enums.Emoji
import claimer.app.telegram.bot.filter.Filter
import claimer.app.telegram.bot.service.ProjectsCRUDService
import claimer.app.telegram.bot.service.RepositoryChooser
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatcher.handlers.Handler
import com.github.kotlintelegrambot.entities.Update
import com.github.kotlintelegrambot.entities.dice.DiceEmoji
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toMono

@Component
class ShowAllCommandHandler(
    telegramSettings: TelegramSettings,
    private val projectsCRUDService: ProjectsCRUDService
) : CommandHandler(telegramSettings) {

    override val command: String
        get() = COMMAND

    override suspend fun handleUpdate(bot: Bot, update: Update) {
        projectsCRUDService.findAllProjects().collectList().subscribe {
            val message = createMessage(it)
            bot.sendMessageToOwner(message)
        }
    }

    private fun createMessage(projects: List<Project>): String {
        return MESSAGE + projects.joinToString(separator = "\n") { "${it.projectName} --> ${getCircleEmoji(it.active)}" }
    }

    private fun getCircleEmoji(predicate: Boolean) = when (predicate) {
        true -> Emoji.GREEN_CIRCLE.unicode
        false -> Emoji.RED_CIRCLE.unicode
    }


    companion object {
        private const val COMMAND = "/show_all"
        private const val MESSAGE = "All projects:\n"
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
