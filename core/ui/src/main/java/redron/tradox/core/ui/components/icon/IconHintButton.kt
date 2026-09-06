package redron.tradox.core.ui.components.icon

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import redron.tradox.core.common.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun IconHintButton(
    @StringRes hintTextResId: Int,
    modifier: Modifier = Modifier
) {
    var showHint by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Icon(
            imageVector = Icons.Default.Help,
            contentDescription = stringResource(R.string.hint_icon_description),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(20.dp)
                .clickable { showHint = !showHint }
        )

        if (showHint) {
            Popup(
                alignment = Alignment.TopEnd,
                onDismissRequest = { showHint = false },
            ) {
                Box(
                    modifier = Modifier
                        .shadow(4.dp, CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .width(300.dp)
                        .padding(12.dp)
                ) {
                    Text(
                        text = stringResource(hintTextResId),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}
