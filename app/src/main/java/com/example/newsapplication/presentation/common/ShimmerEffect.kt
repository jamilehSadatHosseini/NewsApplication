package com.example.newsapplication.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapplication.R
import com.example.newsapplication.presentation.Dimens
import com.example.newsapplication.presentation.Dimens.MediumPadding1
import com.example.newsapplication.ui.theme.NewsApplicationTheme

fun Modifier.ShimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}


@Composable
fun ArticleCardShimmerEffect() {
    Row {
        Box(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .ShimmerEffect()

        )
        Column(
            verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
                .padding(
                    Dimens.ExtraSmallPadding
                )
                .height(
                    Dimens.ArticleCardSize
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(30.dp)
                    .padding(horizontal = MediumPadding1)
                    .ShimmerEffect()
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .height(15.dp)
                        .padding(horizontal = MediumPadding1)
                        .ShimmerEffect()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardShimmerEffectPreview() {
    NewsApplicationTheme {
        ArticleCardShimmerEffect()
    }
}