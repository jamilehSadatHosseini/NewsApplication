package com.example.newsapplication.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.example.newsapplication.domain.model.Article
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.newsapplication.R
import com.example.newsapplication.presentation.Dimens.MediumPadding1
import com.example.newsapplication.presentation.common.ArticleList
import com.example.newsapplication.presentation.common.SearchBar
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigateToSearch: () -> Unit,navigateToDetail:(Article)->Unit) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.height(MediumPadding1)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick =
                navigateToSearch ,
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color= colorResource(id = R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(modifier=Modifier.padding(), articles = articles, onClick ={
            navigateToDetail(it)
        } )

    }
}

