package com.xinhui.mob201.ui.login

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xinhui.mob201.ui.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.xinhui.mob201.core.utils.NativeUtils
import com.xinhui.mob21firebase.R
import com.xinhui.mob21firebase.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    private lateinit var signInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        Log.d("debugging", "Result code: ${it.resultCode}")
        if(it.resultCode == RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val acc = task.result
                Log.d("debugging", acc.displayName.toString())
            }catch (e:Exception){
                e.printStackTrace()
                Log.d("debugging", e.message.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUIComponents() {
        super.setupUIComponents()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(NativeUtils.provideWebClientId())
            .requestEmail()
            .build()

        signInClient = GoogleSignIn.getClient(requireActivity(),gso)

        binding.run {
            tvRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginToRegister()
                navController.navigate(action)
            }

            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()
                viewModel.login(email, pass)
            }

            btnSignInWithGoogle.setOnClickListener {
                googleSignInLauncher.launch(signInClient.signInIntent)
            }
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.success.collect {
                val action = LoginFragmentDirections.toHome()
                navController.navigate(action)
            }
        }

        lifecycleScope.launch {
            viewModel.greet.collect{
                binding.tvGreet.text = it
            }
        }
    }
}