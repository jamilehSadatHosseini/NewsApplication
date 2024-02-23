import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.presentation.Dimens.MediumPadding1
import com.example.newsapplication.presentation.common.ArticleList
import com.example.newsapplication.presentation.common.SearchBar
import com.example.newsapplication.presentation.search.SearchEvent
import com.example.newsapplication.presentation.search.SearchState

@Composable
fun SearchScreen(
    state:SearchState,
    event: (SearchEvent)->Unit,
    navigate:(Article)->Unit
){
Column(modifier = Modifier
    .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    .statusBarsPadding()
    .fillMaxSize()
) {
    SearchBar(text = state.searchQuery, readOnly = false, onValueChange ={ event(SearchEvent.UpdateSearchQuery(it))},
        onSearch = {
        event(SearchEvent.SearchNews)
    } )

   Spacer(modifier = Modifier.height(MediumPadding1))
    state.article?.let {it->
        val articles=it.collectAsLazyPagingItems()
        ArticleList( articles = articles, onClick = {
            navigate(it)
        })
    }
}
}