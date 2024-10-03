package ir.ham.aghaeidi

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ham.aghaeidi.databinding.ActivityAghaeiScrollBinding
import ir.ham.aghaeidi.databinding.ItemBinding

class ScrollActivity:AppCompatActivity() {
    private val TAG = ScrollActivity::class.java.simpleName
    private lateinit var binding: ActivityAghaeiScrollBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAghaeiScrollBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = Adapter()
        adapter.data = listOfData.toMutableList()
        binding.rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rv.adapter = adapter

    }
}