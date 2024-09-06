package juliewww.compose.multiplatform.openai.client

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import juliewww.compose.multiplatform.openai.client.component.ChatBar
import juliewww.compose.multiplatform.openai.client.component.Conversation
import juliewww.compose.multiplatform.openai.client.component.InputBar

import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = koinViewModel(),
) {
    val uiState by viewModel.chatState.collectAsStateWithLifecycle()
    Column(modifier = modifier) {
        ChatBar()
        HorizontalDivider()
        Conversation(modifier = Modifier.weight(1f), chatList = uiState.chatList)
        InputBar { message ->
            viewModel.sendMessage(message)
        }
    }
}
