import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sildian.apps.restofinder.uilayer.MainEntryPoint
import com.sildian.apps.restofinder.uilayer.uiLayerModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        printLogger()
        modules(uiLayerModule)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "RestoFinder",
    ) {
        MainEntryPoint()
    }
}