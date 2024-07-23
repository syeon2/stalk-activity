dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}
