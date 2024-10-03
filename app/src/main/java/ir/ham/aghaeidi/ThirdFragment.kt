package ir.ham.aghaeidi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.ham.aghaeidi.databinding.FragmentConfigurationBinding

class ThirdFragment:Fragment() {
    private val TAG = ThirdFragment::class.simpleName
    private lateinit var binding: FragmentConfigurationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfigurationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text = "Third"
    }

}