import extentions.libraries

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm")
    idea
    java
}

repositories {
    gradlePluginPortal()
    maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://mvn.mchv.eu/repository/mchv/") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(libraries.reflect)
    implementation(libraries.stdlib)
    implementation(libraries.stdlibCommon)
    implementation(libraries.kotlinJacksonLib)
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = Common.Compile.javaSource
        targetCompatibility = Common.Compile.javaTarget
        inputs.files(processResources)
    }
}
