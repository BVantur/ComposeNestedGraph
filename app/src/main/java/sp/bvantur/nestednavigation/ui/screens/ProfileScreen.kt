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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.ExternalDetailsScreenDestination
import com.ramcosta.composedestinations.InternalDetailsScreen2Destination
import com.ramcosta.composedestinations.InternalDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigateTo

@Destination
@Composable
fun ProfileScreen(
	// RAAMCOSTA: since this destination is the start destination of the NavHost
	// it cannot have mandatory navigation arguments. So if there is a navigation argument
	// it must have a default value or be nullable
	argument: String = "default value",
	parentNavigator: NavHostController,
	destinationsNavigator: DestinationsNavigator
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.Red)
	) {
		Column {
			Button(onClick = {
				parentNavigator.navigateTo(ExternalDetailsScreenDestination)
			}) {
				Text(text = "External details screen")
			}
			Spacer(Modifier.height(8.dp))
			Button(onClick = {
				destinationsNavigator.navigate(InternalDetailsScreenDestination)
			}) {
				Text(text = "Inside details screen")
			}
			Spacer(Modifier.height(8.dp))
			Button(onClick = {
				destinationsNavigator.navigate(InternalDetailsScreen2Destination.invoke(false))
			}) {
				Text(text = "Inside details screen2")
			}
		}
		Text(
			text = "argument = $argument",
			modifier = Modifier.align(Alignment.Center)
		)
	}
}