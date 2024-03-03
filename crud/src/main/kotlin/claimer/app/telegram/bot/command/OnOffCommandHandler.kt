package claimer.app.telegram.bot.command

import claimer.app.entity.TelegramSettings
import claimer.app.telegram.bot.service.ProjectsCRUDService
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Update
import org.slf4j.LoggerFactory
import org.springframework.data.crossstore.ChangeSetPersister
import reactor.kotlin.core.publisher.switchIfEmpty

abstract class OnOffCommandHandler(
    telegramSettings: TelegramSettings,
    private val projectsCRUDService: ProjectsCRUDService
) : CommandHandler(telegramSettings) {

    abstract val activeValue: Boolean

    override suspend fun handleUpdate(bot: Bot, update: Update) {
        val message = update.message
        if (message == null) {
            LOG.info("message null")
            return
        }
        val name = if (message.text == null) "" else message.text!!.replace(command, "").trim()
        projectsCRUDService.findFirstProjectByName(name)
            .map {
                it.active = activeValue
                it
            }
            .flatMap { projectsCRUDService.save(it) }
            .doOnNext { bot.sendMessageToOwner(MESSAGE_SAVED.format(it.projectName)) }
            .switchIfEmpty {
                bot.sendMessageToOwner(MESSAGE_EMPTY + name)
                throw ChangeSetPersister.NotFoundException()
            }
            .onErrorStop()
            .subscribe()
    }

    companion object {
        private const val MESSAGE_EMPTY = "No project with name: "
        private const val MESSAGE_SAVED = "Project %s saved"
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
