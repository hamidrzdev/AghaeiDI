package ir.ham.aghaeidi.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ham.aghaeidi.repository.UserRepository
import javax.inject.Inject
import kotlin.reflect.KClass

class LoginViewModel (
    private val userRepository: UserRepository
): ViewModel(){
    private val TAG = LoginViewModel::class.simpleName

    fun getData(type: TYPE):String{
        return when (type) {
            TYPE.NAME -> userRepository.getName()
            TYPE.AGE -> userRepository.getAge()
            TYPE.MOBILE -> userRepository.getMobile()
        }
    }

    fun setData(type: TYPE, value: String){
        when (type) {
            TYPE.NAME -> userRepository.setName(value)
            TYPE.AGE -> userRepository.setAge(value)
            TYPE.MOBILE -> userRepository.setMobile(value)
        }
    }

    companion object{
        enum class TYPE{
            NAME, AGE, MOBILE
        }

        fun Factory(userRepository: UserRepository):ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {

                return LoginViewModel(
                    userRepository = userRepository
                ) as T
            }
        }
    }

    init {
        Log.i(TAG, "init: ")
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared: ")
        super.onCleared()
    }
}