package juliewww.compose.multiplatform.openai.client.di

import com.russhwolf.settings.Settings
import org.koin.dsl.module

val settingsModule = module {
    single<Settings> {
        Settings()
    }
}