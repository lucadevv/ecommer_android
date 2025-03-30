package com.example.shopping_app.features

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.shopping_app.core.theme.Shopping_appTheme
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold { innerPadding ->
        Body(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        )

    }
}

@Composable
private fun Body(modifier: Modifier) {
    var searchText by remember { mutableStateOf("") }

    val listImage = listOf(
        "https://cdn.pixabay.com/photo/2021/01/08/10/57/shopping-5899638_1280.jpg",
        "https://cdn.pixabay.com/photo/2018/09/14/03/50/center-3676219_1280.jpg",
        "https://cdn.pixabay.com/photo/2025/03/19/15/04/lotus-9480927_1280.jpg",
        "https://cdn.pixabay.com/photo/2022/10/11/16/43/french-bulldog-7514725_1280.jpg"
    )
    val infinitePageCount = Int.MAX_VALUE
    val startPage = infinitePageCount / 2
    val pagerState = rememberPagerState(initialPage = startPage, pageCount = { infinitePageCount })
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // Cambia cada 3 segundos
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }
    Column(
        modifier = modifier
    ) {

        TextField(
            value = "Search",
            onValueChange = { newText ->
                searchText = newText
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        HorizontalPager(
            state = pagerState,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                snapAnimationSpec = tween(durationMillis = 600)
            )
        ) { page ->
            val actualIndex = page % listImage.size
            val item = listImage[actualIndex]
            Card(
                Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // Efectos de transformación
                        alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                        scaleY = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

                        // Efecto de profundidad (3D)
                        val rotationX = lerp(-30f, 0f, 1f - pageOffset)

                        // Efecto de desplazamiento
                        translationX = pageOffset * 100f
                    }
            ) {
                AsyncImage(
                    model = item,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

            }
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                "Sale and Promotions",
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                "See All",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                ),
            )
        }
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primary)

        ) { }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    Shopping_appTheme {
        val navController = rememberNavController();
        HomeScreen(navController = navController)
    }
}