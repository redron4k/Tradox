package redron.tradox.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import redron.tradox.core.common.R
import redron.tradox.core.ui.theme.TradoxTheme

@Composable
fun TradoxExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    collapsedMaxLines: Int = 3,
    @StringRes showMoreText: Int = R.string.common_show_more_label,
    @StringRes showLessText: Int = R.string.common_show_less_label,
    style: TextStyle = TradoxTheme.typography.body1,
    color: Color = TradoxTheme.colors.textPrimary,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isExpandable by remember(text) { mutableStateOf(false) }

    Column(modifier = modifier) {

        AnimatedContent(
            targetState = isExpanded,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
        ) { expanded ->

            Text(
                text = text,
                style = style,
                color = color,
                maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLines,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { result ->
                    if (!isExpandable && result.hasVisualOverflow) {
                        isExpandable = true
                    }
                }
            )
        }

        if (isExpandable) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = if (isExpanded)
                    stringResource(showLessText)
                else
                    stringResource(showMoreText),
                style = TradoxTheme.typography.body2,
                color = TradoxTheme.colors.primary,
                modifier = Modifier.clickable {
                    isExpanded = !isExpanded
                }
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TradoxExpandableTextPreview() {
    TradoxTheme {
        TradoxExpandableText(
            text = "Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit, sed do eiusmod tempor " +
                    "incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation"
        )
    }
}
