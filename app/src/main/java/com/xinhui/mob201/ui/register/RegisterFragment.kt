package com.xinhui.mob201.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xinhui.mob201.ui.base.BaseFragment
import com.xinhui.mob21firebase.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUIComponents() {
        super.setupUIComponents()

        binding.run {
            tvLogin.setOnClickListener {
                navController.popBackStack()
            }

            btnRegister.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()
                val confirmPass = etPasswordAgain.text.toString()
                viewModel.register(email, pass, confirmPass)
            }
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.success.collect {
                val action = RegisterFragmentDirections.toHome()
                navController.navigate(action)
            }
        }
    }
}