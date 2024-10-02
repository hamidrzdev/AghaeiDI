package ir.ham.aghaeidi.old

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ham.aghaeidi.repository.AccountRepository
import java.io.File
import java.io.Serializable
import javax.inject.Inject
import javax.sql.DataSource

@HiltViewModel
class MainViewModel @Inject constructor(private val accountRepository: AccountRepository):ViewModel() {
    private val TAG = MainViewModel::class.simpleName
    fun remoteAccountName() = accountRepository.getRemoteAccountName()
    fun localAccountName() = accountRepository.getLocalAccountName()

    init {
        Log.i(TAG, ": ")
    }
}

sealed class First : Parent(){
    data class Adc(val d:Int): First()
}

open class Parent()


sealed interface ResponseState<T>{
    data class Success<T>(val data:T, val responseCode:Int): ResponseState<T>
    data class Error<T>(val error:Exception, val responseCode:Int): ResponseState<T>
}

fun provideData(): ResponseState<String> {
    val response = ResponseState.Success("", 0)
    return response

}


fun handleResponse(){
    val response: ResponseState<String> = provideData()


    when (response) {
        is ResponseState.Success -> {
            response.data
            response.responseCode
        }
        is ResponseState.Error -> {

        }
    }
}










// Create a sealed interface
sealed interface Error

// Create a sealed class that implements sealed interface Error
sealed class IOError(): Error

// Define subclasses that extend sealed class 'IOError'
class FileReadError(val file: File): IOError(), Serializable
class DatabaseError(val source: DataSource): IOError()

// Create a singleton object implementing the 'Error' sealed interface
object RuntimeError : Error





interface UserService{
    fun printUser():String
}
class UserServiceImpl: UserService {
    override fun printUser():String {
        return "USER"
    }
}

interface MessageService{
    fun printMessage():String
}
class MessageServiceImpl: MessageService {
    override fun printMessage():String {
        return "MESSAGE"
    }
}































