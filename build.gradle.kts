plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
    id("org.jlleitschuh.gradle.ktlint")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

val projectGroup: String by project
val applicationVersion: String by project

allprojects {
    group = projectGroup
    version = applicationVersion

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    val kotlinMockitoVersion: String by project
    val springCloudDependenciesVersion: String by project

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudDependenciesVersion")
        }
    }

    dependencies {
        // base config
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // spring
        implementation("org.springframework.boot:spring-boot-starter")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.mockito.kotlin:mockito-kotlin:$kotlinMockitoVersion")
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }
}
