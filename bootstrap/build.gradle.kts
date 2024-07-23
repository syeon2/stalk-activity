dependencies {
    implementation(project(":support:msa-core"))
    implementation(project(":support:monitor"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
