
pluginManagement {
    includeBuild("buildScr")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "claimer"
include("app", "penumbra", "common", "berachain")
