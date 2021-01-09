import io.qameta.allure.gradle.AllureExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junitVersion = "5.7.0"
val retrofitVersion = "2.9.0"
val allureVersion = "2.13.8"

plugins {
    kotlin("jvm") version "1.4.20"
    id("io.qameta.allure") version "2.8.1"
    id("org.openapi.generator") version "5.0.0"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform {

    }
    testLogging {
        events("passed", "skipped", "failed")
    }
    ignoreFailures = true
    finalizedBy("allureReport")
}

configure<AllureExtension> {
    autoconfigure = true
    aspectjweaver = true
    clean = true

    version = allureVersion
    useJUnit5 {
        version = allureVersion
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openapitools:openapi-generator-gradle-plugin:5.0.0")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-jackson:$retrofitVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("io.qameta.allure:allure-okhttp3:$allureVersion")
    implementation("io.qameta.allure:allure-junit5:$allureVersion")
    implementation("io.qameta.allure:allure-java-commons:$allureVersion")
    implementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("io.kotest:kotest-assertions-core-jvm:4.3.2")
}

openApiGenerate {
    generatorName.set("kotlin")
    library.set("jvm-retrofit2")

    configOptions.set(mapOf(
            "dateLibrary" to "java8",
            "serializationLibrary" to "jackson",
            "enumPropertyNaming" to "UPPERCASE"
    ))

    inputSpec.set("$rootDir/src/main/resources/specs/swagger.yaml")
    outputDir.set("$buildDir/generated")

    apiPackage.set("io.swagger.petstore.api")
    modelPackage.set("io.swagger.petstore.model")

    generateApiDocumentation.set(false)
    generateModelDocumentation.set(false)
}
