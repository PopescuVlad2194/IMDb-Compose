package ro.vlad.theaters_domain.repository

import ro.vlad.theaters_domain.model.BoxOfficeMovie
import ro.vlad.theaters_domain.model.now_showing.NowShowingMovie

typealias NowShowingMovies = List<NowShowingMovie>
typealias BoxOfficeMovies = List<BoxOfficeMovie>

interface TheatersRepository {

    suspend fun getNowShowingMovies(): Result<NowShowingMovies?>

    suspend fun getBoxOfficeMovies(): Result<BoxOfficeMovies?>

//    suspend fun getComingSoonMovies(): Result<List<ComingSoonMovie>?>

}