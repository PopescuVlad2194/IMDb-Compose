package ro.vlad.core.util

import ro.vlad.core.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.API_KEY
    const val DETAIL_SCREEN_ARGUMENT_KEY = "movieId"
    const val BASE_URL = "https://imdb-api.com/"
    const val FRAGMENT_TAG = "FragmentTag"
    const val SEARCH_EXTRA_KEY = "searchKeywords"
    const val SEARCH_TIME_DELAY = 500L
}