package juliewww.compose.multiplatform.openai.client.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val dispatchModule = module {
    single<CoroutineDispatcher> {
        Dispatchers.IO
    }
}