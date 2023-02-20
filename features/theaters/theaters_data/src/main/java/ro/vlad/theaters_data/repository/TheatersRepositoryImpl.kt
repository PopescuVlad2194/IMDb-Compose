package ro.vlad.theaters_data.repository

import ro.vlad.core.remote.IMDbApi
import ro.vlad.theaters_data.mapper.toBoxOfficeMovie
import ro.vlad.theaters_data.mapper.toNowShowingMovie
import ro.vlad.theaters_domain.repository.BoxOfficeMovies
import ro.vlad.theaters_domain.repository.NowShowingMovies
import ro.vlad.theaters_domain.repository.TheatersRepository

class TheatersRepositoryImpl(
    private val api: IMDbApi
) : TheatersRepository {
    override suspend fun getNowShowingMovies(): Result<NowShowingMovies?> {
        return try {
            val nowShowingDto = api.getNowShowing()
            Result.success(
                nowShowingDto.items?.map { it.toNowShowingMovie() }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBoxOfficeMovies(): Result<BoxOfficeMovies?> {
        return try {
            val boxOfficeDto = api.getBoxOffice()
            Result.success(
                boxOfficeDto.items?.map { it.toBoxOfficeMovie() }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

//    override suspend fun getComingSoonMovies(): Result<List<ComingSoonMovie>?> {
//        return try {
//            val comingSoonDto = api.getComingSoon()
//            Result.success(
//                comingSoonDto.items?.map { it.toComingSoonMovie() }
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.failure(e)
//        }
//    }
}