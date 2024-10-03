package ir.ham.aghaeidi

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.ham.aghaeidi.databinding.ActivityConfugarationBinding

class ConfigurationActivity:AppCompatActivity() {
    private val TAG = ConfigurationActivity::class.simpleName
    private lateinit var binding:ActivityConfugarationBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityConfugarationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null){
            Log.d(TAG, "onCreate: savedInstanceState is not null")
        }else{
            Log.d(TAG, "onCreate: savedInstanceState is null")
            loadFragment(FirstFragment())
        }

        binding.root.setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
        }
    }

    override fun onRestart() {
        Log.i(TAG, "onRestart: ")
        super.onRestart()
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: '")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: ")
        super.onDestroy()
    }
}