package com.xgame.base.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.xgame.base.R

@Composable
fun AlertDialogCustom(
    modifier: Modifier,
    title: String,
    message: String,
    warning: String = "",
    colorBackground: Color = Color(0xFF071131),
    colorTitle: Color = Color(0xFFFFFFFF),
    colorMessage: Color = Color(0xFFF2F2F2),
    colorWarning: Color = Color(0xFFF2C94C),
    colorNegative: Color = Color(0xFFB5B5B5),
    colorPositive: Color = Color(0xFF10B9F0),
    noDimBackground: Boolean = true,
    dismissText: String = stringResource(R.string.cancel),
    onDismiss: () -> Unit,
    confirmText: String = stringResource(R.string.accept),
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false
        )
    ) {
        if (noDimBackground) {
            (LocalView.current.parent as DialogWindowProvider?)?.window?.setDimAmount(0f)
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(24.dp))
                .background(colorBackground),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(top = 20.dp),
                    color = colorTitle,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = message,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp),
                    color = colorMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = warning,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    color = colorWarning,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0x33FFFFFF))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .width(0.dp)
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable { onDismiss.invoke() }) {
                        Text(
                            text = dismissText,
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.titleMedium,
                            color = colorNegative,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .background(Color(0x33FFFFFF))
                    )
                    Box(modifier = Modifier
                        .width(0.dp)
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable { onConfirm.invoke() }) {
                        Text(
                            text = confirmText,
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.titleMedium,
                            color = colorPositive,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

