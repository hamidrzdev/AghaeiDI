package ir.ham.aghaeidi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.ham.aghaeidi.databinding.ActivityMainBinding
import ir.ham.aghaeidi.login.LoginActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {

        binding.btn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.tv.text = """
            local: ${viewModel.localAccountName()}
            remote ${viewModel.remoteAccountName()}
        """.trimIndent()

    }
}