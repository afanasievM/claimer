package claimer.app.telegram

import claimer.app.entity.TelegramSettings
import it.tdlight.ClientFactory
import it.tdlight.Log
import it.tdlight.Slf4JLogMessageHandler
import it.tdlight.client.APIToken
import it.tdlight.client.AuthenticationSupplier
import it.tdlight.client.SimpleTelegramClient
import it.tdlight.client.SimpleTelegramClientBuilder
import it.tdlight.client.SimpleTelegramClientFactory
import it.tdlight.client.TDLibSettings
import it.tdlight.jni.TdApi
import it.tdlight.jni.TdApi.AuthorizationStateClosed
import it.tdlight.jni.TdApi.AuthorizationStateClosing
import it.tdlight.jni.TdApi.AuthorizationStateLoggingOut
import it.tdlight.jni.TdApi.AuthorizationStateReady
import it.tdlight.jni.TdApi.InputMessageText
import it.tdlight.jni.TdApi.SendMessage
import it.tdlight.jni.TdApi.UpdateAuthorizationState
import it.tdlight.util.NativeLibraryLoader
import java.lang.reflect.Method
import java.nio.file.Paths
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class TelegramClient(private val telegramSettings: TelegramSettings) {
    private lateinit var clientFactory: SimpleTelegramClientFactory

    init {
        Log.setLogMessageHandler(2, Slf4JLogMessageHandler())
        clientFactory = SimpleTelegramClientFactory(ClientFactory.create())
    }

    //    /data/telegramDb
    fun sendMessageToChat(message: String, chatId: Long): Mono<Unit> {
//        println("tg")
//        Init.init()
        val client = buildClient(clientFactory)
//        TdApi.Close()
        Thread.sleep(5 * 1000)
        val mes = client.sendMessage(buildMessage(message, chatId), true)
        mes.get()
        client.sendClose()
//        client.close()
//        client.sendClose()
        println("CLOSSSSSSSSss")
//        clientFactory.close()
//        println("CLOS2")
//        TdApi.Close()
        return Mono.just(Unit)
    }

    private fun buildClient(clientFactory: SimpleTelegramClientFactory): SimpleTelegramClient {
        val props = telegramSettings.clientSettings
        val apiToken = APIToken(props.apiId.toInt(), props.apiHash)
        val settings = TDLibSettings.create(apiToken)

        val sessionPath = Paths.get(props.sessionPath)
//        val sessionPath = Paths.get("example-tdlight-session")
        settings.databaseDirectoryPath = sessionPath.resolve(props.databaseDirectory)
        val clientBuilder = clientFactory.builder(settings)
        val authenticationData = AuthenticationSupplier.user(props.phone)
        setupHandlers(clientBuilder)
        return clientBuilder.build(authenticationData)
    }

    private fun buildMessage(message: String, chatId: Long): SendMessage {
        val textContent = InputMessageText()
        textContent.text = TdApi.FormattedText(message, null)
        val textReq = SendMessage()
        textReq.chatId = chatId
        textReq.inputMessageContent = textContent
        return textReq
    }

    private fun setupHandlers(clientBuilder: SimpleTelegramClientBuilder) {
        clientBuilder.addUpdateHandler<UpdateAuthorizationState>(
            UpdateAuthorizationState::class.java,
            this::onUpdateAuthorizationState
        )
    }


    private fun onUpdateAuthorizationState(update: UpdateAuthorizationState) {
        val authorizationState = update.authorizationState
        var state = ""
        if (authorizationState is AuthorizationStateReady) state = "Logged in"
        else if (authorizationState is AuthorizationStateClosing) state = "Closing..."
        else if (authorizationState is AuthorizationStateClosed) state = "Closed"
        else if (authorizationState is AuthorizationStateLoggingOut) state = "Logging out..."
        println(state)
    }


    companion object {
        private val LOG = LoggerFactory.getLogger(Companion::class.java)
    }
}
