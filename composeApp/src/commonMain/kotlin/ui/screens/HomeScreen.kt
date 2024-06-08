package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.viewmodel.FruitViewModel
import fruitsbasket.composeapp.generated.resources.Res
import fruitsbasket.composeapp.generated.resources.popular_items
import fruitsbasket.composeapp.generated.resources.your_gotos
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.Screens
import ui.components.Headings
import ui.components.HorizontalCards
import ui.components.Navbar
import ui.components.SearchBar
import ui.components.TopBar
import ui.components.VerticalCards

@Composable
fun HomeScreen(
    fruitViewModel: FruitViewModel = koinInject<FruitViewModel>(), navigateTo: (Screens) -> Unit
) {
    val list = fruitViewModel.fruitList.collectAsState(emptyList()).value
    val selectedFruits = fruitViewModel.selectedFruits.collectAsState(emptyList()).value
    LaunchedEffect(list.isEmpty()) {
        fruitViewModel.insertFakeData()
    }
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = {
        SnackbarHost(snackBarHostState)
    }) {
        LaunchedEffect(selectedFruits.sumOf { it.count }) {
            if (selectedFruits.isNotEmpty()) {
                if (selectedFruits.isNotEmpty()) snackBarHostState.showSnackbar(
                    "You have added ${selectedFruits.size} types of fruits, total quantity of ${selectedFruits.sumOf { it.count }} items to cart",
                    duration = SnackbarDuration.Short
                )
            }
        }
        Box {
            LazyColumn(Modifier.padding(it).fillMaxSize()) {
                item {
                    Column(Modifier.padding(horizontal = 36.dp)) {
                        TopBar(selectedFruits.size) { navigateTo.invoke(Screens.CART) }
                        SearchBar()
                        Headings(
                            stringResource(Res.string.popular_items),
                            horizontal = 12.dp,
                            vertical = 22.dp
                        )
                    }
                }
                item {
                    LazyRow {
                        item { Spacer(Modifier.width(36.dp)) }
                        items(list) {
                            VerticalCards(it, {
                                fruitViewModel.select(it)
                                navigateTo.invoke(Screens.DETAIL)
                            }) {
                                fruitViewModel.incrementCount(it)
                            }
                        }
                    }
                }
                item {
                    Column(Modifier.padding(horizontal = 36.dp).padding(top = 12.dp)) {
                        Headings(
                            stringResource(Res.string.your_gotos),
                            horizontal = 12.dp,
                            vertical = 22.dp
                        )
                    }
                }
                items(list) {
                    HorizontalCards(it, {
                        fruitViewModel.select(it)
                        navigateTo.invoke(Screens.DETAIL)
                    }) {
                        fruitViewModel.incrementCount(it)
                    }
                }
            }
            Navbar()
        }
    }
}

val SquircleShape = GenericShape { size, _ ->
    val thirdY = size.height / 6
    val thirdX = size.width / 6
    val twoThirdX = size.width - thirdX
    val twoThirdY = size.height - thirdY
    moveTo(0.0f, size.height / 2)
    cubicTo(0.0f, thirdY, thirdX, 0.0f, size.width / 2, 0.0f)
    cubicTo(twoThirdX, 0.0f, size.width, thirdY, size.width, size.height / 2)
    cubicTo(size.width, twoThirdY, twoThirdX, size.height, size.width / 2, size.height)
    cubicTo(thirdX, size.height, 0.0f, twoThirdY, 0.0f, size.height / 2)
    close()
}