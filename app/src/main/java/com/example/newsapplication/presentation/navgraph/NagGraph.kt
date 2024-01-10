package com.example.newsapplication.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.newsapplication.presentation.bookMark.BookMarkScreen
import com.example.newsapplication.presentation.bookMark.BookMarkViewModel
import com.example.newsapplication.presentation.onbording.OnBoardingScreen
import com.example.newsapplication.presentation.onbording.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }

//        navigation(
//            route = Route.NewsNavigation.route,
//            startDestination = Route.NewsNavigationScreen.route
//        ) {
//            composable(route = Route.NewsNavigationScreen.route){
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles=viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(articles = articles, navigate = {})
//
//            }
//        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ) {
            composable(route = Route.NewsNavigationScreen.route){
                val viewModel: BookMarkViewModel = hiltViewModel()
                BookMarkScreen(
                    bookMarkState = viewModel.state.value,
                    navigateUp = {})

            }
        }
    }
}
