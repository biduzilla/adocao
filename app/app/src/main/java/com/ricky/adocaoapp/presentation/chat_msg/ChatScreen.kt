package com.ricky.adocaoapp.presentation.chat_msg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.domain.models.Msg
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    state: ChatState,
    onEvent: (ChatEvent) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.clip(
            RoundedCornerShape(
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            )
        ),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = state.recipientNome,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            })
    }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.msgs) { item ->
                    ChatBubble(msg = item)
                }
            }
            Surface(
                color = MaterialTheme.colorScheme.primary
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextFieldCompose(
                        modifier = Modifier.weight(5f),
                        value = state.msg,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        ime = ImeAction.Done,
                        onDone = {
                            onEvent(ChatEvent.SendMsg)
                        },
                    ) { msg ->
                        onEvent(ChatEvent.OnChangeMsg(msg))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .weight(1f),
                        onClick = { onEvent(ChatEvent.SendMsg) }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    }
                }
            }


        }

    }
}

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    msg: Msg
) {
    Row(
        horizontalArrangement = if (msg.isFromMe) Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (msg.isFromMe) 48f else 0f,
                        bottomEnd = if (msg.isFromMe) 0f else 48f
                    )
                )
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Text(text = msg.content)
        }
    }
}

@Preview
@Composable
private fun ChatMsgScreenPrev() {
    ChatScreen(state = ChatState()) {

    }
}