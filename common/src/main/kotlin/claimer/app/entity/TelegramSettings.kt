package claimer.app.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import sun.jvm.hotspot.oops.FieldType

@Document(collection = "telegramSettings")
data class TelegramSettings(
    @Id
    @Field("_id", targetType = FieldType.STRING)
    var id: UUID? = UUID.randomUUID(),

    @Field("clientSettings")
    val clientSettings: TelegramClientSettings,

    @Field("botSettings")
    val botSettings: TelegramBotSettings,
)

data class TelegramClientSettings(
    @Field("apiId")
    val apiId: String,

    @Field("apiHash")
    val apiHash: String,

    @Field("phone")
    val phone: String,

    @Field("databaseEncryptionKey")
    val databaseEncryptionKey: String,

    @Field("deviceModel")
    val deviceModel: String,

    @Field("useMessageDatabase")
    val useMessageDatabase: Boolean,

    @Field("useFileDatabase")
    val useFileDatabase: Boolean,

    @Field("useChatInfoDatabase")
    val useChatInfoDatabase: Boolean,

    @Field("useSecretChats")
    val useSecretChats: Boolean,

    @Field("logVerbosityLevel")
    val logVerbosityLevel: Int,

    @Field("sessionPath")
    val sessionPath: String,

    @Field("databaseDirectory")
    val databaseDirectory: String,
)

data class TelegramBotSettings(
    @Field("token")
    val token: String,

    @Field("ownerId")
    val ownerId: Long
)
