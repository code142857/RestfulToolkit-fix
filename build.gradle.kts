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
        classpath("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.7.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    }
}

plugins {
    java
    kotlin("jvm") version "1.7.0"
    id("org.jetbrains.intellij") version "1.7.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

intellij {
    type.set("IU")
    plugins.set(
        listOf(
            "java",
            "Kotlin",
            "Spring",
            "SpringBoot",
            "properties",
            "yaml"
        )
    )
//     version = "2021.3"
     version .set( "2021.1.3")
//    version.set("2022.1.4")
//    localPath =
//        "/Users/jinghong/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/222.3345.90/IntelliJ IDEA.app/Contents"
    pluginName.set("RestfulToolkit-fix")
    updateSinceUntilBuild.set(false)
    downloadSources.set(true)
}

group = "me.jinghong.restful.toolkit"
version = "2.0.8"

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("com.fifesoft:rsyntaxtextarea:3.1.6")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
