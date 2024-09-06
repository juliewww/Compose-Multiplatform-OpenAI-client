package juliewww.compose.multiplatform.openai.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import juliewww.compose.multiplatform.openai.client.model.OpenAIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class ChatViewModel(
    private val openAIRepository: OpenAIRepository,
) : ViewModel() {
    private var _chatState = MutableStateFlow(ChatState())
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    fun sendMessage(message: String) {
        _chatState.value = _chatState.value.copy(
            chatList = _chatState.value.chatList.toMutableList().apply {
                add(Chat(message = message, sender = Sender.User))
                add(Chat(message = "Typing...", sender = Sender.AI))
            }
        )
        viewModelScope.launch {
            chatCompletionRequest(message)
        }
    }

    private suspend fun chatCompletionRequest(message: String) {
        val result: StringBuilder = StringBuilder()
        openAIRepository.chatCompletion(
            message = message,
            history = integrateHistory()
        ).onEach { print(it.choices.first().delta?.content.orEmpty()) }
            .onCompletion { println() }
            .collect {
                result.append(it.choices.first().delta?.content.orEmpty())
                updateLocalAnswer(result.toString())
            }
    }

    private fun updateLocalAnswer(answer: String) {
        // Replace the last message with the answer
        _chatState.value = _chatState.value.copy(
            chatList = _chatState.value.chatList.toMutableList().apply {
                removeLast()
                add(Chat(message = answer, sender = Sender.AI))
            }
        )
    }

    private fun integrateHistory(): String {
        // Integrate the last 6 messages and sender into the history as a whole string
        val history = _chatState.value.chatList.takeLast(6).joinToString(separator = "") {
            "${it.sender.value}: ${it.message}\n"
        }
        return history
    }
}

data class ChatState(
    val title: String = "Chat",
    val chatList: MutableList<Chat> = mutableListOf(
        Chat(
            message = "Hello, how can I help you?",
            sender = Sender.AI,
        ),
    ),
)

@Serializable
data class Chat(
    val message: String = "",
    val sender: Sender = Sender.AI,
)

enum class Sender(
    val value: String,
) {
    AI("AI"),
    User("User"),
}
