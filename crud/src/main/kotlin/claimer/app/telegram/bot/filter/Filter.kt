package claimer.app.telegram.bot.filter

import claimer.app.entity.TelegramSettings
import com.github.kotlintelegrambot.entities.Update

abstract class Filter(private val telegramSettings: TelegramSettings) {
    var ownerId: Long = 0

    init {
        ownerId = telegramSettings.botSettings.ownerId
    }

    fun filterByOwnerId(update: Update) = update.message?.from?.id?.equals(ownerId) == true
    fun filterByCommand(update: Update, command: String) = update.message?.text?.startsWith(command) == true

    fun filterByCommandAndOwnerId(update: Update, command: String) =
        filterByOwnerId(update) && filterByCommand(update, command)
}
