plugins {
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.devtools.ksp") version "2.2.10-2.0.2"
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.spring") version "2.2.10"
    kotlin("plugin.jpa") version "2.2.10"
}

group = "com.moira"
version = "1.0.0"
description = "the-byte"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val jjwtVersion = "0.12.6"
val queryDslVersion = "7.0"

dependencies {
    // database
    runtimeOnly("com.mysql:mysql-connector-j")
    // jwt
    implementation("io.jsonwebtoken:jjwt-api:${jjwtVersion}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${jjwtVersion}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${jjwtVersion}")
    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // querydsl
    implementation("io.github.openfeign.querydsl:querydsl-jpa:${queryDslVersion}")
    ksp("io.github.openfeign.querydsl:querydsl-ksp-codegen:${queryDslVersion}")
    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // web
    implementation("org.springframework.boot:spring-boot-starter-web")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
