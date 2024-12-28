import androidx.compose.ui.window.ComposeUIViewController
import com.sildian.apps.restofinder.uilayer.MainEntryPoint
import com.sildian.apps.restofinder.uilayer.uiLayerModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        printLogger()
        modules(uiLayerModule)
    }
    MainEntryPoint()
}