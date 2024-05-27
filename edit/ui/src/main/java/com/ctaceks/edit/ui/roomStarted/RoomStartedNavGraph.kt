package com.ctaceks.edit.ui.roomStarted

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ctaceks.core.ui.utils.daggerViewModel
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
            Movie(
                1,
                "Spider man: Miles Morales",
                "Wooow",
                "https://i.imgflip.com/7tj3h2.png?a476520"
            ),
            Movie(
                2,
                "History of 10 долгов",
                "Description of movie 1",
                "https://www.iphones.ru/wp-content/uploads/2019/07/dolgi-gde-posmotret.jpg"
            ),
            Movie(
                3,
                "Yandex Taxi",
                "Драйвер",
                "https://www.kino-teatr.ru/art/2310/20154.jpg"
            ),
            Movie(
                4,
                "Spider man",
                "Человек Паук",
                "https://img.championat.com/c/1200x900/news/big/k/f/tobi-maguajr-nazval-svoj-mem-tanec-iz-cheloveka-pauka-3-zabavnym_16718743281889649825.jpg"
            ),
        )
        MoviesScreen(
            sampleMovies,
            onEndButton,
        )

    }
}
