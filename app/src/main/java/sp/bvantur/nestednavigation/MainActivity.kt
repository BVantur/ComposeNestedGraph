package sp.bvantur.nestednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sp.bvantur.nestednavigation.ui.screens.ExternalDetailsScreen
import sp.bvantur.nestednavigation.ui.screens.HomeScreen
import sp.bvantur.nestednavigation.ui.screens.SplashScreen
import sp.bvantur.nestednavigation.ui.theme.NestedNavigationTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {

			val navController: NavHostController = rememberNavController()

			NestedNavigationTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					NavHost(navController = navController, startDestination = "splash") {
						composable("splash") { SplashScreen(navController) }
						composable("home") { HomeScreen(navController) }
						composable("external-details") { ExternalDetailsScreen() }
					}
				}
			}
		}
	}
}

@Composable
fun Greeting(name: String) {
	Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	NestedNavigationTheme {
		Greeting("Android")
	}
}