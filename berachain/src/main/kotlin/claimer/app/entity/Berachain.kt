package claimer.app.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("berachain")
data class Berachain(
    @Id
    override var id: UUID? = UUID.randomUUID(),

    @Field("wallet")
    var wallet: String,

    override var active: Boolean,
    override var projectName: String,
): Project
