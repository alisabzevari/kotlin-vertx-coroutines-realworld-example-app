import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val log4jVersion = "2.12.1"
val jacksonVersion = "2.10.0"
val kotestVersion = "4.4.3"
val vertxVersion = "4.0.3"

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    jacoco
    id("com.github.ben-manes.versions") version "0.26.0"
    id("com.adarshr.test-logger") version "2.0.0"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
    implementation("io.vertx:vertx-web")
    implementation("io.vertx:vertx-pg-client")
    implementation("io.vertx:vertx-lang-kotlin")
    implementation("io.vertx:vertx-lang-kotlin-coroutines")

    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.h2database:h2:1.4.198")

    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")

    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner:$jacksonVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-json:$kotestVersion")
    testImplementation("io.mockk:mockk:1.9.3")
}

application {
    // Define the main class for the application.
    mainClass.set("conduit.AppKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"
