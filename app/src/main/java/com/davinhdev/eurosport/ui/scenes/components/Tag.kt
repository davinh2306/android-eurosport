package com.davinhdev.eurosport.ui.scenes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davinhdev.eurosport.R
import com.davinhdev.eurosport.ui.theme.EurosportColors
import com.davinhdev.eurosport.ui.theme.EurosportTheme

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier
            .offset(y = (-12).dp) // (lineHeight + vertical padding x 2) / 2
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(EurosportColors.darkBlue)
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
        text = text,
        style = MaterialTheme.typography.subtitle2,
        color = EurosportColors.white,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}


@Preview
@Composable
private fun PreviewTag() {
    EurosportTheme {
        Tag(
            text = stringResource(id = R.string.app_name)
        )
    }
}