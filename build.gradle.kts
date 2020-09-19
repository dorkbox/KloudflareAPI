
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.Instant


///////////////////////////////
//////    PUBLISH TO SONATYPE / MAVEN CENTRAL
////// TESTING : (to local maven repo) <'publish and release' - 'publishToMavenLocal'>
////// RELEASE : (to sonatype/maven central), <'publish and release' - 'publishToSonatypeAndRelease'>
///////////////////////////////

plugins {
    java

    id("com.dorkbox.GradleUtils") version "1.8"
    id("com.dorkbox.Licensing") version "1.4.2"
    id("com.dorkbox.VersionUpdate") version "1.6.1"
    id("com.dorkbox.GradlePublish") version "1.2"

    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
}

object Extras {
    // set for the project
    const val description = "Cloudflare API v4 for Kotlin"
    const val group = "com.dorkbox"
    const val version = "1.3"

    // set as project.ext
    const val name = "KloudflareAPI"
    const val id = "KloudflareAPI"
    const val vendor = "Dorkbox LLC"
    const val vendorUrl = "https://dorkbox.com"
    const val url = "https://git.dorkbox.com/dorkbox/KloudflareAPI"
    val buildDate = Instant.now().toString()

    val JAVA_VERSION = JavaVersion.VERSION_11.toString()
    const val KOTLIN_API_VERSION = "1.3"
    const val KOTLIN_LANG_VERSION = "1.3"
}

///////////////////////////////
/////  assign 'Extras'
///////////////////////////////
GradleUtils.load("$projectDir/../../gradle.properties", Extras)
GradleUtils.fixIntellijPaths()

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
//    mavenLocal() // this must be first!
    jcenter()
}



///////////////////////////////
//////    Task defaults
///////////////////////////////
tasks.withType<JavaCompile> {
    doFirst {
        println("\tCompiling classes to Java $sourceCompatibility")
    }

    options.encoding = "UTF-8"

    sourceCompatibility = Extras.JAVA_VERSION
    targetCompatibility = Extras.JAVA_VERSION
}

tasks.withType<KotlinCompile> {
    doFirst {
        println("\tCompiling classes to Kotlin, Java ${kotlinOptions.jvmTarget}")
    }

    sourceCompatibility = Extras.JAVA_VERSION
    targetCompatibility = Extras.JAVA_VERSION

    // see: https://kotlinlang.org/docs/reference/using-gradle.html
    kotlinOptions {
        jvmTarget = Extras.JAVA_VERSION
        apiVersion = Extras.KOTLIN_API_VERSION
        languageVersion = Extras.KOTLIN_LANG_VERSION
    }
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
        // https://docs.oracle.com/javase/tutorial/deployment/jar/packageman.html
        attributes["Name"] = Extras.name

        attributes["Specification-Title"] = Extras.name
        attributes["Specification-Version"] = Extras.version
        attributes["Specification-Vendor"] = Extras.vendor

        attributes["Implementation-Title"] = "${Extras.group}.${Extras.id}"
        attributes["Implementation-Version"] = Extras.buildDate
        attributes["Implementation-Vendor"] = Extras.vendor

        attributes["Automatic-Module-Name"] = Extras.id
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

    val moshiVer = "1.9.2"
    val okHttpVer = "4.3.0"
    val retroVer = "2.7.1"

    implementation("com.squareup.okhttp3:okhttp:$okHttpVer")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVer") // Log Network Calls

    // better SSL library
    implementation("org.conscrypt:conscrypt-openjdk-uber:2.2.1")

    // For serialization. THESE ARE NOT TRANSITIVE because it screws up the kotlin version
    implementation("com.squareup.retrofit2:retrofit:$retroVer")
    implementation("com.squareup.retrofit2:converter-moshi:$retroVer")

    implementation ("com.squareup.moshi:moshi:$moshiVer")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVer")

    // for AUTOMATIC kotlin reflective serialization of json classes
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:$moshiVer")
    kaptTest ("com.squareup.moshi:moshi-kotlin-codegen:$moshiVer")
}

publishToSonatype {
    groupId = Extras.group
    artifactId = Extras.id
    version = Extras.version

    name = Extras.name
    description = Extras.description
    url = Extras.url

    vendor = Extras.vendor
    vendorUrl = Extras.vendorUrl

    issueManagement {
        url = "${Extras.url}/issues"
        nickname = "Gitea Issues"
    }

    developer {
        id = "dorkbox"
        name = Extras.vendor
        email = "email@dorkbox.com"
    }
}
