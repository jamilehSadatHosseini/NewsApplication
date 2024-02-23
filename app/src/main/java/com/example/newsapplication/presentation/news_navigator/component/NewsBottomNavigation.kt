package com.example.newsapplication.presentation.news_navigator

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapplication.R
import com.example.newsapplication.presentation.Dimens
import com.example.newsapplication.presentation.Dimens.IconSize
import com.example.newsapplication.ui.theme.NewsApplicationTheme

@Composable
fun NewsBottomNavigation( items:List<BottomNavigationItem>, selected:Int, onItemClicked:(Int)-> Unit) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClicked(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))
                        Text(text =item.title , style = MaterialTheme.typography.labelMedium )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                )

            )
        }
    }
}


data class BottomNavigationItem(
    @DrawableRes val icon:Int,
    val title:String
)

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
fun NewsBottomNavigation(){
NewsApplicationTheme {
    NewsBottomNavigation(items = listOf(
     BottomNavigationItem(icon = R.drawable.ic_home, title ="Home"),
     BottomNavigationItem(icon = R.drawable.ic_search, title ="Search"),
     BottomNavigationItem(icon = R.drawable.ic_bookmark, title ="Bookmark"),

    ), selected = 0, onItemClicked ={} )
}
}