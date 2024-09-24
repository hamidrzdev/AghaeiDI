package ir.ham.aghaeidi.login

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ir.ham.aghaeidi.databinding.ActivityLoginBinding
import ir.ham.aghaeidi.repository.UserRepository
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity:AppCompatActivity() {
    private val TAG = LoginActivity::class.simpleName
    private lateinit var binding:ActivityLoginBinding

    @Inject
    lateinit var userRepository: UserRepository

    private val viewModel:LoginViewModel by viewModels { LoginViewModel.Factory(userRepository = userRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        loadFragment(fragment = LoginNameFragment(), addToBackStack = false)
    }

    fun loadFragment(fragment: Fragment, addToBackStack: Boolean = true){
        Log.i(TAG, "loadFragment: fragment: ${fragment::class.simpleName}")
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)

        if (addToBackStack)
            transaction.addToBackStack(null)

        transaction.commit()
    }
}