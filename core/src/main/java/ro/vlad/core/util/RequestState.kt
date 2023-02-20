package ro.vlad.core.util

//sealed class RequestStateMultipleResults {
//    data class Success<T>(val data: List<T>): RequestStateMultipleResults()
//    data class Error(val message: String): RequestStateMultipleResults()
//    object Loading : RequestStateMultipleResults()
//    object Empty: RequestStateMultipleResults()
//}
//
//sealed class RequestStateSingleResult {
//    data class Success<T>(val data: T): RequestStateSingleResult()
//    data class Error(val message: String): RequestStateSingleResult()
//    object Loading : RequestStateMultipleResults()
//    object Empty: RequestStateSingleResult()
//}


sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()
}