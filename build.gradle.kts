plugins {
    java
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
}

group = "com.enzulode"
version = "0.0.1"
description = "ITMO information security: lab1"

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // api related
    implementation(libs.starterWebmvc)
    implementation(libs.starterSecurity)

    // persistence related
    implementation(libs.starterDataJpa)
    implementation(libs.starterLiquibase)
    runtimeOnly(libs.postgresDriver)

    // dev tools
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
