package juliewww.compose.multiplatform.openai.client.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun KeySettingDialog(
    key: String,
    modifier: Modifier = Modifier,
    updateKey: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    var keyTextValue by rememberSaveable { mutableStateOf(key) }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Open AI key management",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            OutlinedTextField(
                value = keyTextValue,
                onValueChange = { keyTextValue = it },
            )
        },
        confirmButton = {
            Button(onClick = {
                updateKey(keyTextValue)
                onDismiss()
            }) {
                Text("Update")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}
