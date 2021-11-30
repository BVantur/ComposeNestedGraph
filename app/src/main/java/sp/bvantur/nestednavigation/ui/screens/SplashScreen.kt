package sp.bvantur.nestednavigation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(parentNavController: NavHostController) {
	Box(modifier = Modifier
		.fillMaxSize()
		.background(Color.Blue)) {
		Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
			Text("Splash screen", style = TextStyle(color = Color.White))
			Button(onClick = { parentNavController.navigate("home") }) {
				Text("To home screen")
			}
		}

	}
}