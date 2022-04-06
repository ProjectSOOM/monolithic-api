import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
}

group = "com.soom"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven { url = uri("https://www.jitpack.io" ) }
}

dependencies {
    /////SPRING BOOT/////
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor") //configuration
    implementation("org.springframework.boot:spring-boot-starter-web") //web
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") //data jpa
    implementation("org.springframework.boot:spring-boot-starter-data-redis") //redis
    implementation("org.springframework.boot:spring-boot-starter-validation") //validation
    implementation("org.springframework.boot:spring-boot-starter-security") //security
    implementation("org.springframework.boot:spring-boot-starter-mail") //mailing
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf") //thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-actuator") //actuator
    developmentOnly("org.springframework.boot:spring-boot-devtools") //devtool

    /////KOTLIN/////
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") //jackson for kotilin

    /////DATABASE/////
    runtimeOnly("mysql:mysql-connector-java") //mysql

    /////UTIL/////
    implementation("com.squareup.okhttp3:okhttp:4.9.1") //okhttp
    implementation("com.google.guava:guava:18.0") //guava
    implementation("io.jsonwebtoken:jjwt:0.9.1") //jwt token

    /////MESSAGING/////
    implementation("com.corundumstudio.socketio:netty-socketio:1.7.19") //socket

    /////PARSING/////
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0") //jackson
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.0")
    implementation("org.apache.tika:tika-parsers:2.2.1")//tika parser
    implementation("org.apache.tika:tika-core:2.2.1")
    implementation("io.github.leeseojune53:neis-api:1.0.3")//neisAPI
    implementation("com.github.AppS01u7E:NeisApi:0.04.00")

    /////MANAGING/////
    implementation ("org.springdoc:springdoc-openapi-ui:1.5.4")//openapi
    implementation("org.apache.logging.log4j:log4j-api:2.17.0") //logging

    /////INFRA/////
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE") //aws
    implementation("com.google.firebase:firebase-admin:7.1.0") //firebase
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
