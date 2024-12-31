import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sildian.apps.restofinder.datalayer.dataLayerModule
import com.sildian.apps.restofinder.domainlayer.domainLayerModule
import com.sildian.apps.restofinder.uilayer.MainEntryPoint
import com.sildian.apps.restofinder.uilayer.uiLayerModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        printLogger()
        modules(
            dataLayerModule,
            domainLayerModule,
            uiLayerModule,
        )
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "RestoFinder",
    ) {
        MainEntryPoint()
    }
}