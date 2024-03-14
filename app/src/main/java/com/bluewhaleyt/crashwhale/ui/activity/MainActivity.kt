package com.bluewhaleyt.crashwhale.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bluewhaleyt.crashwhale.ui.screen.MainScreen
import com.bluewhaleyt.crashwhale.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier
                        .background( MaterialTheme.colorScheme.surface)
                        .fillMaxSize()
                ) {
                    MainScreen()
                }
            }
        }
    }
}