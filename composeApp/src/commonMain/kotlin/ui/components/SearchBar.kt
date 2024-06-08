package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ui.screens.SquircleShape

@Composable
fun SearchBar() {
    Row(modifier = Modifier.padding(vertical = 12.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(70.dp).weight(1f, true)
                .background(Color.White, RoundedCornerShape(26.dp)).padding(16.dp)
        ) {
            Image(
                imageVector = Icons.Filled.Search,
                null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(36.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                "Search Items..", fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(Modifier.width(20.dp))
        Box(
            Modifier.size(72.dp).background(
                MaterialTheme.colorScheme.primary, shape = SquircleShape
            ), contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Filled.Settings,
                null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.size(36.dp).align(Alignment.Center)
            )
        }
    }
}