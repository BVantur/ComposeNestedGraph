package sp.bvantur.nestednavigation.ui

import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.Destination
import com.ramcosta.composedestinations.ExternalDetailsScreenDestination
import com.ramcosta.composedestinations.HomeScreenDestination
import com.ramcosta.composedestinations.InternalDetailsScreen2Destination
import com.ramcosta.composedestinations.InternalDetailsScreenDestination
import com.ramcosta.composedestinations.NavGraph
import com.ramcosta.composedestinations.ProfileScreenDestination
import com.ramcosta.composedestinations.SplashScreenDestination
import com.ramcosta.composedestinations.navDestination

object NavGraphs {

    val parent = NavGraph(
        route = "parent",
        startDestination = SplashScreenDestination,
        destinations = listOf(
            SplashScreenDestination,
            HomeScreenDestination,
            ExternalDetailsScreenDestination
        )
    )

    val home = NavGraph(
        route = "home",
        startDestination = ProfileScreenDestination,
        destinations = listOf(
            ProfileScreenDestination,
            InternalDetailsScreenDestination,
            InternalDetailsScreen2Destination
        )
    )
}

//BONUS: USEFUL EXTENSIONS (MAYBE)

/**
 * Will return null if you use it on a [NavBackStackEntry] corresponding to
 * 'parent' nav graph!
 */
val NavBackStackEntry.homeDestination: Destination? get() = navDestination(NavGraphs.home)

/**
 * Will return null if you use it on a [NavBackStackEntry] corresponding to
 * 'home' nav graph!
 */
val NavBackStackEntry.parentDestination: Destination? get() = navDestination(NavGraphs.parent)