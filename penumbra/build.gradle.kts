plugins {
    id("claimer.kotlin-library-conventions")
}

dependencies {
    implementation(project(":discord"))
    implementation(project(":common"))
    implementation(project(":ssh-client"))
}
