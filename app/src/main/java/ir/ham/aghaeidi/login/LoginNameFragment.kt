package ir.ham.aghaeidi.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.ham.aghaeidi.databinding.FragmentLoginNameBinding

@AndroidEntryPoint
class LoginNameFragment:Fragment() {
    private val TAG = LoginNameFragment::class.simpleName
    private lateinit var binding: FragmentLoginNameBinding
    private val viewModel: LoginViewModel by activityViewModels{LoginViewModel.Factory(userRepository = (requireActivity() as LoginActivity).userRepository)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginNameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        binding.navigate.setOnClickListener {
            (requireActivity() as LoginActivity).loadFragment(LoginAgeFragment())
        }

        binding.nameEt.setText(viewModel.getData(LoginViewModel.Companion.TYPE.NAME))

        binding.nameEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.setData(LoginViewModel.Companion.TYPE.NAME, p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}