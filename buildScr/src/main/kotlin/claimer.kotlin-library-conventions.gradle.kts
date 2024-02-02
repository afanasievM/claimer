import extentions.libraries

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("claimer.kotlin-common-conventions")
    id("org.jetbrains.kotlin.plugin.spring")
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(libraries.springBootDependencies)
    implementation(libraries.springBootStarterWebflux)
    implementation(libraries.springBootStarter)
    implementation(libraries.reactorKotlinExtensions)
    implementation(platform(libraries.reactorBom))
    implementation(libraries.reactorCore)
    implementation(libraries.reactorExtra)
    implementation(libraries.reactorTools)

    testImplementation(libraries.reactorTest)
    testImplementation(libraries.springBootTest)
    testImplementation(libraries.springBootStarterTest)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
