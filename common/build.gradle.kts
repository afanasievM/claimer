plugins {
    id("claimer.kotlin-library-conventions")
}
repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.gsonLib)
    implementation(libs.maverickSynergyLib)
    implementation(libs.twoCapchaLib)
    implementation(platform(libs.tdLightLib))
    implementation(group = "it.tdlight", name = "tdlight-java")
    implementation(group = "it.tdlight", name = "tdlight-natives", classifier = "linux_amd64_gnu_ssl1")
    implementation(group = "it.tdlight", name = "tdlight-natives", classifier = "linux_amd64_clang_ssl3")
    implementation(group = "it.tdlight", name = "tdlight-natives", classifier = "linux_amd64_gnu_ssl3")
    implementation(group = "it.tdlight", name = "tdlight-natives", classifier = "macos_arm64")
    implementation(group = "it.tdlight", name = "tdlight-natives", classifier = "macos_amd64")
}
