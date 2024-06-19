package edu.ap.myjetpackcomposecimotapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ProvideWindowInsets
import edu.ap.myjetpackcomposecimotapp.enums.LifeState
import edu.ap.myjetpackcomposecimotapp.ui.screen.SplashScreen
import edu.ap.myjetpackcomposecimotapp.ui.screen.login.LoginNavScreen
import edu.ap.myjetpackcomposecimotapp.ui.theme.MyJetpackComposeCimotAppTheme
import kotlinx.coroutines.DelicateCoroutinesApi

class LoginActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposeLoginUI()
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyJetpackComposeCimotAppTheme {
        Greeting2(name = "Android")
    }
}

@Composable
private fun ComposeLoginUI() {
    ProvideWindowInsets {
        val (appState, setAppState) = remember {
            mutableStateOf(LifeState.Splash)
        }

        when (appState) {
            LifeState.Splash -> {
                SplashScreen {
                    setAppState(LifeState.Home)
                }
            }
            LifeState.Home -> {
                LoginNavScreen()
            }
        }
    }
}