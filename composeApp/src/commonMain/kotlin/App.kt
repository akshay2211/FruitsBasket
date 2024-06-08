import androidx.compose.runtime.Composable
import di.appModule
import di.sharedModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import ui.NavigationScreen
import ui.theme.FruitBasketTheme

@Composable
@Preview
fun App(koinInit: KoinAppDeclaration = {}) {
    KoinApplication(application = {
        koinInit()
        modules(sharedModule, *appModule)
    }) {
        // Only Light Color theme supported yet
        FruitBasketTheme(false) {
            NavigationScreen()
        }
    }
}

