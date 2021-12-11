package sp.bvantur.nestednavigation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.InternalDetailsScreen2Destination
import com.ramcosta.composedestinations.InternalDetailsScreenDestination
import com.ramcosta.composedestinations.ProfileScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.navigation.navigateTo
import kotlinx.coroutines.launch
import sp.bvantur.nestednavigation.ui.NavGraphs
import sp.bvantur.nestednavigation.ui.homeDestination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
// RAAMCOSTA: DestinationsNavigator is meant for navigation "from" a specific screen, in this cases
// we can use NavHostController directly. Maybe I'll improve this next version
fun HomeScreen(parentNavController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController: NavHostController = rememberNavController()

    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination =
        currentBackStackEntryAsState?.homeDestination ?: NavGraphs.home.startDestination


    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerRow("Profile", destination == ProfileScreenDestination) {
                navController.navigateTo(ProfileScreenDestination(argument = "from drawer"))
                scope.launch { scaffoldState.drawerState.close() }
            }
            DrawerRow("Internal details 1", destination == InternalDetailsScreenDestination) {
                // RAAMCOSTA -> you can use both normal navigate method and add ".route" to the destination
                // or you can use navigateTo extension function
                // navigateTo is safer because it won't let you use a route that is not resolved, i.e
                // if the destination has navigation arguments, then to get a Routed object, you need to invoke
                // it passing the arguments.
                navController.navigateTo(InternalDetailsScreenDestination)
                scope.launch { scaffoldState.drawerState.close() }
            }
            DrawerRow("Internal details 2", destination == InternalDetailsScreen2Destination) {
                navController.navigateTo(InternalDetailsScreen2Destination(someArg = true))
                scope.launch { scaffoldState.drawerState.close() }
            }
        },
        topBar = {
            AppBar {
                scope.launch { scaffoldState.drawerState.open() }
            }
        },
    ) {
        DestinationsNavHost(
            navGraph = NavGraphs.home,
            navController = navController,
        ) {
            // TODO 2# how can we add argument to ProfileScreenDestination?
            // RAAMCOSTA: you mean a navigation argument? like this: (check also the composable)
            composable(ProfileScreenDestination) { args, entry ->
                ProfileScreen(
                    argument = args.argument,
                    parentNavigator = parentNavController,
                    destinationsNavigator = DestinationsNavController(navController, entry)
                )
            }
            // TODO 1# is expected to add subscreens for each composable that needs to be shown in this NavHost
            // RAAMCOSTA: I'm not sure what you mean, but hopefully, my changes helped you here also?
//			composable(InternalDetailsScreenDestination) {
//				childNavigator1 = DestinationsNavController(navController, it)
//				InternalDetailsScreen(
//					parentNavigator = parentDestinationsNavigator,
//					destinationsNavigator = childNavigator1
//				)
//			}
//			composable(InternalDetailsScreen2Destination) {
//				childNavigator1 = DestinationsNavController(navController, it)
//				InternalDetailsScreen2(
//					parentNavigator = parentDestinationsNavigator,
//					destinationsNavigator = childNavigator1
//				)
//			}
        }
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