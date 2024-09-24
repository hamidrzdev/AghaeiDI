package ir.ham.aghaeidi

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ham.aghaeidi.repository.AccountRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val accountRepository: AccountRepository):ViewModel() {
    private val TAG = MainViewModel::class.simpleName
    fun remoteAccountName() = accountRepository.getRemoteAccountName()
    fun localAccountName() = accountRepository.getLocalAccountName()

    init {
        Log.i(TAG, ": ")
    }
}