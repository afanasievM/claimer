package claimer.app.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document("shardeum")
data class Shardeum(
    @Id
    override var id: UUID? = UUID.randomUUID(),

    @Field("wallet")
    var wallet: String,

    @Field("telegramChannelId")
    var telegramChannelId: String,

    override var active: Boolean,
    override var projectName: String,
) : Project
