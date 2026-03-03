package redron.tradox.core.ui.components.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import redron.tradox.core.common.R

@Composable
fun IconBack() {
    Icon(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = stringResource(R.string.back)
    )
}

