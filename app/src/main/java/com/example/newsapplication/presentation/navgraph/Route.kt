package com.example.newsapplication.presentation.navgraph


sealed class Route(val route: String)
{
    object OnBoardingScreen:Route("onBoardingScreen")
    object HomeScreen:Route("homeScreen")
    object SearchScreen:Route("searchScreen")
    object BookmarkScreen:Route("bookmarkScreen")
    object DetailsScreen:Route("detailsScreen")
    object AppStartNavigation:Route("appStartNavigation")
    object NewsNavigation:Route("newsNavigation")
    object NewsNavigationScreen:Route("newNavigationScreen")
}
