import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.maxvar"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.71"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("com.github.kittinunf.fuel:fuel:2.2.1")
    implementation("com.github.kittinunf.fuel:fuel-gson:2.2.1")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
    implementation(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}