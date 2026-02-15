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

@Single
class InMemoryVersionStorage : VersionStorage {
    private val storedDatas = MutableStateFlow(emptyList<MCVersion>())

    override suspend fun saveVersions(version: List<MCVersion>) {
        storedDatas.value = version
    }

    override fun getVersionDataById(versionId: String): Flow<MCVersion?> {
        return storedDatas.map { objects ->
            objects.find { it.id == versionId }
        }
    }

    override fun getVersionDatas(): Flow<List<MCVersion>> = storedDatas

}