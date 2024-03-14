package com.bluewhaleyt.crashwhale.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExceptionContainer(
    title: String,
    snackbarHostState: SnackbarHostState,
    onExit: () -> Unit,
    onCopy: () -> Unit,
    onRestart: () -> Unit,
    onShare: () -> Unit,
    content: @Composable () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        canScroll = { true }
    )
    val windowInsets = TopAppBarDefaults.windowInsets

    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                LargeTopAppBar(
                    title = {
                        Column {
                            Text(text = title)
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    windowInsets = windowInsets,
                    navigationIcon = {
                        IconButton(onClick = onExit) {
                            Icon(imageVector = Icons.Outlined.Close, contentDescription = "Exit")
                        }
                    },
                    actions = {
                        IconButton(onClick = onCopy) {
                            Icon(imageVector = Icons.Outlined.CopyAll, contentDescription = "Copy")
                        }
                        IconButton(onClick = onShare) {
                            Icon(imageVector = Icons.Outlined.Share, contentDescription = "Share")
                        }
                        IconButton(onClick = onRestart) {
                            Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "Restart")
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) {
                    Snackbar(snackbarData = it)
                }
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                content()
            }
        }
    }
}