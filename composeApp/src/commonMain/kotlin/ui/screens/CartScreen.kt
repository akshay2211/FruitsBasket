package ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.repo.getUpdatedPrice
import data.viewmodel.FruitViewModel
import org.koin.compose.koinInject
import ui.components.CartHorizontalCards

@Composable
fun CartScreen(
    fruitViewModel: FruitViewModel = koinInject<FruitViewModel>(),
    onBackPress: () -> Unit,
    navigateTo: () -> Unit
) {
    val selectedFruits by fruitViewModel.selectedFruits.collectAsState(emptyList())
    Scaffold(topBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.padding(24.dp).size(54.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = SquircleShape)
                    .padding(2.dp).background(Color.White, shape = SquircleShape)
            ) {
                IconButton(
                    onClick = onBackPress, Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        Icons.TwoTone.ArrowBackIosNew,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            Text(
                "Cart",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }, bottomBar = {
        Column(
            Modifier.fillMaxWidth().background(
                Color.White, shape = RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp)
            ).padding(horizontal = 36.dp).padding(top = 12.dp, bottom = 24.dp)
        ) {

            Spacer(Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Total",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.weight(1f, true))
                Text(
                    selectedFruits.getUpdatedPrice(),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.background(
                        MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp)
                    ).padding(horizontal = 12.dp, vertical = 10.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(72.dp).fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(26.dp))
                    .padding(16.dp)
            ) {
                Spacer(Modifier.width(38.dp))
                Text(
                    "Checkout", style = MaterialTheme.typography.headlineSmall, color = Color.White
                )
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

        if (selectedFruits.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        imageVector = Icons.Filled.RemoveShoppingCart,
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Cart is Empty",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(selectedFruits) {
                    CartHorizontalCards(it, {
                        fruitViewModel.select(it)
                        navigateTo.invoke()
                    }, {
                        fruitViewModel.incrementCount(it)
                    }, {
                        fruitViewModel.decrementCount(it)
                    })
                }
            }
        }
    }
}