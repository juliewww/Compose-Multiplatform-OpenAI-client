package juliewww.compose.multiplatform.openai.client.model

import com.aallam.openai.api.chat.ChatCompletionChunk
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import io.ktor.client.engine.cio.CIO
import juliewww.compose.multiplatform.openai.client.datastore.PreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.seconds


interface OpenAIRepository {
    suspend fun chatCompletion(message: String, history: String?): Flow<ChatCompletionChunk>
}

class ChatCompletionRepository(
    preferenceDataSource: PreferenceDataSource,
) : OpenAIRepository {
    private val userData: Flow<UserData> = preferenceDataSource.userData

    override suspend fun chatCompletion(
        message: String,
        history: String?,
    ): Flow<ChatCompletionChunk> {
        val config = OpenAIConfig(
            token = userData.map { it.openAiKey }.first(),
            timeout = Timeout(socket = 60.seconds),
            engine = CIO.create(),
        )
        val openAI = OpenAI(config)

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-4o-mini"),
            messages = listOf(ChatMessage(
                role = ChatRole.System,
                content = "You are a helpful assistant!" + (history?.let { "\n Chat history: \n $it" }
                    ?: ""),
            ), ChatMessage(
                role = ChatRole.User,
                content = message,
            )))
        return openAI.chatCompletions(chatCompletionRequest)
    }
}
