import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    jacoco
}

group = "nl.jaysh"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.exposed)
    implementation(libs.bundles.flyway)
    implementation(libs.bundles.spring)
    implementation(libs.jackson)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.coroutines.reactor)
    implementation(libs.firebase)
    developmentOnly(libs.spring.boot.devtools)
    runtimeOnly(libs.bundles.storage)
    testImplementation(libs.spring.boot.starter.test) {
        exclude(module = "mockito-core")
        exclude(module = "mockito-junit-jupiter")
    }
    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
        jvmTarget = JvmTarget.JVM_23
    }
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.withType<JacocoReport>() {
    dependsOn("test")

    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(true)
    }
}
