package com.vi.mcsrc.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single(createdAtStart = true)
class VersionRepository(
    private val versionManifestAPI: VersionManifestAPI,
    private val versionStorage: VersionStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    init {
        initialize()
    }

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        versionStorage.saveVersions(versionManifestAPI.getManifest().versions)
    }

    fun getVersions(): Flow<List<MCVersion>> = versionStorage.getVersionDatas()

    fun getVersionById(versionId: String): Flow<MCVersion?> = versionStorage.getVersionDataById(versionId)
}