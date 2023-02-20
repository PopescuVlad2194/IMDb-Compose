package ro.vlad.theaters_domain.use_case

import ro.vlad.theaters_domain.repository.NowShowingMovies
import ro.vlad.theaters_domain.repository.TheatersRepository

class GetNowShowingMovies(
    private val repository: TheatersRepository
) {

    suspend operator fun invoke(): Result<NowShowingMovies?> {
        return repository.getNowShowingMovies()
    }
}