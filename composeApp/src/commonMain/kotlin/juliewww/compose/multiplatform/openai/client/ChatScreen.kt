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
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel = koinViewModel(),
    settingsViewModel: SettingsViewModel = koinViewModel(),
) {
    val chatUiState by chatViewModel.chatState.collectAsStateWithLifecycle()
    val settingsUiState by settingsViewModel.settingsUiState.collectAsStateWithLifecycle()
    Column(modifier = modifier) {
        ChatBar(
            key = (settingsUiState as? SettingsUiState.Success)?.settings?.openAiKey ?: "",
            updateKey = settingsViewModel::setOpenAiKey
        )
        HorizontalDivider()
        Conversation(modifier = Modifier.weight(1f), chatList = chatUiState.chatList)
        InputBar { message ->
            chatViewModel.sendMessage(message)
        }
    }
}

