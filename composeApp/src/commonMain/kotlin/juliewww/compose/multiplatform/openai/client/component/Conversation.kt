package juliewww.compose.multiplatform.openai.client.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import juliewww.compose.multiplatform.openai.client.Chat
import juliewww.compose.multiplatform.openai.client.Sender
import juliewww.compose.multiplatform.openai.client.component.scrollbar.DraggableScrollbar
import juliewww.compose.multiplatform.openai.client.component.scrollbar.rememberDraggableScroller
import juliewww.compose.multiplatform.openai.client.component.scrollbar.scrollbarState

@Composable
fun Conversation(
    modifier: Modifier = Modifier,
    chatList: List<Chat> = listOf(),
) {
    val listState = rememberLazyListState()
    val scrollbarState = listState.scrollbarState(
        itemsAvailable = chatList.size,
    )
    Box(modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            itemsIndexed(chatList) { _, chat ->
                Message(
                    chat = chat,
                )
                Spacer(Modifier.size(8.dp))
            }
        }
        listState.DraggableScrollbar(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 2.dp)
                .align(Alignment.CenterEnd),
            state = scrollbarState,
            orientation = Orientation.Vertical,
            onThumbMove = listState.rememberDraggableScroller(
                itemsAvailable = chatList.size,
            ),
        )
    }
}

@Composable
fun Message(
    modifier: Modifier = Modifier,
    chat: Chat,
) {
    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = if (chat.sender == Sender.User) {
                Icons.Outlined.AccountCircle
            } else {
                Icons.Outlined.Star
            },
            contentDescription = "Sender",
            modifier = Modifier.size(24.dp),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = chat.message,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
