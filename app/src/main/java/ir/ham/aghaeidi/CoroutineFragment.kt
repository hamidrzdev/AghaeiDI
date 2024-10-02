package ir.ham.aghaeidi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import ir.ham.aghaeidi.databinding.FragmentCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineFragment:Fragment() {
    private val TAG = CoroutineFragment::class.simpleName
    private lateinit var binding: FragmentCoroutineBinding

    private val counterLiveData = MediatorLiveData<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoroutineBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        produceLiveDataAndConsume()

    }


    private fun produceLiveDataAndConsume(){
        CoroutineScope(Dispatchers.Main).launch {
            repeat(Int.MAX_VALUE){
                delay(1000)
                counterLiveData.postValue(it)
            }
        }

    }
}