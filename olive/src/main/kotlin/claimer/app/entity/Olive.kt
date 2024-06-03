package claimer.app.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("olive")
data class Olive(
    @Id
    override var id: UUID? = UUID.randomUUID(),

    @Field("referral")
    var referral: String,

    @Field("discordChannelId")
    var discordChannelId: String,

    @Field("discordUserToken")
    var discordUserToken: String,

    override var active: Boolean,
    override var projectName: String,
) : Project
