import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.Effort
import com.github.spotbugs.snom.SpotBugsTask

plugins {
    java
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
    id("com.github.spotbugs") version("6.4.8") // SAST
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

spotbugs {
    toolVersion = "4.9.8"
    ignoreFailures = false
    effort = Effort.MAX
    reportLevel = Confidence.MEDIUM
}

tasks.withType<SpotBugsTask> {
    reports {
        create("xml") {
            enabled = true
            outputLocation = layout.buildDirectory.get().dir("reports/spotbugs/spotbugs-report.xml").asFile
        }
        create("sarif") {
            enabled = true
            outputLocation = layout.buildDirectory.get().dir("reports/spotbugs/spotbugs-report.sarif").asFile
        }
        create("html") {
            enabled = true
            outputLocation = layout.buildDirectory.get().dir("reports/spotbugs/spotbugs-report.html").asFile
        }
    }
}

tasks.named<DefaultTask>("check") {
    dependsOn(tasks.withType<SpotBugsTask>())
}
