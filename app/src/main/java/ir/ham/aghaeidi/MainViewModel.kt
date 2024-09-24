package ir.ham.aghaeidi

import android.util.Log
import androidx.lifecycle.ViewModel
import ir.ham.aghaeidi.repository.UserRepository

class MainViewModel():ViewModel() {
    private val TAG = MainViewModel::class.simpleName
    val remoteUsername = "userRepository.getRemoteUserName()"
    val localUsername = "userRepository.getLocalUserName()"

    init {
        Log.i(TAG, ": ")
    }
}