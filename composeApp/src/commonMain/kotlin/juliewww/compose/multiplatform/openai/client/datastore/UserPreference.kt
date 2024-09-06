package juliewww.compose.multiplatform.openai.client.datastore

import juliewww.compose.multiplatform.openai.client.Chat
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val openAiKey: String,
    val chatHistory: Set<Chat>,
) {
    companion object {
        val DEFAULT = UserPreferences(
            openAiKey = "",
            chatHistory = emptySet(),
        )
    }
}
