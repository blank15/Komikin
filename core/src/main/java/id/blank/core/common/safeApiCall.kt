package id.blank.core.common

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.blank.core.R
import id.blank.core.base.BaseResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.util.concurrent.TimeoutException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultState<T> {

    return withContext(dispatcher) {
        try {
            ResultState.HasData(apiCall.invoke())
        } catch (throwable: Throwable) {
            Log.d("error ",throwable.localizedMessage)
            if(throwable.localizedMessage.equals("HTTP 503 Service Unavailable")){
                ResultState.Error(R.string.unknown_error)
            }else{
                when (throwable) {
                    is ConnectException -> ResultState.Error(R.string.error_server_maintenance)
                    is IOException -> ResultState.NoInternetConnection()
                    is TimeoutException -> ResultState.Error(R.string.timeout)

                    is HttpException -> {
                        ResultState.ErrorInformation(getErrorMessage(throwable.response()?.errorBody()))
                    }
                    else -> ResultState.Error(R.string.unknown_error)

                }
            }
        }
    }
}

private fun getErrorMessage(e: ResponseBody?): String {
    val gson = Gson()
    var message =""
        val error =     e?.bytes()?.let { it1 -> String(it1)}
        Log.e("error ","$error")

    message = try {
        val response = gson.fromJson<BaseResponse<String>>(
            error,
            object : TypeToken<BaseResponse<String>>() {}.type)
        response.message
    }catch (e : Exception){
        e.localizedMessage
    }
    return message

}
