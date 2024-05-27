package com.example.myapplication

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableFloatStateOf
//import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import kotlin.math.abs
import kotlin.math.roundToInt

private val RainbowColors = listOf(Color.Cyan, Color.Red, Color.Magenta)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    movies: List<Movie>,
    onBackPressed: () -> Unit,
) {
    var currentIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Matching"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        },
    ) { paddingValues ->
        // Получаем максимальную доступную ширину за вычетом отступов
        val maxWidth =
            with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() - 64.dp.toPx() }
        val isMoviesEmpty = movies.getOrNull(currentIndex) == null

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {

            if (isMoviesEmpty) {
                Text(
                    text = "Ничего не смогли вам подобрать",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            val brush = Brush.horizontalGradient(RainbowColors)
                            onDrawWithContent {
                                drawContent()
                                drawRect(brush, blendMode = BlendMode.SrcAtop)
                            }
                        }
                )
            }

            // Карточка на заднем плане (появляющаяся)
            movies.getOrNull(currentIndex + 1)?.let { movieBehind ->
                SwipeableMovieCard(
                    movie = movieBehind,
                    modifier = Modifier.padding(32.dp),
                    maxWidth = maxWidth,
                    isBehind = true
                )
            }

            // Активная карточка (уходящая)
            movies.getOrNull(currentIndex)?.let { movie ->
                SwipeableMovieCard(
                    movie = movie,
                    onSwipeRight = { currentIndex++ },
                    onSwipeLeft = { currentIndex++ },
                    modifier = Modifier.padding(16.dp),
                    maxWidth = maxWidth,
                    isBehind = false
                )
            }
        }
    }
}

@Composable
fun SwipeableMovieCard(
    movie: Movie,
    maxWidth: Float,  // float вместо Dp для удобства использования в расчетах
    modifier: Modifier = Modifier,
    onSwipeRight: () -> Unit = {},
    onSwipeLeft: () -> Unit = {},
    isBehind: Boolean = false,
) {
    var offsetX by remember { mutableStateOf(0f) }
    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (isBehind) 0.95f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isBehind) 0.5f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val animatedTranslationY by animateFloatAsState(
        targetValue = if (isBehind) 20f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val backgroundColor by animateColorAsState(
        targetValue = when {
            offsetX > 0 -> Color.Green.copy(alpha = minOf(1f, animatedOffsetX / maxWidth))
            offsetX < 0 -> Color.Red.copy(alpha = minOf(1f, -animatedOffsetX / maxWidth))
            else -> MaterialTheme.colorScheme.secondaryContainer
        }, label = ""
    )

    Box(
        modifier = modifier
            .offset { IntOffset(animatedOffsetX.roundToInt(), 0) }
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(16.dp)
                scaleX = animatedScale
                scaleY = animatedScale
                alpha = animatedAlpha
                translationY = animatedTranslationY
            }
            .background(backgroundColor)
            .aspectRatio(0.75f)
            .fillMaxWidth(0.9f)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                },
                onDragStopped = {
                    if (abs(offsetX) > maxWidth * 0.3) { // Обновлен условный порог к относительному размеру
                        if (offsetX > 0) onSwipeRight() else onSwipeLeft()
                        offsetX = 0f
                    } else {
                        offsetX = 0f
                    }
                }
            )
    ) {
        SubcomposeAsyncImage(
            model = movie.imageUrl,
            loading = {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        MovieTitle(
            title = movie.enName,
            subTitle = movie.ruName,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun MovieTitle(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

data class Movie(
    val id: Int,
    val enName: String,
    val ruName: String,
    val imageUrl: String,
)


@Preview(showBackground = true)
@Composable
fun PreviewMoviesScreen() {
    val sampleMovies = listOf(
        Movie(1, "Movie Title 1", "Description of movie 1", "url_to_movie_image_1"),
        Movie(1, "Movie Title 1", "Description of movie 1", "url_to_movie_image_1"),
        Movie(1, "Movie Title 1", "Description of movie 1", "url_to_movie_image_1"),
        Movie(1, "Movie Title 1", "Description of movie 1", "url_to_movie_image_1"),
    )
    MoviesScreen(
        sampleMovies,
        onBackPressed = {}
    )
}