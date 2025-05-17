package com.benzo.benzomobile.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThemeSelectionScreen(
    onBackClick: () -> Unit,
    selectedTheme: String,
    onThemeSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад"
                )
            }
        }

        Text("Выберите тему", color = Color.Black, style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ThemeCircleOption("Светлая", Color(0xFFF1F1F1), selectedTheme, onThemeSelected)
            Spacer(modifier = Modifier.width(24.dp))
            ThemeCircleOption("Тёмная", Color(0xFF1C1C1C), selectedTheme, onThemeSelected)
            Spacer(modifier = Modifier.width(24.dp))
            ThemeCircleOption("Системная", Color(0xFF888888), selectedTheme, onThemeSelected)
        }
    }
}



@Composable
fun ThemeCircleOption(
    themeName: String,
    color: Color,
    selectedTheme: String,
    onSelected: (String) -> Unit
) {
    val isSelected = selectedTheme == themeName
    val interactionSource = remember { MutableInteractionSource() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .background(color, shape = CircleShape)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onSelected(themeName) },
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "selected",
                    tint = Color.Green
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = themeName,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

