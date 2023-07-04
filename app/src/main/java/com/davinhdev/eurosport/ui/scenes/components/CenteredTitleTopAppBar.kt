package com.davinhdev.eurosport.ui.scenes.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davinhdev.eurosport.R
import com.davinhdev.eurosport.ui.theme.EurosportColors
import com.davinhdev.eurosport.ui.theme.EurosportTheme

private val IconModifier = Modifier
    .fillMaxHeight()
    .aspectRatio(1f)

@Composable
fun CenteredTitleTopAppBar(
    modifier: Modifier = Modifier,
    @DrawableRes navigationIcon: Int? = null,
    onNavigationIconClicked: (() -> Unit?)? = null,
    title: String = "",
    content: @Composable () -> Unit = {
        Spacer(
            modifier = IconModifier
        )
    }
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(EurosportColors.darkBlue),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
            color = EurosportColors.white
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (navigationIcon != null) {
                IconButton(
                    modifier = IconModifier,
                    onClick = { onNavigationIconClicked?.invoke() }
                ) {
                    Icon(
                        painter = painterResource(id = navigationIcon),
                        contentDescription = null,
                        tint = EurosportColors.white
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            content()
        }
    }
}

@Preview
@Composable
private fun PreviewCenteredTitleTopAppBar() {
    EurosportTheme {
        CenteredTitleTopAppBar(
            navigationIcon = R.drawable.ic_back,
            title = stringResource(id = R.string.app_name)
        )
    }
}

