import org.jetbrains.kotlin.gradle.utils.COMPILE_ONLY

plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.inf_ruxy.plugin.proxyBan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.0")

    compileOnly("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}