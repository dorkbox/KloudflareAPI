/*
 * Copyright 2025 dorkbox, llc
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

package dorkbox.kloudflareApi

import dorkbox.kloudflareApi.api.dns.ARecord
import kotlinx.coroutines.runBlocking
import java.time.Instant

/**
 *
 */
object KloudflareTest {
    init {
        // fix logging context
        val property = System.getProperty("logging.context")
        if (property == null) {
            // we also have "webserver", but that is specified during webserver production launch
            System.setProperty("logging.context", "kloudflareAPI")
        }

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            run {
                println("Uncaught exception in thread: '${t.name}'")
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    fun main(arguments: Array<String>) {
        // drop everything to lower case.
        for ((index, value) in arguments.withIndex()) {
            val lowerCase = value.lowercase()
            arguments[index] = lowerCase
        }

        println("Starting : ${Instant.now()}")

        // start up the application!
        val jvmName = System.getProperty("java.vm.name")
        val jvmVersion = System.getProperty("java.version")
        val jvmVendor = System.getProperty("java.vm.specification.vendor")
        println("Execution JVM: $jvmVendor  $jvmName $jvmVersion")
        println("Execution arguments: ${arguments.joinToString()}")


        val email = "user@example.com"
        val token = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"

        val kloudflare = Kloudflare(email, token)

        try {

            runBlocking {
//                println(kloudflare.user.details())
//                println(kloudflare.user.billingProfile())
//                println(kloudflare.user.billingHistory())

//                val zones = kloudflare.zones.list()
//                zones.forEach { zone: Zone ->
//                    println(zone)
////                    val ratePlans = zone.ratePlans()
////                    println(ratePlans)
//
//                    val dnsRecords = zone.dnsRecords()
//                    dnsRecords.forEach { dnsRecord: DnsRecord ->
//                        println(dnsRecord)
//                    }
//                }

                val newDnsRecord = ARecord()
                newDnsRecord.name = "testing3.dorkbox.com"
                newDnsRecord.content = "1.1.1.1"

                val zone = kloudflare.zones.list().first{ it.name == "dorkbox.com" }
                var record = zone.dnsRecords().firstOrNull { it.name == "testing3.dorkbox.com" }
                if (record == null) {
                    record = zone.createDnsRecord(newDnsRecord)
                    println("Created: $newDnsRecord")
                } else {
                    println("Reused: $record")
                }


                record.ttl = 1
                record.update()
                println("To 1: ${record.update()}")

                record.ttl = 60
                println("To 60: ${record.update()}")
            }

    //        println(kloudflare.getZoneSettings("123"))
//            println(kloudflare.listAccessRules())
//
//
//            val zone = kloudflare.listZones().first { it.name == "dorkbox.com" }
//            zone.dnsRecords.forEach {
//               println(it)
//            }

//

        }
        finally {
            kloudflare.shutdown()
        }
    }
}
