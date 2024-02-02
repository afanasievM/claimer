import extentions.libraries

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm")
    idea
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libraries.reflect)
    implementation(libraries.stdlib)
    implementation(libraries.stdlibCommon)
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = Common.Compile.javaSource
        targetCompatibility = Common.Compile.javaTarget
        inputs.files(processResources)
    }
}
