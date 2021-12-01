package sp.bvantur.nestednavigation.ui.screens

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ramcosta.composedestinations.Destination
import com.ramcosta.composedestinations.NavGraphs
import com.ramcosta.composedestinations.navDestination

@Composable
fun DestinationsScaffold(
	navController: NavHostController,
	scaffoldState: ScaffoldState,
	topBar: (@Composable (Destination) -> Unit),
	drawerContent: @Composable ColumnScope.(Destination) -> Unit,
	content: @Composable (PaddingValues) -> Unit
) {
	val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
	val destination =
		currentBackStackEntryAsState?.navDestination ?: NavGraphs.root.startDestination

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = { topBar(destination) },
		content = content,
		drawerContent = { drawerContent(destination) }
	)
}