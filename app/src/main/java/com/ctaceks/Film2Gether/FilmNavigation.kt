package com.ctaceks.Film2Gether

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ctaceks.auth.domain.AuthInfoProvider
import com.ctaceks.auth.ui.AuthScreenRoutePattern
import com.ctaceks.auth.ui.authScreen
import com.ctaceks.auth.ui.navigateToAuth
import com.ctaceks.core.ui.theme.ExtendedTheme
import com.ctaceks.edit.ui.navigateToTaskEdit
import com.ctaceks.edit.ui.taskEditScreen
import com.ctaceks.tasks.ui.TasksScreenRoutePattern
import com.ctaceks.tasks.ui.navigateToTasks
import com.ctaceks.tasks.ui.tasksScreen

/**
 * Root of app navigation graph
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FilmNavigation(authProvider: AuthInfoProvider) {
    val navController = rememberAnimatedNavController()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = ExtendedTheme.colors.backPrimary
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = if (authProvider.authInfo().accessToken.isBlank()) AuthScreenRoutePattern
                else TasksScreenRoutePattern
        ) {
            authScreen(
                onSuccessAuth = navController::navigateToTasks
            )
            tasksScreen(
                onNavigateToCreateTask = navController::navigateToTaskEdit,
                onNavigateToEditTask = navController::navigateToTaskEdit,
                onSignOut = navController::navigateToAuth
            )
            taskEditScreen(
                onNavigateUp = navController::navigateUp,
                onSuccessSave = navController::navigateUp
            )
        }
    }
}
