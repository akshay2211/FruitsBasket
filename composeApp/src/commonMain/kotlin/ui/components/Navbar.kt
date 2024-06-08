package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Bookmark
import androidx.compose.material.icons.twotone.Cottage
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Person3
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun BoxScope.Navbar() {
    Spacer(
        Modifier.height(100.dp).fillMaxWidth().align(Alignment.BottomCenter).zIndex(10f).background(
            Brush.linearGradient(
                listOf(
                    Color.Transparent, MaterialTheme.colorScheme.background
                ), start = Offset(50f, 0f), end = Offset(50f, 100f)
            )

        )
    )

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        Icons.TwoTone.Cottage, Icons.TwoTone.Favorite, Icons.TwoTone.Bookmark, Icons.TwoTone.Person3
    )
    NavigationBar(
        modifier = Modifier.fillMaxWidth().zIndex(11f).height(120.dp).align(Alignment.BottomCenter)
            .padding(horizontal = 36.dp, vertical = 12.dp).clip(RoundedCornerShape(32.dp)),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        Spacer(Modifier.width(24.dp))
        items.forEachIndexed { index, item ->
            NavigationBarItem(icon = {
                Icon(
                    item,
                    contentDescription = null,
                    tint = if (selectedItem == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(32.dp)
                )
            },

                selected = selectedItem == index, onClick = { selectedItem = index })
        }
        Spacer(Modifier.width(24.dp))
    }

}