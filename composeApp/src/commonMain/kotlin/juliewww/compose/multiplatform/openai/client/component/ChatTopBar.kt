package juliewww.compose.multiplatform.openai.client.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ChatBar(
    modifier: Modifier = Modifier,
    title: String = "Chat",
) {
    var text by rememberSaveable { mutableStateOf(title) }
    Row(modifier = modifier) {
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
    }
}
