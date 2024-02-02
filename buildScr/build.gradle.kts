plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.owaspDependencyLib)
    implementation(libs.springBootGradlePlugin)
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.kotlinSpringPlugin)
}
