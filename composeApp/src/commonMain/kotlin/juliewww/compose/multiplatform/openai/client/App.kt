package juliewww.compose.multiplatform.openai.client

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import juliewww.compose.multiplatform.openai.client.di.dispatchModule
import juliewww.compose.multiplatform.openai.client.di.openAIRepositoryModule
import juliewww.compose.multiplatform.openai.client.di.preferenceDataSourceModule
import juliewww.compose.multiplatform.openai.client.di.settingsModule
import juliewww.compose.multiplatform.openai.client.di.userDataRepositoryModule
import juliewww.compose.multiplatform.openai.client.di.viewModelModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(
            viewModelModule,
            openAIRepositoryModule,
            userDataRepositoryModule,
            dispatchModule,
            preferenceDataSourceModule,
            settingsModule,
        )
    }) {
        androidx.compose.material3.MaterialTheme {
            Surface {
                ChatScreen()
            }
        }
    }
}