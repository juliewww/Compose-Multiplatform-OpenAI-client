package juliewww.compose.multiplatform.openai.client.model

import juliewww.compose.multiplatform.openai.client.Chat

data class UserData(
    val openAiKey: String,
    val chatHistory: Set<Chat>,
)
