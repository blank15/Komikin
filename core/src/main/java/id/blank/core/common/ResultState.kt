package id.blank.core.common

import androidx.annotation.StringRes

sealed class ResultState<out T> {
    class NoInternetConnection<out T> : ResultState<T>()
    class Loading<out T> : ResultState<T>()
    data class Timeout<out T>(@StringRes val errorMessage: Int) : ResultState<T>()
    data class HasData<out T>(val responseData: T) : ResultState<T>()
    data class Error(@StringRes val errorMessage: Int) : ResultState<Nothing>()
    data class ErrorInformation(val message:String) : ResultState<Nothing>()
    data class EmailNotValide<out T>(val message:String) : ResultState<T>()
}