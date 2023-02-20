package ro.vlad.theaters_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ro.vlad.core.util.ConnectivityObserver
import ro.vlad.core.util.RequestState
import ro.vlad.theaters_domain.repository.BoxOfficeMovies
import ro.vlad.theaters_domain.repository.NowShowingMovies
import ro.vlad.theaters_domain.use_case.TheatersUseCases
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TheatersViewModel @Inject constructor(
    private val theatersUseCases: TheatersUseCases,
    private val networkConnectivityObserver: ConnectivityObserver
): ViewModel() {
    private var _nowShowingState: MutableStateFlow<RequestState<NowShowingMovies>> =
        MutableStateFlow(RequestState.Idle)
    val nowShowingState: StateFlow<RequestState<NowShowingMovies>> = _nowShowingState
    private var _boxOfficeState: MutableStateFlow<RequestState<BoxOfficeMovies>> =
        MutableStateFlow(RequestState.Idle)
    val boxOfficeState: StateFlow<RequestState<BoxOfficeMovies>> = _boxOfficeState

    private var networkStatus: MutableStateFlow<ConnectivityObserver.Status> =
        MutableStateFlow(ConnectivityObserver.Status.Unavailable)

    init {
        viewModelScope.launch {
            networkConnectivityObserver.observe().collect { networkStatus.value = it }
        }
    }

    fun getNowShowingMovies() = viewModelScope.launch {
        networkStatus.collect {
            if (networkStatus.value == ConnectivityObserver.Status.Available) {
                theatersUseCases
                    .getNowShowingMovies()
                    .onSuccess { moviesList ->
                        moviesList?.let { list ->
                            _nowShowingState.value = RequestState.Success(list)
                        }
                    }
                    .onFailure { throwable ->
                        when (throwable) {
                            is IOException -> _nowShowingState.value =
                                RequestState.Error(throwable)
                            else -> _nowShowingState.value =
                                RequestState.Error(throwable)
                        }
                    }
            } else {
                _nowShowingState.value = RequestState.Error(Exception("No internet connection"))
            }
        }
    }

    fun getBoxOfficeMovies() = viewModelScope.launch {
        networkStatus.collect {
            if (networkStatus.value == ConnectivityObserver.Status.Available) {
                theatersUseCases
                    .getBoxOfficeMovies()
                    .onSuccess { movieList ->
                        movieList?.let { list ->
                            _boxOfficeState.value = RequestState.Success(list)
                        }
                    }
                    .onFailure { throwable ->
                        when (throwable) {
                            is IOException -> _boxOfficeState.value =
                                RequestState.Error(throwable)
                            else -> _boxOfficeState.value =
                                RequestState.Error(throwable)
                        }
                    }
            } else {
                _boxOfficeState.value = RequestState.Error(Exception("No internet connection"))
            }
        }
    }
}