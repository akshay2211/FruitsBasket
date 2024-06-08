package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.repo.getFormattedPrice
import data.repo.getIcon
import data.repo.getUpdatePrice
import models.FruitEntity
import org.jetbrains.compose.resources.painterResource

@Composable
fun VerticalCards(fruitEntity: FruitEntity, onClick: () -> Unit, onClick2: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(end = 12.dp).width(185.dp).height(274.dp)
            .background(Color.Blue, shape = RoundedCornerShape(26.dp)),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
        )

    ) {


        Box(Modifier.fillMaxSize()) {
            Column(Modifier.padding(12.dp)) {
                Image(
                    painterResource(fruitEntity.name.getIcon()),
                    null,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.weight(1f, true))
                Text(
                    fruitEntity.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    fruitEntity.priced, style = MaterialTheme.typography.labelMedium
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    fruitEntity.getFormattedPrice(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Box(
                Modifier.size(55.dp).background(
                    MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 26.dp)
                ).align(Alignment.BottomEnd), contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onClick2) {
                    Icon(Icons.Outlined.Add, null, tint = Color.White)
                }
            }
        }
    }

}

@Composable
fun HorizontalCards(fruitEntity: FruitEntity, onClick: () -> Unit, onClick2: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 36.dp, vertical = 4.dp).padding(bottom = 12.dp)
            .fillMaxWidth().height(124.dp)
            .background(Color.Blue, shape = RoundedCornerShape(26.dp)),
        shape = RoundedCornerShape(26.dp)

    ) {
        Box(Modifier.fillMaxSize()) {
            Row(
                Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(12.dp))

                Image(
                    painterResource(fruitEntity.name.getIcon()), null,
                )
                Spacer(Modifier.width(20.dp))

                Column(Modifier.padding(4.dp)) {
                    Text(
                        fruitEntity.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(fruitEntity.priced, style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.weight(1f, true))
                    Text(
                        fruitEntity.getFormattedPrice(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Box(
                Modifier.width(65.dp).height(55.dp).background(
                    MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 26.dp)
                ).align(Alignment.BottomEnd), contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onClick2) {
                    Icon(Icons.Outlined.Add, null, tint = Color.White)
                }
            }


        }
    }
}


@Composable
fun CartHorizontalCards(
    fruitEntity: FruitEntity,
    onClick: () -> Unit,
    increment: () -> Unit,
    decrement: () -> Unit
) {
    val updatedPrice by remember(
        fruitEntity.count,
        fruitEntity.price
    ) { mutableStateOf(fruitEntity.getUpdatePrice()) }

    Card(
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 36.dp, vertical = 4.dp).padding(bottom = 12.dp)
            .fillMaxWidth().height(134.dp)
            .background(Color.Blue, shape = RoundedCornerShape(26.dp)),
        shape = RoundedCornerShape(26.dp)

    ) {
        Box(Modifier.fillMaxSize()) {
            Row(
                Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(12.dp))

                Image(
                    painterResource(fruitEntity.name.getIcon()), null,
                )
                Spacer(Modifier.width(20.dp))

                Column(Modifier.padding(4.dp)) {
                    Text(
                        fruitEntity.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(fruitEntity.priced, style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.weight(1f, true))
                    Text(
                        updatedPrice,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Box(
                Modifier.width(65.dp).height(40.dp).background(
                    MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 26.dp)
                ).align(Alignment.BottomEnd), contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = increment) {
                    Icon(Icons.Outlined.Add, null, tint = Color.White)
                }
            }

            Text(
                text = "${fruitEntity.count}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 24.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Box(
                Modifier.width(65.dp).height(40.dp).background(
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomStart = 26.dp)
                ).align(Alignment.TopEnd), contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = decrement) {
                    Icon(Icons.Outlined.Remove, null, tint = Color.White)
                }
            }


        }
    }
}