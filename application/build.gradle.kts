dependencies {
    implementation(project(":domain"))
}

tasks.getByName("jar").enabled = true
tasks.getByName("bootJar").enabled = false
