plugins {
    id("claimer.kotlin-library-conventions")
}

dependencies {
    implementation(libs.gsonLib)
    implementation(libs.simpleJavaApiWrapperLib)
    implementation(libs.springBootOAuth2)
    implementation(libs.springBootStarterSecurity)
}
