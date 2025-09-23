import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    java
    alias(libs.plugins.springBootPlugin)
    alias(libs.plugins.dependencyManagementPlugin)
    alias(libs.plugins.spotbugsPlugin)
}

group = "com.enzulode"
version = "1.0.0"
description = "ITMO InfoSec: lab1"
base {
    archivesName = "infosec-lab1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.starterWeb)
    implementation(libs.starterDataJpa)
    runtimeOnly(libs.postgresqlDriver)
    implementation(libs.starterSecurity)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

configurations["compileOnly"].extendsFrom(configurations["annotationProcessor"])
java.toolchain.languageVersion = JavaLanguageVersion.of(libs.versions.jvm.get())
tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<BootBuildImage>("bootBuildImage") {
    environment = mapOf(
        "BPE_DELIM_JAVA_TOOL_OPTIONS" to " ",
        "BPE_APPEND_JAVA_TOOL_OPTIONS" to "-Dspring.profiles.default=prod",
    )
}
