package com.vi.mcsrc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vi.mcsrc.data.MCVersion
import com.vi.mcsrc.di.KoinApp
import com.vi.mcsrc.screens.home.VersionSelectScreen
import org.koin.compose.KoinApplication
import org.koin.plugin.module.dsl.koinConfiguration


@Composable
@Preview
fun App() {
    var selectedVersion by remember { mutableStateOf<MCVersion?>(null) }
    KoinApplication(configuration = koinConfiguration<KoinApp>()) {


        MaterialTheme {
            var showContent by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    VersionSelectScreen({ showContent = !showContent }, { selectedVersion = it; showContent = false })
                }
            }
        }
    }
}