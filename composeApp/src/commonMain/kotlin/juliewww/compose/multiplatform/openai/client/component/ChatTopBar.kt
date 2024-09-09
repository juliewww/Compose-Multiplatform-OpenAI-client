package juliewww.compose.multiplatform.openai.client.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatBar(
    modifier: Modifier = Modifier,
    title: String = "Chat",
    key: String = "",
    updateKey: (String) -> Unit = {},
) {
    var text by rememberSaveable { mutableStateOf(title) }
    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            modifier = Modifier.size(20.dp).clickable {
                showSettingsDialog = true
            })
    }
    if (showSettingsDialog) {
        KeySettingDialog(key = key,
            onDismiss = { showSettingsDialog = false },
            updateKey = { updateKey(it) })
    }
}
