/*
 * Copyright 2021 dorkbox, llc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.time.Instant

///////////////////////////////
//////    PUBLISH TO SONATYPE / MAVEN CENTRAL
////// TESTING : (to local maven repo) <'publish and release' - 'publishToMavenLocal'>
////// RELEASE : (to sonatype/maven central), <'publish and release' - 'publishToSonatypeAndRelease'>
///////////////////////////////

gradle.startParameter.showStacktrace = ShowStacktrace.ALWAYS   // always show the stacktrace!
gradle.startParameter.warningMode = WarningMode.All

plugins {
    id("com.dorkbox.GradleUtils") version "2.9"
    id("com.dorkbox.Licensing") version "2.9.2"
    id("com.dorkbox.VersionUpdate") version "2.4"
    id("com.dorkbox.GradlePublish") version "1.11"

    kotlin("jvm") version "1.5.21"
    kotlin("kapt") version "1.5.21"
}

object Extras {
    // set for the project
    const val description = "Cloudflare API v4 for Kotlin"
    const val group = "com.dorkbox"
    const val version = "1.4"

    // set as project.ext
    const val name = "KloudflareAPI"
    const val id = "KloudflareAPI"
    const val vendor = "Dorkbox LLC"
    const val vendorUrl = "https://dorkbox.com"
    const val url = "https://git.dorkbox.com/dorkbox/KloudflareAPI"

    val buildDate = Instant.now().toString()
}

///////////////////////////////
/////  assign 'Extras'
///////////////////////////////
GradleUtils.load("$projectDir/../../gradle.properties", Extras)
GradleUtils.defaults()
GradleUtils.compileConfiguration(JavaVersion.VERSION_11)

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

tasks.jar.get().apply {
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

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    val moshiVer = "1.12.0"
    val okHttpVer = "4.9.1"
    val retroVer = "2.9.0"

    implementation("com.squareup.okhttp3:okhttp:$okHttpVer")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVer") // Log Network Calls

    // better SSL library
    implementation("org.conscrypt:conscrypt-openjdk-uber:2.5.2")

    // For serialization. THESE ARE NOT TRANSITIVE because it screws up the kotlin version
    implementation("com.squareup.retrofit2:retrofit:$retroVer")
    implementation("com.squareup.retrofit2:converter-moshi:$retroVer")

    implementation ("com.squareup.moshi:moshi:$moshiVer")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVer")

    implementation("com.dorkbox:Updates:1.1")

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
