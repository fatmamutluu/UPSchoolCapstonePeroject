
package com.example.upshoolcapstoneproject.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.upshoolcapstoneproject.R
import com.example.upshoolcapstoneproject.common.viewBinding
import com.example.upshoolcapstoneproject.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)

    private val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnSignIn.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                viewModel.checkInfo(email,password)
            }
            tvSignInUp.setOnClickListener {
                findNavController().navigate(R.id.signInToSıgnUp)
            }
        }
        observeSignIn()
    }

    private fun observeSignIn() = with(binding){
        viewModel.signInState.observe(viewLifecycleOwner) {state->
            when(state){
                SignInState.SuccessState -> {
                    findNavController().navigate(R.id.signInToMainGraph)
                }


                else -> {}
            }
        }
    }
}
