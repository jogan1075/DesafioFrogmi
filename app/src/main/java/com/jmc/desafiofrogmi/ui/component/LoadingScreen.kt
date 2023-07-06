package com.jmc.desafiofrogmi.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import com.jmc.desafiofrogmi.ui.theme.Overlay

@Composable
fun LoadingScreen(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Surface(modifier = Modifier.disableGestures(true)) {
            Surface(
                color = Overlay,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            content()
        }
    } else {
        content()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.disableGestures(disabled: Boolean = true) =
    if (disabled) {
        pointerInput(Unit) {
            awaitPointerEventScope {
                // we should wait for all new pointer events
                while (true) {
                    awaitPointerEvent(pass = PointerEventPass.Initial)
                        .changes
                        .forEach(PointerInputChange::consume)
                }
            }
        }
    } else {
        Modifier
    }