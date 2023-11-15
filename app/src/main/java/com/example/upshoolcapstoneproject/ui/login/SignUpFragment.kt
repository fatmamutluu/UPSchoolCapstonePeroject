
package com.example.upshoolcapstoneproject.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.upshoolcapstoneproject.R
import com.example.upshoolcapstoneproject.common.viewBinding
import com.example.upshoolcapstoneproject.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSignIn.setOnClickListener {
                val email = etEmailUp.text.toString()
                val password = etPasswordUp.text.toString()

                viewModel.checkInfo(email, password)
            }

            tvSignIn.setOnClickListener {
                findNavController().navigate(R.id.signUpToSıgnIn)
            }
        }
        observeSignUp()
    }

    private fun observeSignUp() {
        viewModel.signUpState.observe(viewLifecycleOwner) { state ->
            when (state) {

                SignUpState.SuccessState -> {
                    findNavController().navigate(R.id.signUpToMainGraph)
                }

                else -> { }
            }
        }
    }
}
