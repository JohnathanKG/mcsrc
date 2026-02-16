package com.vi.mcsrc.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

interface VersionStorage {
    suspend fun saveVersions(versions: List<MCVersion>)
    fun getVersionDatas(): Flow<List<MCVersion>>
    fun getVersionDataById(versionId: String): Flow<MCVersion?>
}

expect class PlatformVersionStorage