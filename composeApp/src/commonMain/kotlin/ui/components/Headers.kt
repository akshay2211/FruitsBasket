package ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fruitsbasket.composeapp.generated.resources.Res
import fruitsbasket.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import ui.screens.SquircleShape

@Composable
fun TopBar(count: Int, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 24.dp)
    ) {
        Box(
            Modifier.size(60.dp).background(
                MaterialTheme.colorScheme.primary, shape = SquircleShape
            ), contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                imageVector = Icons.Outlined.Person,
                null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(Modifier.width(22.dp))
        Column {
            Spacer(Modifier.height(6.dp))
            Text(
                "Welcome to", style = MaterialTheme.typography.labelLarge
            )
            Text(
                stringResource(Res.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.weight(1f, true))

        BadgedBox(badge = {
            if (count > 0) {
                Badge(Modifier.size(20.dp).offset(-10.dp, 3.dp)) {
                    val badgeNumber = "$count"
                    AnimatedContent(targetState = badgeNumber) { targetCount ->
                        // Make sure to use `targetCount`, not `count`.
                        Text(badgeNumber, modifier = Modifier.semantics {
                            contentDescription = "$badgeNumber new Items in Cart"
                        })
                    }
                }
            }
        }) {
            IconButton(onClick = onClick) {
                Icon(
                    Icons.TwoTone.ShoppingCart,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(36.dp),
                    contentDescription = null
                )
            }
        }

    }
}