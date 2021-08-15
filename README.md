KloudflareAPI
=======

###### [![Dorkbox](https://badge.dorkbox.com/dorkbox.svg "Dorkbox")](https://git.dorkbox.com/dorkbox/KloudflareAPI) [![Github](https://badge.dorkbox.com/github.svg "Github")](https://github.com/dorkbox/KloudflareAPI) [![Gitlab](https://badge.dorkbox.com/gitlab.svg "Gitlab")](https://gitlab.com/dorkbox/KloudflareAPI) [![Bitbucket](https://badge.dorkbox.com/bitbucket.svg "Bitbucket")](https://bitbucket.org/dorkbox/KloudflareAPI)


KloudflareAPI is an kotlin project that implements (some of) the Cloudflare v4 API, allowing one manage DNS zones and user information

Pull requests are welcome!

- This is for cross-platform use, specifically - linux 32/64, mac 64, and windows 32/64. Java 8+
    
``` java
val email = "test@example.com"
val token = "ac1d3f45b51cc5d23e47aa4ac7c07b54d60f2"

val kloudflare = Kloudflare(email, token)
println(kloudflare.getUser())
        
val listZones = kloudflare.listZones()
listZones.forEach { zone ->
    println("${zone.name} : ${zone.id}")

    val dnsRecords = kloudflare.listDnsRecords(zone)
    dnsRecords.forEach { dnsRecord ->
        if (dnsRecord.type == RecordType.A || dnsRecord.type == RecordType.AAAA) {
            val domainLength = zone.name.length
            val length = dnsRecord.name.length - domainLength

            val domainName = if (length == 0) {
                "@"
            }
            else {
                // -1 because we don't want the '.'
                dnsRecord.name.subSequence(0, length - 1)
            }

            println("  $domainName ${dnsRecord.type} : ${dnsRecord.id}")

            val newDnsRecord = CreateDnsRecord(zone)
            newDnsRecord.type = RecordType.A
            newDnsRecord.name = "test"
            newDnsRecord.content = "1.2.3.4"

            val newRecord = kloudflare.createDnsRecord(newDnsRecord)
            println("Created: ${newRecord.name} -> ${newRecord.content}")
        }
    }
}
```

&nbsp; 
&nbsp; 

Maven Info
---------
```
<dependencies>
    ...
    <dependency>
      <groupId>com.dorkbox</groupId>
      <artifactId>KloudflareAPI</artifactId>
      <version>1.4</version>
    </dependency>
</dependencies>
```

Gradle Info
---------
````
dependencies {
    ...
    compile 'com.dorkbox:KloudflareAPI:1.3'
}
````

License
---------
This project is © 2019 dorkbox llc, and is distributed under the terms of the Apache v2.0 License. See file "LICENSE" for further references.

