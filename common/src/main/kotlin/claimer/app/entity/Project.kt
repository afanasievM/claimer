package claimer.app.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

interface Project {
    @set:Field("_id", targetType = FieldType.STRING)
    var id: UUID?

    @set:Field("active")
    var active: Boolean

    @set:Field("projectName")
    var projectName: String
}


