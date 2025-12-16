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
    implementation(libs.starterWeb)
    implementation(libs.starterSecurity)
    implementation(libs.starterValidation)
    implementation(libs.jacksonDatatype310)

    // persistence related
    implementation(libs.starterDataJpa)
    runtimeOnly(libs.postgresDriver)

    // jwt stuff
    implementation(libs.jjwtApi)
    runtimeOnly(libs.jjwtImpl)
    implementation(libs.jjwtJackson)

    // dev tools
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
