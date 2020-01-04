
import java.time.Instant

println("\tGradle ${project.gradle.gradleVersion}")

plugins {
    java
    signing
    `maven-publish`

    // publish on sonatype
    id("de.marcphilipp.nexus-publish") version "0.4.0"
    // close and release on sonatype
    id("io.codearte.nexus-staging") version "0.21.1"

    id("com.dorkbox.Licensing") version "1.4.2"
    id("com.dorkbox.VersionUpdate") version "1.6.1"
    id("com.dorkbox.GradleUtils") version "1.2.8"

    kotlin("jvm") version "1.3.61"
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

    var sonatypeUserName = ""
    var sonatypePassword = ""
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
//    mavenLocal() // this must be first!
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
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    val moshiVer = "1.9.2"
    val okHttpVer = "4.2.2"
    val retroVer = "2.7.0"

    implementation("com.squareup.okhttp3:okhttp:$okHttpVer")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVer") // Log Network Calls

    // better SSL library
    implementation("org.conscrypt:conscrypt-openjdk-uber:2.2.1")

    // For serialization. THESE ARE NOT TRANSITIVE because it screws up the kotlin version
    implementation("com.squareup.retrofit2:retrofit:$retroVer")
    implementation("com.squareup.retrofit2:converter-moshi:$retroVer")
    implementation ("com.squareup.moshi:moshi:$moshiVer")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVer")
}

///////////////////////////////
//////    PUBLISH TO SONATYPE / MAVEN CENTRAL
//////
////// TESTING : local maven repo <PUBLISHING - publishToMavenLocal>
//////
////// RELEASE : sonatype / maven central, <PUBLISHING - publish> then <RELEASE - closeAndReleaseRepository>
///////////////////////////////
val sourceJar = task<Jar>("sourceJar") {
    description = "Creates a JAR that contains the source code."

    from(sourceSets["main"].java)

    archiveClassifier.set("sources")
}

val javaDocJar = task<Jar>("javaDocJar") {
    description = "Creates a JAR that contains the javadocs."

    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = Extras.group
            artifactId = Extras.id
            version = Extras.version

            from(components["java"])

            artifact(sourceJar)
            artifact(javaDocJar)

            pom {
                name.set(Extras.name)
                description.set(Extras.description)
                url.set(Extras.url)

                issueManagement {
                    url.set("${Extras.url}/issues")
                    system.set("Gitea Issues")
                }
                organization {
                    name.set(Extras.vendor)
                    url.set("https://dorkbox.com")
                }
                developers {
                    developer {
                        id.set("dorkbox")
                        name.set(Extras.vendor)
                        email.set("email@dorkbox.com")
                    }
                }
                scm {
                    url.set(Extras.url)
                    connection.set("scm:${Extras.url}.git")
                }
            }
        }
    }

    signing {
        setRequired({ project.gradle.taskGraph.hasTask("publishToSonatype") })

        // the home dir for the signing keys are wrong in windows. This makes sure it's consistent
        extra["signing.secretKeyRingFile"] = System.getProperty("user.home") + "\\.gnupg\\secring.gpg"
        sign(publishing.publications["maven"])
    }

    nexusStaging {
        packageGroup = Extras.group
        username = Extras.sonatypeUserName
        password = Extras.sonatypePassword
    }

    nexusPublishing {
        packageGroup.set(Extras.group)

        repositories {
            sonatype {
                username.set(Extras.sonatypeUserName)
                password.set(Extras.sonatypePassword)
            }
        }
    }

    task<Task>("Publish & Release to Sonatype") {
        group = "publish and release"

        // required to make sure the tasks run in the correct order
        tasks["closeAndReleaseRepository"].mustRunAfter(tasks["publishToSonatype"])
        dependsOn("publishToSonatype", "closeAndReleaseRepository")
    }

    task<Task>("Publish & Release to Maven Local") {
        group = "publish and release"

        // required to make sure the tasks run in the correct order
        dependsOn("publishToMavenLocal")
    }

    // "hide" all of the irrelevant tasks by un-setting their group (which will cause them NOT to display).
    // 'gradle tasks --all' will still show them
    project.afterEvaluate {
        tasks.forEach {
            if (it.group == "release") {
                it.apply {
                    this.group = ""
                }
            } else if (it.group == "publishing") {
                it.apply {
                    this.group = ""
                }
            }
        }
    }

    // output the local coordinates in the console
    tasks["publishToMavenLocal"].doLast {
        println("Maven Local coordinates: ${Extras.group}:${Extras.name}:${Extras.version}")
    }

    // output the release URL in the console
    tasks["releaseRepository"].doLast {
        val url = "https://oss.sonatype.org/content/repositories/releases/"
        val projectName = Extras.group.replace('.', '/')
        val name = Extras.name
        val version = Extras.version

        println("Maven URL: $url$projectName/$name/$version/")
    }
}
