package ro.vlad.theaters_presentation.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import ro.vlad.core.R
import ro.vlad.core.util.RequestState
import ro.vlad.theaters_domain.model.BoxOfficeMovie
import ro.vlad.theaters_domain.repository.BoxOfficeMovies

@Composable
fun BoxOfficeTab(
    navigateToDetails: () -> Unit,
    boxOfficeMovies: RequestState<BoxOfficeMovies>
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {
        when (boxOfficeMovies) {
            is RequestState.Success -> {
                boxOfficeMovies.data.let { list ->
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 18.dp,
                            vertical = 18.dp
                        )
                    ) {
                        items(
                            items = list,
                            itemContent = {
                                list.forEach {
                                    MovieListItem(movie = it)
                                }
                            }
                        )
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun MovieListItem(movie: BoxOfficeMovie) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            MovieImage(
                image = movie.movieImage,
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.Top)
            ) {
                BoxOfficeTitle(title = movie.movieTitle)
                BoxOfficeWeekendEarnings(amount = movie.movieEarningsWeekend)
                BoxOfficeGross(amount = movie.movieEarningsGross)
            }
        }
    }
}

@Composable
fun MovieImage(
    image: String,
    modifier: Modifier
) {
    GlideImage(
        imageModel = { image },
        modifier = modifier
            .width(100.dp)
            .fillMaxHeight()
            .padding(
                start = 6.dp,
                top = 6.dp,
                bottom = 6.dp
            )
            .clip(RoundedCornerShape(15.dp))
    )
}

@Composable
fun BoxOfficeTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier

            .padding(
                start = 24.dp,
                top = 12.dp,
                end = 24.dp
            ),
        textAlign = TextAlign.Start,
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 2.sp,
    )
}

@Composable
fun BoxOfficeWeekendEarnings(amount: String) {
    Text(
        text = stringResource(
            id = R.string.box_office_weekend,
            amount
        ),
        modifier = Modifier.padding(
            start = 24.dp,
            top = 12.dp
        ),
        lineHeight = 6.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun BoxOfficeGross(amount: String) {
    Text(
        text = stringResource(
            id = R.string.box_office_gross,
            amount
        ),
        modifier = Modifier.padding(start = 24.dp),
        lineHeight = 6.sp,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
}