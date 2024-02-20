package claimer.app.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document("penumbra")
data class Penumbra(
    @Id
    @Field("_id", targetType = FieldType.STRING)
    var id: UUID? = UUID.randomUUID(),

    @Field("wallet")
    var wallet: String,

    @Field("discordChannelId")
    var discordChannelId: String,

    @Field("discordUserToken")
    var discordUserToken: String,

    @Field("sshCommand")
    var sshCommand: String,

    override var active: Boolean,
    override var projectName: String,
) : Project
