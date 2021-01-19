/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.spacedimvisuel.screens.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.spacedimvisuel.R
import com.example.spacedimvisuel.databinding.LoginFragmentBinding

/**
 * Fragment where the game is played
 */
class LoginFragment : Fragment() {


    private lateinit var binding: LoginFragmentBinding
    private val TAG = "LoginFragment"
    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.login_fragment,
                container,
                false
        )
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        binding.rocketButton.setOnClickListener {
            viewModel.findUser(binding.editText.getText().toString())
            viewModel.joinRoom("FuckThisOkHttpThingyEatMyShit")
            /*goToLobby()*/
        }

        return binding.root
    }

    private fun goToLobby() {
        val action = LoginFragmentDirections.actionLoginDestinationToLobbyDestination()
        NavHostFragment.findNavController(this).navigate(action)
    }

}