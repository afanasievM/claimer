package claimer.app.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document("sshCredentials")
data class SshCredentials(
    @Id
    @Field("_id", targetType = FieldType.STRING)
    var id: UUID? = UUID.randomUUID(),

    @Field("host")
    var host: String,

    @Field("port")
    var port: String,

    @Field("username")
    var username: String,

    @Field("password")
    var password: String
)
