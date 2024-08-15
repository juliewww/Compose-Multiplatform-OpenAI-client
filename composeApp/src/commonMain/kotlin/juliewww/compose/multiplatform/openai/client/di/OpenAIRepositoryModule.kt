package juliewww.compose.multiplatform.openai.client.di

import juliewww.compose.multiplatform.openai.client.model.ChatCompletionRepository
import juliewww.compose.multiplatform.openai.client.model.OpenAIRepository
import org.koin.dsl.module

val openAIRepositoryModule = module {
    single<OpenAIRepository> {
        ChatCompletionRepository()
    }
}
