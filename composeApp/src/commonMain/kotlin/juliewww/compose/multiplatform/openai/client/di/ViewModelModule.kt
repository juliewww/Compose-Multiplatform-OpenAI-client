package juliewww.compose.multiplatform.openai.client.di

import juliewww.compose.multiplatform.openai.client.ChatViewModel
import juliewww.compose.multiplatform.openai.client.SettingsViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChatViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}
