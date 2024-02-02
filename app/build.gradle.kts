
plugins {
    id("claimer.kotlin-application-conventions")
}

dependencies {
    implementation(project(":discord"))
}

application {
    mainClass.set("claimer.app.AppKt")
}
