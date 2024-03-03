package claimer.app.telegram.bot

import claimer.app.entity.TelegramSettings
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.handlers.Handler
import com.github.kotlintelegrambot.entities.ChatId
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private
import org.springframework.stereotype.Component

@Component
class TelegramBot(
    private val telegramSettings: TelegramSettings,
    private val handlers: List<Handler>
) {
    private lateinit var bot: Bot
    private lateinit var token: String

    init {
        token = telegramSettings.botSettings.token
        val bot = bot {
            token = this@TelegramBot.token
            dispatch {
                handlers.forEach { addHandler(it) }
            }
        }
        bot.startPolling()
    }

    fun sendMessage(channelId: Long, message: String) {
        bot.sendMessage(ChatId.fromId(channelId), message)
    }


}
