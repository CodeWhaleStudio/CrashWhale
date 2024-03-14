package com.bluewhaleyt.crashwhale.ui.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.bluewhaleyt.crashwhale.ExceptionData
import com.bluewhaleyt.crashwhale.R
import com.bluewhaleyt.crashwhale.ui.screen.component.ExceptionContainer
import com.bluewhaleyt.crashwhale.ui.utils.copyToClipboard
import com.bluewhaleyt.crashwhale.ui.utils.restartApplication
import com.bluewhaleyt.crashwhale.ui.utils.share
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@Composable
internal fun ExceptionScreen(
    exceptionData: ExceptionData,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val copyStacktraceMessage = stringResource(id = R.string.copy_stacktrace_message)

    ExceptionContainer(
        title = stringResource(id = R.string.exception_title),
        onExit = {
            exitProcess(2)
        },
        onCopy = {
            scope.launch {
                exceptionData.stacktrace.copyToClipboard(context, "Stacktrace")
                snackbarHostState.showSnackbar(copyStacktraceMessage)
            }
        },
        onRestart = {
            context.restartApplication()
        },
        onShare = {
            exceptionData.stacktrace.share(context, "Stacktrace")
        },
        snackbarHostState = snackbarHostState
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.exception_description),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = """
                ${stringResource(id = R.string.type)} : ${exceptionData.type}
                ${stringResource(id = R.string.cause)}  : ${exceptionData.cause}
                ${stringResource(id = R.string.message)} : ${exceptionData.message}
                
                ${stringResource(id = R.string.manufacturer)} : ${exceptionData.manufacturer}
                ${stringResource(id = R.string.device)} : ${exceptionData.device}
                ${stringResource(id = R.string.android)} : ${exceptionData.androidOsVersion} (API ${exceptionData.androidSdkVersion})
                ${stringResource(id = R.string.time)} : ${exceptionData.time}
            """.trimIndent())

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(id = R.string.stacktrace)} : "
            )
            StacktraceText(text = exceptionData.stacktrace)
        }
    }
}

@Composable
private fun StacktraceText(
    text: String
) {
    val scrollState = rememberScrollState()
    SelectionContainer(
        modifier = Modifier
            .horizontalScroll(scrollState)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.error,
            fontFamily = FontFamily.Monospace,
        )
    }
}