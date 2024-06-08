package ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.repo.getIcon
import data.repo.getUpdatePrice
import data.viewmodel.FruitViewModel
import models.FruitEntity
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun DetailScreen(
    fruitViewModel: FruitViewModel = koinInject<FruitViewModel>(),
    onBackPress: () -> Unit,
    navigateTo: () -> Unit
) {
    val fruit by fruitViewModel.selectedFruit.collectAsState(FruitEntity())
    val updatedPrice by remember(
        fruit.count, fruit.price
    ) { mutableStateOf(fruit.getUpdatePrice()) }

    Scaffold(topBar = {
        Box(
            Modifier.padding(24.dp).size(54.dp)
                .background(MaterialTheme.colorScheme.primary, shape = SquircleShape).padding(2.dp)
                .background(Color.White, shape = SquircleShape)
        ) {
            IconButton(
                onClick = onBackPress, Modifier.align(Alignment.Center)
            ) {
                Icon(
                    Icons.TwoTone.ArrowBackIosNew, contentDescription = null, Modifier.size(28.dp)
                )
            }
        }
    }, bottomBar = {

        Row(Modifier.padding(horizontal = 36.dp).padding(top = 12.dp, bottom = 24.dp)) {
            Box(
                Modifier.size(72.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = SquircleShape)
                    .padding(2.dp).background(Color.White, shape = SquircleShape)
            ) {
                IconButton(
                    onClick = navigateTo, Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        Icons.TwoTone.ShoppingCart, contentDescription = null, Modifier.size(36.dp)
                    )
                }
            }
            Spacer(Modifier.width(20.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(72.dp).weight(1f, true)
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(26.dp))
                    .padding(16.dp)
            ) {
                Spacer(Modifier.width(38.dp))
                Text("Buy", style = MaterialTheme.typography.headlineSmall, color = Color.White)
                Spacer(Modifier.width(12.dp))
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    null,
                    tint = Color.White,
                )
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    null,
                    tint = Color.White.copy(alpha = 0.6f),
                )
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    null,
                    tint = Color.White.copy(alpha = 0.3f),
                )
            }
        }
    }) {
        Column(Modifier.scrollable(rememberScrollState(0), Orientation.Vertical)) {
            Image(
                painterResource(fruit.name.getIcon()),
                contentDescription = null,
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 12.dp)
            )
            Box(
                Modifier.fillMaxWidth().weight(1f, true).background(
                    Color.White, shape = RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp)
                )
            ) {
                Column(modifier = Modifier.padding(36.dp)) {
                    Spacer(Modifier.padding(top = 8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            fruit.name,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.weight(1f, true))

                        Row(
                            Modifier.padding(top = 12.dp).width(140.dp).height(50.dp).background(
                                MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(120.dp)
                            ).padding(6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (fruit.count <= 0) {
                                Text(textAlign = TextAlign.Center,
                                    text = "Add",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.fillMaxSize()
                                        .clickable { fruitViewModel.incrementCount(fruit) }
                                        .padding(9.dp))
                            } else {

                                IconButton(
                                    onClick = { fruitViewModel.decrementCount(fruit) },
                                    modifier = Modifier.background(
                                        if (fruit.count <= 0) Color.LightGray else MaterialTheme.colorScheme.primary,
                                        shape = CircleShape
                                    ).size(38.dp)
                                ) {
                                    Icon(
                                        Icons.Filled.Remove,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                AnimatedContent(targetState = fruit.count) {
                                    Text(
                                        text = "${fruit.count}",
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier.padding(
                                            horizontal = 20.dp, vertical = 4.dp
                                        )
                                    )
                                }
                                IconButton(
                                    onClick = { fruitViewModel.incrementCount(fruit) },
                                    modifier = Modifier.background(
                                        MaterialTheme.colorScheme.primary, shape = CircleShape
                                    ).size(38.dp)
                                ) {
                                    Icon(
                                        Icons.Filled.Add,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                    Spacer(Modifier.padding(top = 11.dp))
                    Text(
                        updatedPrice,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp)
                        ).padding(horizontal = 12.dp, vertical = 10.dp)
                    )

                    Spacer(Modifier.padding(top = 44.dp))
                    Text(
                        "Details",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.padding(top = 28.dp))
                    Text(
                        fruit.description,
                        lineHeight = 28.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}