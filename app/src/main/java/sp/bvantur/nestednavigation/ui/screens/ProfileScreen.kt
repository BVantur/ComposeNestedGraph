package sp.bvantur.nestednavigation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(parentNavController: NavHostController, navController: NavHostController) {
	Box(modifier = Modifier
		.fillMaxSize()
		.background(Color.Blue)) {
		Column {
			Button(onClick = {
				parentNavController.navigate("external-details")
			}) {
				Text(text = "External details screen")
			}
			Spacer(Modifier.height(8.dp))
			Button(onClick = {
				navController.navigate("internal-details")
			}) {
				Text(text = "Inside details screen")
			}
		}
	}
}