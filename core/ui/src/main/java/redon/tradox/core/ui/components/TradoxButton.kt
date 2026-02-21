package redon.tradox.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import redon.tradox.core.ui.theme.TradoxTheme


@Composable
fun TradoxButton(
    text: String,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = true,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val buttonColors = when {
        isPrimary -> ButtonDefaults.buttonColors(
            containerColor = TradoxTheme.colors.primary,
            contentColor = TradoxTheme.colors.background,
            disabledContainerColor = TradoxTheme.colors.primarySoft,
            disabledContentColor = TradoxTheme.colors.background,
        )

        else -> ButtonDefaults.buttonColors(
            containerColor = TradoxTheme.colors.background,
            contentColor = TradoxTheme.colors.error,
            disabledContainerColor = TradoxTheme.colors.background,
            disabledContentColor = TradoxTheme.colors.textDisabled,
        )
    }

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = buttonColors,
        shape = TradoxTheme.shapes.medium,
        contentPadding = PaddingValues(
            horizontal = 60.dp,
            vertical = 10.dp,
        ),
        elevation = null,
    ) {
        Text(
            text = text,
            style = TradoxTheme.typography.body1,
        )
    }
}

@Composable
@Preview
private fun TradoxButtonPreview() {
    TradoxTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TradoxButton(
                text = "Кнопка 1",
            ) {}

            TradoxButton(
                text = "Кнопка 2",
                enabled = false,
            ) {}

            TradoxButton(
                text = "Кнопка 3",
                isPrimary = false,
            ) {}
            TradoxButton(
                text = "Кнопка 4",
                isPrimary = false,
                enabled = false,
            ) { }
        }
    }
}
