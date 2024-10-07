package ir.ham.aghaeidi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ir.ham.aghaeidi.databinding.FragmentConfigurationBinding

class FirstFragment:Fragment() {
    private val TAG = FirstFragment::class.simpleName
    private lateinit var binding: FragmentConfigurationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfigurationBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = "FIRST ${requireArguments().getString("number")}"
        binding.navigateBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,SecondFragment())
                .addToBackStack(null)
                .commit()

        }
    }



    companion object {
        fun newInstance(number: String):FirstFragment {
            val args = Bundle()
            args.putString("number", number)
            val fragment = FirstFragment()
            fragment.arguments = args
            return fragment
        }
    }

    fun getNumber() = requireArguments().getString("number")
}