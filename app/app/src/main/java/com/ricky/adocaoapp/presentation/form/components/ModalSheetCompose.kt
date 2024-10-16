package com.ricky.adocaoapp.presentation.form.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ricky.adocaoapp.R


@Composable
fun ModalBottomSheetCompose(
    header: String = "Escolha uma opção",
    onDismiss: () -> Unit,
    onTakePhotoClick: () -> Unit,
    onPhotoGalleryClick: () -> Unit
) {
    ModalBottomSheetContent(
        header = header,
        onDismiss = { onDismiss.invoke() },
        items = listOf(
            BottomSheetItem(
                title = stringResource(id = R.string.camera),
                icon = Icons.Default.CameraAlt,
                onClick = {
                    onTakePhotoClick.invoke()
                }
            ),
            BottomSheetItem(
                title = stringResource(id = R.string.galeria),
                icon = Icons.Default.Image,
                onClick = {
                    onPhotoGalleryClick.invoke()
                }
            ),
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetContent(
    onDismiss: () -> Unit,
    header: String = "Escolha uma opção",
    items: List<BottomSheetItem> = listOf()
) {
    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val windowInsets = if (edgeToEdgeEnabled)
        WindowInsets(0) else BottomSheetDefaults.windowInsets

    ModalBottomSheet(
        shape = RoundedCornerShape(
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
        onDismissRequest = { onDismiss.invoke() },
        sheetState = bottomSheetState,
        windowInsets = windowInsets
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                text = header,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            items.forEach { item ->
                ListItem(
                    modifier = Modifier.clickable { item.onClick() },
                    headlineContent = {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    leadingContent = {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    }
                )
            }
            Spacer(modifier = Modifier.height(46.dp))
        }
    }
}

data class BottomSheetItem(
    val title: String = "",
    val icon: ImageVector,
    val onClick: () -> Unit
)