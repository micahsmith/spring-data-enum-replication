import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version ("3.2.2-SNAPSHOT")
  id("io.spring.dependency-management") version "1.1.4"
  kotlin("jvm") version "1.9.21"
  kotlin("plugin.spring") version "1.9.21"
}

group = "msmith"

version = "0.0.1-SNAPSHOT"

java { sourceCompatibility = JavaVersion.VERSION_17 }

repositories {
  mavenCentral()
  maven { url = uri("https://repo.spring.io/milestone") }
  maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.flywaydb:flyway-core")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  implementation("org.postgresql:postgresql:42.7.1")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:junit-jupiter")

  testImplementation("org.testcontainers:postgresql:1.19.3")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> { useJUnitPlatform() }
