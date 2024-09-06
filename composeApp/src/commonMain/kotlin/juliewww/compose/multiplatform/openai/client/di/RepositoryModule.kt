package juliewww.compose.multiplatform.openai.client.di

import OfflineUserDataRepository
import UserDataRepository
import juliewww.compose.multiplatform.openai.client.model.ChatCompletionRepository
import juliewww.compose.multiplatform.openai.client.model.OpenAIRepository
import org.koin.dsl.module

val openAIRepositoryModule = module {
    single<OpenAIRepository> {
        ChatCompletionRepository(get())
    }
}

val userDataRepositoryModule = module {
    single<UserDataRepository> {
        OfflineUserDataRepository(get())
    }
}
