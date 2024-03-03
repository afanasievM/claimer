package claimer.app.telegram.bot.command

import claimer.app.entity.TelegramSettings
import claimer.app.telegram.bot.service.ProjectsCRUDService
import org.springframework.stereotype.Component

@Component
class OnCommandHandler(
    telegramSettings: TelegramSettings,
    projectsCRUDService: ProjectsCRUDService,
) : OnOffCommandHandler(telegramSettings, projectsCRUDService) {

    override val command: String
        get() = COMMAND
    override val activeValue: Boolean
        get() = ACTIVE_VALUE

    companion object {
        private const val COMMAND = "/on"
        private const val ACTIVE_VALUE = true
    }
}
