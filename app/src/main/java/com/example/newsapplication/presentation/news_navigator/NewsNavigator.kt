package com.example.newsapplication.presentation.news_navigator

import SearchScreen
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapplication.R
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.presentation.articleDetails.DetailEvent
import com.example.newsapplication.presentation.articleDetails.DetailScreen
import com.example.newsapplication.presentation.articleDetails.DetailViewModel
import com.example.newsapplication.presentation.bookMark.BookMarkScreen
import com.example.newsapplication.presentation.bookMark.BookMarkViewModel
import com.example.newsapplication.presentation.home.HomeScreen
import com.example.newsapplication.presentation.home.HomeViewModel
import com.example.newsapplication.presentation.navgraph.Route
import com.example.newsapplication.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, title = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, title = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, title = "Bookmark"),

            )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    val isBottomNavigatorVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    selectedItem = remember(key1 = backStackState) {

        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomNavigatorVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClicked = { index ->
                        when (index) {
                            0 -> navigateToTap(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTap(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTap(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) { it ->
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewMode: HomeViewModel = hiltViewModel()
                val articles = viewMode.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigateToSearch = {
                    navigateToTap(
                        navController = navController, Route.SearchScreen.route
                    )
                }, navigateToDetail = { article ->
                    navigateToDetail(
                        navController = navController, article = article
                    )
                })
            }

            composable(route = Route.SearchScreen.route) {
                val viewMode: SearchViewModel = hiltViewModel()
                val state = viewMode.state.value
                SearchScreen(state = state, event = viewMode::onEvent, navigate = {
                    navigateToDetail(
                        navController = navController, article = it
                    )
                })
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                if(viewModel.sideEffect !=null)
                {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->
                        DetailScreen(article = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            })
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookMarkScreen(bookMarkState = state, navigateToDetail = { article ->
                    navigateToDetail(
                        navController = navController, article = article
                    )
                })
            }

        }
    }
}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route = route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true

        }
    }
}

fun navigateToDetail(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailsScreen.route)
}