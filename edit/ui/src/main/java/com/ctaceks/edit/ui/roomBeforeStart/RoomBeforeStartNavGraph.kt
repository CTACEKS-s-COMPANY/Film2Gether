package com.ctaceks.edit.ui.roomBeforeStart

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.ctaceks.core.ui.utils.daggerViewModel
import com.ctaceks.edit.ui.roomBeforeStart.di.RoomBeforeStartUiComponentProvider
import com.example.myapplication.User
import com.example.myapplication.UsersScreen

const val roomBeforeStartRoutePattern = "roomBeforeStarted"

fun NavController.navigateToRoomBeforeStart() {
    this.navigate(
        route = roomBeforeStartRoutePattern
    ) {
        launchSingleTop = true
    }
}

/**
 * Edit navigation graph
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.roomBeforeStartScreen(
    onStartButton: () -> Unit,
) {
    composable(
        route = roomBeforeStartRoutePattern,
    ) {
        val context = LocalContext.current
        val viewModel: RoomBeforeStartViewModel = daggerViewModel {
            (context.applicationContext as RoomBeforeStartUiComponentProvider)
                .provideEditUiComponent().getViewModel()
        }

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        UsersScreen(
            users = listOf(
                User("", "Саша", imageUrl = "https://plus.unsplash.com/premium_photo-1678112180202-cd950dbe5a35?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" ),
                User("", "Петя", imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Outdoors-man-portrait_%28cropped%29.jpg/1200px-Outdoors-man-portrait_%28cropped%29.jpg" ),
                User("", "Наташа", imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaFE2a8R_5l01rlFqxz4EeRnRBLvFS6vvRWAPJL6O0zg&s" ),
                User("", "Ваня", imageUrl = "https://media.gq.com/photos/6536772ec321fd27c14688cd/3:4/w_843,h_1124,c_limit/skims%20lede%202.jpg" ),
                User("", "Стасик❤️", imageUrl = "https://m.globalnrav.ast.social/images/Ocenki/Rectors/KudzhCA.jpg" ),

            ),
            onStartMatchClicked = onStartButton
        )

    }
}
