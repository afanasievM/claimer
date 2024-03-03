package claimer.app.telegram.bot.command

import claimer.app.entity.TelegramSettings
import claimer.app.telegram.bot.filter.Filter
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatcher.handlers.Handler
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.Update
import com.github.kotlintelegrambot.entities.dice.DiceEmoji

abstract class CommandHandler(telegramSettings: TelegramSettings) : Handler, Filter(telegramSettings) {

    abstract val command: String
    override fun checkUpdate(update: Update): Boolean {
        return filterByCommandAndOwnerId(update, command)
    }

    protected fun Bot.sendMessageToOwner(message: String) {
        this.sendMessage(ChatId.fromId(ownerId), message)
    }
}
