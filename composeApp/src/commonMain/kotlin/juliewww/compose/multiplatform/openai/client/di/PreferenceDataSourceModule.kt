package juliewww.compose.multiplatform.openai.client.di

import juliewww.compose.multiplatform.openai.client.datastore.PreferenceDataSource
import org.koin.dsl.module

val preferenceDataSourceModule = module {
    single<PreferenceDataSource> {
        PreferenceDataSource(get(), get())
    }
}