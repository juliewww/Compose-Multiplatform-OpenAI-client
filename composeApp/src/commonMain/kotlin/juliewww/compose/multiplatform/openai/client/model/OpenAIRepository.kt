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
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration.Companion.seconds


interface OpenAIRepository {
    fun chatCompletion(message: String, history: String?): Flow<ChatCompletionChunk>
}

class ChatCompletionRepository : OpenAIRepository {
    override fun chatCompletion(
        message: String,
        history: String?,
    ): Flow<ChatCompletionChunk> {
        val config = OpenAIConfig(
            token = openAIApiKey,
            timeout = Timeout(socket = 60.seconds),
            engine = CIO.create(),
        )
        val openAI = OpenAI(config)

        val chatCompletionRequest = ChatCompletionRequest(model = ModelId("gpt-3.5-turbo"),
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
