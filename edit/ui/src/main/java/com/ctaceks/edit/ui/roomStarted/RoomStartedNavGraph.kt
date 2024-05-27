package com.ctaceks.edit.ui.roomStarted

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ctaceks.core.ui.utils.daggerViewModel
import com.ctaceks.edit.ui.roomBeforeStart.TaskEditScreen
import com.ctaceks.edit.ui.roomBeforeStart.di.RoomStartedUiComponentProvider
import com.example.myapplication.Movie
import com.example.myapplication.MoviesScreen

const val RoomStartedRoutePattern = "roomStarted"

fun NavController.navigateToRoomStarted() {
    this.navigate(
        route = RoomStartedRoutePattern
    ) {
        launchSingleTop = true
    }
}

/**
 * Edit navigation graph
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.roomStartedScreen(
    onNavigateUp: () -> Unit,
    onSuccessSave: () -> Unit,
    onEndButton: () -> Unit,
) {
    composable(
        route = RoomStartedRoutePattern,
    ) {
        val context = LocalContext.current
        val viewModel: RoomStartedViewModel = daggerViewModel {
            (context.applicationContext as RoomStartedUiComponentProvider)
                .provideRoomStartedUiComponent().getViewModel()
        }

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sampleMovies = listOf(
            Movie(1, "The Green Elephant", "Trash", "https://play-lh.googleusercontent.com/emSrXlVBEGDAkksSrA-SHX3fypI5RvDbLIk6dxkqoaeWc14rfbjVh8ugXKKjKiZPpds"),
            Movie(2, "Marvel", "Wooow", "https://i.imgflip.com/7tj3h2.png?a476520"),
            Movie(3, "History of 10 долгов", "Description of movie 1", "https://www.iphones.ru/wp-content/uploads/2019/07/dolgi-gde-posmotret.jpg"),
            Movie(4, "Yandex Taxi", "Description of movie 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTo8s27Ps7jQ4XyX49h1vOHnYo386Kcv9IQc87qYSaO1g&s"),
        )
        MoviesScreen(
            sampleMovies,
            onEndButton,
        )

    }
}
