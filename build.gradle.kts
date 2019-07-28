
import java.time.Instant

println("\tGradle ${project.gradle.gradleVersion}")

plugins {
    java

    id("com.dorkbox.Licensing") version "1.4"
    id("com.dorkbox.VersionUpdate") version "1.4.1"
    id("com.dorkbox.GradleUtils") version "1.2"

    kotlin("jvm") version "1.3.40"
}

object Extras {
    // set for the project
    const val description = "Cloudflare API v4 for Kotlin"
    const val group = "com.dorkbox"
    const val version = "1.0"

    // set as project.ext
    const val name = "KloudflareAPI"
    const val id = "KloudflareAPI"
    const val vendor = "Dorkbox LLC"
    const val url = "https://git.dorkbox.com/dorkbox/KloudflareAPI"
    val buildDate = Instant.now().toString()

    val JAVA_VERSION = JavaVersion.VERSION_1_8.toString()
}

///////////////////////////////
/////  assign 'Extras'
///////////////////////////////
GradleUtils.load("$projectDir/../../gradle.properties", Extras)
description = Extras.description
group = Extras.group
version = Extras.version


licensing {
    license(License.APACHE_2) {
        author(Extras.vendor)
        url(Extras.url)
        note(Extras.description)
    }
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))

            // want to include java and kotlin files for the source. 'setSrcDirs' resets includes...
            include("**/*.java", "**/*.kt")
        }
    }

    test {
        java {
            setSrcDirs(listOf("test"))

            // want to include java and kotlin files for the source. 'setSrcDirs' resets includes...
            include("**/*.java", "**/*.kt")
        }
    }
}

repositories {
    mavenLocal() // this must be first!
    jcenter()
}



///////////////////////////////
//////    Task defaults
///////////////////////////////
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"

    sourceCompatibility = Extras.JAVA_VERSION
    targetCompatibility = Extras.JAVA_VERSION
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.FAIL
}

tasks.compileJava.get().apply {
    println("\tCompiling classes to Java $sourceCompatibility")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.FAIL
}

tasks.jar.get().apply {
    duplicatesStrategy = DuplicatesStrategy.FAIL

    manifest {
        attributes["Implementation-Version"] = Extras.version
        attributes["Build-Date"] = Extras.buildDate
        attributes["Main-Class"] = "dorkbox.UndertowServerTest"
    }
}

configurations.all {
    resolutionStrategy {
        // fail eagerly on version conflict (includes transitive dependencies)
        // e.g. multiple different versions of the same dependency (group and name are equal)
        failOnVersionConflict()

        // if there is a version we specified, USE THAT VERSION (over transitive versions)
        preferProjectModules()

        // cache dynamic versions for 10 minutes
        cacheDynamicVersionsFor(10 * 60, "seconds")

        // don't cache changing modules at all
        cacheChangingModulesFor(0, "seconds")
    }
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    val coroutrineVer = "1.2.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutrineVer")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutrineVer")

    val okHttpVer = "4.0.0"
    val moshiVer = "1.8.0"
    val retroVer = "2.6.0"

    implementation("com.squareup.okhttp3:okhttp:$okHttpVer")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVer") // Log Network Calls

    // For serialization. THESE ARE NOT TRANSITIVE because it screws up the kotlin version
    implementation("com.squareup.retrofit2:retrofit:$retroVer")
    implementation("com.squareup.retrofit2:converter-moshi:$retroVer")
    implementation ("com.squareup.moshi:moshi:$moshiVer")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVer")


    // awesome logging framework for kotlin.
    // https://www.reddit.com/r/Kotlin/comments/8gbiul/slf4j_loggers_in_3_ways/
    // https://github.com/MicroUtils/kotlin-logging
    implementation("io.github.microutils:kotlin-logging:1.6.26")
    implementation("io.github.microutils:kotlin-logging-common:1.6.26")

    implementation("org.slf4j:slf4j-api:1.7.26")

    implementation("ch.qos.logback:logback-core:1.2.3")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}
