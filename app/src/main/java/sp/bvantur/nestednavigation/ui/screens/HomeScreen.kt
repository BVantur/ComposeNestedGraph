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
import com.ramcosta.composedestinations.navDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import sp.bvantur.nestednavigation.ui.NavGraphs

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(parentDestinationsNavigator: DestinationsNavigator) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController: NavHostController = rememberNavController()

    lateinit var childNavigator: DestinationsNavController

    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination =
        currentBackStackEntryAsState?.navDestination(NavGraphs.home) ?: NavGraphs.home.startDestination


    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerRow("Profile", destination == ProfileScreenDestination) {
                childNavigator.navigate(ProfileScreenDestination)
                // TODO 1# currently navigation works only for the first time, afterwards it works only if we set onlyIfResumed property to false
//				childNavigator1.navigate(ProfileScreenDestination, false)
                scope.launch { scaffoldState.drawerState.close() }
            }
            DrawerRow("Internal details 1", destination == InternalDetailsScreenDestination) {
                childNavigator.navigate(InternalDetailsScreenDestination)
                // TODO 1# currently navigation works only for the first time, afterwards it works only if we set onlyIfResumed property to false
//				childNavigator1.navigate(InternalDetailsScreenDestination, false)
                scope.launch { scaffoldState.drawerState.close() }
            }
            DrawerRow("Internal details 2", destination == InternalDetailsScreen2Destination) {
                childNavigator.navigate(InternalDetailsScreen2Destination.invoke(true))
                // TODO 1# currently navigation works only for the first time, afterwards it works only if we set onlyIfResumed property to false
//				childNavigator1.navigate(InternalDetailsScreen2Destination, false)
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
            navController = navController
        ) {
            // TODO 2# how can we add argument to ProfileScreenDestination?
            composable(ProfileScreenDestination) {
                childNavigator = DestinationsNavController(navController, it)
                ProfileScreen(
                    parentNavigator = parentDestinationsNavigator,
                    destinationsNavigator = childNavigator
                )
            }
            // TODO 1# is expected to add subscreens for each composable that needs to be shown in this NavHost
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