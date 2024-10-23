package com.ricky.adocaoapp.presentation.chat_msg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ricky.adocaoapp.domain.models.Msg
import com.ricky.adocaoapp.presentation.auth.login.components.TextFieldCompose
import com.ricky.adocaoapp.presentation.chat.ChatEvent
import com.ricky.adocaoapp.presentation.home.components.ToastError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatMsgScreen(
    state: ChatMsgState,
    onEvent: (ChatMsgEvent) -> Unit,
    navController: NavController
) {
    ToastError(error = state.error) {
        onEvent(ChatMsgEvent.ClearError)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            onEvent(ChatMsgEvent.Resume)
        }
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(12.dp),
            onClick = {
                focusManager.clearFocus()
                navController.popBackStack()
            }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                        )
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = state.recipientNome,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    if (state.isLoading) {
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        Column(
                            Modifier
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
                                ) {
                                    TextFieldCompose(
                                        modifier = Modifier.weight(6f),
                                        value = state.msg,
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                        ),
                                        ime = ImeAction.Done,
                                        onDone = {
                                            onEvent(ChatMsgEvent.SendMsg)
                                        },
                                    ) { msg ->
                                        onEvent(ChatMsgEvent.OnChangeMsg(msg))
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    IconButton(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .weight(1f)
                                            .padding(top = 4.dp),
                                        onClick = { onEvent(ChatMsgEvent.SendMsg) }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.Send,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }


                        }

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
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
        ) {
            Text(
                text = msg.content,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
private fun ChatMsgScreenPrev() {
    val context = LocalContext.current
    val navController = NavController(context)
    ChatMsgScreen(state = ChatMsgState(), navController = navController, onEvent = {})
}