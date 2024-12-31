import androidx.compose.ui.window.ComposeUIViewController
import com.sildian.apps.restofinder.datalayer.dataLayerModule
import com.sildian.apps.restofinder.domainlayer.domainLayerModule
import com.sildian.apps.restofinder.uilayer.MainEntryPoint
import com.sildian.apps.restofinder.uilayer.uiLayerModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        printLogger()
        modules(
            dataLayerModule,
            domainLayerModule,
            uiLayerModule,
        )
    }
    MainEntryPoint()
}