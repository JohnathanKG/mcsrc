package com.vi.mcsrc.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.mcsrc.data.MCVersion
import com.vi.mcsrc.data.VersionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.core.annotation.KoinViewModel


@KoinViewModel
class MainViewModel(versionRepository: VersionRepository) : ViewModel() {
    val versions: StateFlow<List<MCVersion>> =
        versionRepository.getVersions().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    var selectedVersion: MutableStateFlow<MCVersion?> = MutableStateFlow(null)

    fun setSelectedVersion(newVersion: MCVersion) {
        selectedVersion.value = newVersion
    }
}