module dorkbox.kloudflareApi {
    exports dorkbox.kloudflareApi;

    requires io.ktor.http;
    requires io.ktor.client.core;
    requires io.ktor.utils;
    requires io.ktor.io;
    requires io.ktor.client.cio;

    requires transitive okhttp3;
    requires transitive okhttp3.logging;

    requires transitive retrofit2;
    requires transitive retrofit2.converter.moshi;

    requires transitive com.squareup.moshi;
    requires transitive com.squareup.moshi.kotlin;

    requires transitive dorkbox.updates;

    requires transitive kotlin.reflect;
    requires transitive kotlin.stdlib;
}
