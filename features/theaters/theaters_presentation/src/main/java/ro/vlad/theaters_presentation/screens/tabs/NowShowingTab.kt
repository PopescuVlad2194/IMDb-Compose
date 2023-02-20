package ro.vlad.theaters_presentation.screens.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ro.vlad.core.R
import ro.vlad.core.util.RequestState
import ro.vlad.core_ui.theme.Typography
import ro.vlad.theaters_domain.repository.NowShowingMovies

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NowShowingTab(
    navigateToDetails: () -> Unit,
    nowShowingMovies: RequestState<NowShowingMovies>
) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        when (nowShowingMovies) {
            is RequestState.Success -> {
                nowShowingMovies.data.let { list ->
                    VerticalPager(count = list.size, state = pagerState) { position ->
                        PagerItemScreen(
                            movieImageUrl = list[position].movieImage,
                            movieTitle = list[position].movieTitle,
                            movieDetails = stringResource(
                                id = R.string.movie_details,
                                list[position].movieRuntimeMinutes,
                                list[position].movieReleaseDate
                            )
                        )
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
fun PagerItemScreen(
    movieImageUrl: String,
    movieTitle: String,
    movieDetails: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            imageModel = { movieImageUrl },
            modifier = Modifier
                .width(320.dp)
                .height(460.dp)
                .padding(top = 15.dp)
                .clip(RoundedCornerShape(20.dp)),
            loading = {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val indicator = createRef()
                    CircularProgressIndicator(
                        modifier = Modifier.constrainAs(indicator) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }
            },
            failure = {
                Text(
                    text = "Network error",
                    color = Color.Red
                )
            },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
        )
        Text(
            text = movieTitle,
            color = Color.White,
            style = Typography.h4,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = movieDetails,
            color = Color.White,
            style = Typography.body2,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}