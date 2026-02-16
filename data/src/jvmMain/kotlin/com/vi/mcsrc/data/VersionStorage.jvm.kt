package com.vi.mcsrc.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class PlatformVersionStorage


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

