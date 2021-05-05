buildscript {
    repositories {
        mavenLocal()
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/releases/") }
        maven { url = uri("https://dl.bintray.com/jetbrains/intellij-plugin-service") }
        maven { url = uri("https://dl.bintray.com/jetbrains/intellij-third-party-dependencies/") }
    }
    dependencies {
        classpath("org.jetbrains.intellij.plugins:gradle-intellij-plugin:0.7.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
}

plugins {
    java
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.intellij") version "0.7.2"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

intellij {
    localPath =
        "/Users/jinghong/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/211.7142.45/IntelliJ IDEA.app"
    type = "IU"
    setPlugins(
        "java",
        "Kotlin",
        "Spring",
        "SpringBoot",
        "properties",
        "yaml"
    )

    pluginName = "RestfulToolkit-fix"
    updateSinceUntilBuild = false
    isDownloadSources = true
}

group = "me.jinghong.restful.toolkit"
version = "1.0.0"

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

dependencies {
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
