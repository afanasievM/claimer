package claimer.app.entity

import org.springframework.data.mongodb.core.mapping.Field

interface Project {
    @set:Field("active")
    var active: Boolean

    @set:Field("projectName")
    var projectName: String
}


