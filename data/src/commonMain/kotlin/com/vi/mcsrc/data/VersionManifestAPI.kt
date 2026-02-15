package com.vi.mcsrc.data

import io.ktor.client.*

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.koin.core.annotation.Single

@Serializable
data class MCVersionManifest(
    val latest: LatestMCRelease,
    val versions: List<MCVersion>
)
@Serializable
data class LatestMCRelease(
    val release: String,
    val snapshot: String
)

@Serializable
data class MCVersion(
    val id: String,
    val type: String,
    val url: String,
    val time: String,
    val releaseTime: String,
    val sha1: String,
    val complianceLevel: Int
)

@Serializable
data class MCVersionDownloads(
    val client: MCVersionDownload,
    val server: MCVersionDownload,
)

@Serializable
data class MCLibArtifact(
    val path: String,
    val sha1: String,
    val size: Long,
    val url: String
)

@Serializable
data class MCLibDownloads(
    val artifact: MCLibArtifact,
)

@Serializable
data class MCLibrary(
    val downloads: MCLibDownloads,
    val name: String

)

@Serializable
data class MCVersionDownload(
    val sha1: String,
    val size: Long,
    val url: String,
)


@Serializable
data class MCVersionThing(
    val downloads: MCVersionDownloads,
    val libraries: List<MCLibrary>
)

val jsonConfig = Json { ignoreUnknownKeys = true }


interface VersionManifestAPI {
    suspend fun getManifest(): MCVersionManifest
}


@Single
class KtorVersionManifestAPI(private val client: HttpClient): VersionManifestAPI {
    private companion object {
        var manifest: MCVersionManifest? = null
        const val MANIFEST_URL: String = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json"
    }

//    suspend fun downloadJarForVer(version: MCVersion): Job
//    {
//        val response = client.get(manifest?.versions?.firstOrNull()!!.url)
//        val json = jsonConfig.decodeFromString<MCVersionThing>(response.bodyAsText())
//        val jarRsponse = client.get(json.downloads.client.url).bodyAsBytes()
//        jarFile.value = File.createTempFile(version.id, ".jar")
//        jarFile.value!!.writeBytes(jarRsponse);
//
//    }

    override suspend fun getManifest(): MCVersionManifest {

        if(manifest != null) return manifest!!
        val response = client.get(MANIFEST_URL)

        manifest = Json.decodeFromString<MCVersionManifest>(response.bodyAsText())
        return manifest!!
    }
}