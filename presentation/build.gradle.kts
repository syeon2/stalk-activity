plugins {
    id("com.epages.restdocs-api-spec")
}

val springRestDocsVersion: String by project
val applicationVersion: String by project

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":infrastructure:persistence"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("com.epages:restdocs-api-spec-mockmvc:$springRestDocsVersion")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

openapi3 {
    title = "Stalk Activity Service Docs"
    description = "Post, Comment APIs"
    version = applicationVersion
    format = "json"
    outputDirectory = "build/docs"
    outputFileNamePrefix = "activity-service"
}
