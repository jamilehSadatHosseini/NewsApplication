package com.example.newsapplication.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import com.example.newsapplication.ui.theme.WhiteGray

@Composable
fun NewsButton(
    text: String,
    onClickCallBack: () -> Unit
) {
    Button(
        onClick = onClickCallBack,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ), shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClickCallBack: () -> Unit
) {
    TextButton(
        onClick = onClickCallBack,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color= WhiteGray
        )
    }
}
@Composable
@Preview(showBackground = true)
fun PreviewNewsButton() {
    NewsApplicationTheme {
        // Provide a sample text and a no-op callback for the onClick event.
        NewsButton(
            text = "Sample Button",
            onClickCallBack = {}
        )
    }
}


