
import juliewww.compose.multiplatform.openai.client.Chat
import juliewww.compose.multiplatform.openai.client.datastore.PreferenceDataSource
import juliewww.compose.multiplatform.openai.client.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    suspend fun setOpenAiKey(newKey: String)
    suspend fun setChatHistory(chat: Chat)
}

class OfflineUserDataRepository(
    private val preferenceDataSource: PreferenceDataSource,
) : UserDataRepository {
     override val userData: Flow<UserData> = preferenceDataSource.userData

    override suspend fun setOpenAiKey(newKey: String) = preferenceDataSource.setOpenAiKey(newKey)

    override suspend fun setChatHistory(chat: Chat) = preferenceDataSource.setChatHistory(chat)
}
