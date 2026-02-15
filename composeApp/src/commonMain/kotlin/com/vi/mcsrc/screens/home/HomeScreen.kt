package com.vi.mcsrc.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vi.mcsrc.data.MCVersion
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VersionSelectScreen(onDismiss: () -> Unit, newSelectedVersion: (MCVersion) -> Unit) {
    val viewModel = koinViewModel<MainViewModel>()

    val versions by viewModel.versions.collectAsStateWithLifecycle()
    val selectedVersion by viewModel.selectedVersion.collectAsState()
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).fillMaxHeight(0.6f)
                .fillMaxWidth(0.8f),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
            )
        )
        {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                versions.forEach {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selectedVersion == it,
                            {
                                viewModel.setSelectedVersion(it)
                                newSelectedVersion(it)
                            })
                        Text(it.id)
                    }
                }
            }
        }
    }
}