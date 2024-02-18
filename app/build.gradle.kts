plugins {
    id("claimer.kotlin-application-conventions")
}

dependencies {
    implementation(project(":penumbra"))
    implementation(project(":berachain"))
}
val mainClassName = "claimer.app.AppKt"

application {
    mainClass = mainClassName
    sourceSets {
        main {
            java {
                setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
            }
        }
    }
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    manifest.apply {
        attributes.put("Main-Class", mainClassName)
    }
    sourceSets {
        main {
            java {
                setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
            }
        }
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

