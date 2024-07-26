dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":infrastructure:persistence"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}
