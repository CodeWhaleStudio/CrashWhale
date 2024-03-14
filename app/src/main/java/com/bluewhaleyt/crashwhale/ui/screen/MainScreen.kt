package com.bluewhaleyt.crashwhale.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bluewhaleyt.crashwhale.ui.activity.ComposeActivity
import com.bluewhaleyt.crashwhale.ui.activity.XmlActivity

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, ComposeActivity::class.java)
                )
            }
        ) {
            Text(text = "Go to Compose activity")
        }
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, XmlActivity::class.java)
                )
            }
        ) {
            Text(text = "Go to Xml activity")
        }
    }
}