package sp.bvantur.nestednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.NavGraphs
import com.ramcosta.composedestinations.allDestinations
import com.ramcosta.composedestinations.rememberDestinationsNavController
import kotlinx.coroutines.launch
import sp.bvantur.nestednavigation.ui.screens.DestinationsScaffold
import sp.bvantur.nestednavigation.ui.theme.NestedNavigationTheme

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {

			val scaffoldState = rememberScaffoldState()
			val scope = rememberCoroutineScope()
			val navController = rememberDestinationsNavController()

			NestedNavigationTheme {
				// A surface container using the 'background' color from the theme
				DestinationsScaffold(
					scaffoldState = scaffoldState,
					navController = navController,
					drawerContent = {
						DrawerRow("Profile", true) {
							scope.launch { scaffoldState.drawerState.close() }
						}
					},
					topBar = { destination ->
						if (NavGraphs.profile.allDestinations.contains(destination)) {
							AppBar {
								scope.launch { scaffoldState.drawerState.open() }
							}
						}
					},
				) {
					DestinationsNavHost(
						navController = navController,
						startDestination = NavGraphs.root.startDestination
					)
				}
			}
		}
	}
}

@Composable
private fun AppBar(
	onDrawerClick: () -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(60.dp)
			.background(MaterialTheme.colorScheme.primary)
	) {
		IconButton(
			onClick = onDrawerClick,
			modifier = Modifier.align(Alignment.CenterStart)
		) {
			Icon(
				imageVector = Icons.Outlined.Menu,
				tint = Color.White,
				contentDescription = "menu"
			)
		}
		Text(
			text = "TEST",
			modifier = Modifier.align(Alignment.Center),
			color = Color.White
		)
	}
}

@Composable
fun DrawerRow(title: String, selected: Boolean, onClick: () -> Unit) {
	val background =
		if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) else Color.Transparent
	val textColor =
		if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface

	Text(
		color = textColor,
		text = title,
		modifier = Modifier
			.clickable(onClick = onClick)
			.background(background)
			.fillMaxWidth()
			.padding(16.dp)
	)
}