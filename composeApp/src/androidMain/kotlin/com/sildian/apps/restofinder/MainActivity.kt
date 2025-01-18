package com.sildian.apps.restofinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sildian.apps.restofinder.datalayer.dataLayerModule
import com.sildian.apps.restofinder.designsystem.theme.RestoFinderTheme
import com.sildian.apps.restofinder.domainlayer.domainLayerModule
import com.sildian.apps.restofinder.uilayer.MainEntryPoint
import com.sildian.apps.restofinder.uilayer.uiLayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(
                dataLayerModule,
                domainLayerModule,
                uiLayerModule,
            )
        }
        setContent {
            RestoFinderTheme(dynamicColor = true) {
                MainEntryPoint()
            }
        }
    }
}
