package com.davinhdev.eurosport.ui.scenes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davinhdev.eurosport.ui.theme.EurosportColors
import com.davinhdev.eurosport.ui.theme.EurosportTheme

@Composable
fun Loader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            strokeWidth = 2.dp,
            color = EurosportColors.lightBlue
        )
    }
}

@Preview
@Composable
private fun PreviewLoader() {
    EurosportTheme {
        Loader()
    }
}
