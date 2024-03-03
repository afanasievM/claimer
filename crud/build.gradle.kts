plugins {
    id("claimer.kotlin-library-conventions")
}
repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(project(":penumbra"))
    implementation(project(":shardeum"))
    implementation(project(":berachain"))
    implementation(project(":common"))
    implementation(libs.telegramBotLib)
}
