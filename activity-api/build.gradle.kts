dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.getByName("jar").enabled = true
tasks.getByName("bootJar").enabled = false
