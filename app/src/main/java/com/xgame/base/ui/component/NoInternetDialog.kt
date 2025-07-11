package com.xgame.base.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoInternetDialog(
    showDialog: Boolean,
    onRetry: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Lỗi kết nối",
                    color = Color(0xFFD32F2F), // đỏ
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Mất kết nối mạng, vui lòng thử lại",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            },
            confirmButton = {
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1976D2) // xanh dương
                    )
                ) {
                    Text(text = "Thử lại", color = Color.White)
                }
            },
            shape = RoundedCornerShape(12.dp),
            containerColor = Color.White
        )
    }
}
