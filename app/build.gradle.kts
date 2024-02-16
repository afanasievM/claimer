
plugins {
    id("claimer.kotlin-application-conventions")
}

dependencies {
    implementation(project(":discord"))
    implementation(project(":penumbra"))
}

application {
    mainClass.set("claimer.app.AppKt")
}
