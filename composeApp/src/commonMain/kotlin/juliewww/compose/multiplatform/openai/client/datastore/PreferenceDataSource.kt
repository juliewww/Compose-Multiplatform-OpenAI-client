package juliewww.compose.multiplatform.openai.client.datastore

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import juliewww.compose.multiplatform.openai.client.Chat
import juliewww.compose.multiplatform.openai.client.model.UserData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

private const val USER_DATA_KEY = "userData"

class PreferenceDataSource(
    private val settings: Settings,
    private val dispatcher: CoroutineDispatcher,
) {
    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    private val _userData = MutableStateFlow(
        settings.decodeValue(
            key = USER_DATA_KEY,
            serializer = UserPreferences.serializer(),
            defaultValue = settings.decodeValueOrNull(
                key = USER_DATA_KEY,
                serializer = UserPreferences.serializer(),
            ) ?: UserPreferences.DEFAULT,
        ),
    )

    val userData: Flow<UserData> = _userData.map {
        UserData(
            openAiKey = it.openAiKey,
            chatHistory = it.chatHistory,
        )
    }

    suspend fun setOpenAiKey(openAiKey: String) = withContext(dispatcher) {
        val preference = settings.getUserPreference()
            .copy(openAiKey = openAiKey)
        settings.putUserPreference(preference)
        _userData.value = preference
    }

    suspend fun setChatHistory(chat: Chat) = withContext(dispatcher) {
        val preference = settings.getUserPreference()
            .copy(chatHistory = settings.getUserPreference().chatHistory + chat)
        settings.putUserPreference(preference)
        _userData.value = preference
    }
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
private fun Settings.putUserPreference(preference: UserPreferences) {
    encodeValue(
        key = USER_DATA_KEY,
        serializer = UserPreferences.serializer(),
        value = preference,
    )
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
private fun Settings.getUserPreference(): UserPreferences {
    return decodeValue(
        key = USER_DATA_KEY,
        serializer = UserPreferences.serializer(),
        defaultValue = UserPreferences.DEFAULT,
    )
}
