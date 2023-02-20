package ro.vlad.theaters_presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ro.vlad.core_ui.common_components.TabRowItem
import ro.vlad.theaters_presentation.TheatersViewModel
import ro.vlad.theaters_presentation.screens.tabs.BoxOfficeTab
import ro.vlad.theaters_presentation.screens.tabs.NowShowingTab

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NowShowingScreen(
    navigateToDetails: () -> Unit,
    viewModel: TheatersViewModel = hiltViewModel(),
) {
    val nowShowingMovies by viewModel.nowShowingState.collectAsState()
    val boxOfficeMovies by viewModel.boxOfficeState.collectAsState()
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val tabRowItems = listOf(
        TabRowItem(
            title = "Now Showing"
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LaunchedEffect(key1 = true) {
                    viewModel.getNowShowingMovies()
                }
                NowShowingTab(
                    navigateToDetails = navigateToDetails,
                    nowShowingMovies = nowShowingMovies,
                )
            }
        },
        TabRowItem(
            title ="Box Office",
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                LaunchedEffect(true) {
                    viewModel.getBoxOfficeMovies()
                }
                BoxOfficeTab(
                    navigateToDetails = navigateToDetails,
                    boxOfficeMovies = boxOfficeMovies,
                )
            }
        },
        TabRowItem(
            title =  "Coming Soon",
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "This is Coming Soon Section!")
            }
        }
    )

    Column(
//        modifier = Modifier
//        .background(MaterialTheme.colors.background)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = MaterialTheme.colors.primary
                )
            },
            contentColor = MaterialTheme.colors.background
        ) {
            tabRowItems.forEachIndexed { index, tabRowItem ->
                Tab(
                    modifier = Modifier.background(MaterialTheme.colors.primaryVariant),
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = tabRowItem.title, color = MaterialTheme.colors.secondary, fontSize = 14.sp) }
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}