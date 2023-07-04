package com.davinhdev.eurosport.ui.scenes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davinhdev.eurosport.R
import com.davinhdev.eurosport.ui.theme.EurosportColors
import com.davinhdev.eurosport.ui.theme.EurosportTheme

@Composable
fun Error() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(EurosportColors.white)
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = stringResource(id = R.string.error_message),
                style = MaterialTheme.typography.h2,
                color = EurosportColors.black,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewError() {
    EurosportTheme {
        Error()
    }
}
