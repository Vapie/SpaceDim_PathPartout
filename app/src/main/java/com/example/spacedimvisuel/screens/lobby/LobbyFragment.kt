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

package com.example.spacedimvisuel.screens.lobby

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.spacedimvisuel.R
import com.example.spacedimvisuel.api.SocketListener
import com.example.spacedimvisuel.databinding.LobbyFragmentBinding
import com.example.spacedimvisuel.old.LobbyViewModel
import com.example.spacedimvisuel.old.LobbyViewModelFactory
import com.example.spacedimvisuel.screens.login.LoginViewModel

/**
 * Fragment where the game is played
 */
class LobbyFragment : Fragment() {

    private lateinit var viewModel: LobbyViewModel
    private lateinit var viewModelFactory: LobbyViewModelFactory
    private val  listPlayer = {"p1";"p2"}
    private val TAG = "LobbyFragment"

    private lateinit var binding: LobbyFragmentBinding
    private val args by navArgs<LobbyFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {




       // viewModel.joinRoom("FuckThisOkHttpThingyEatMyShit") not here
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.lobby_fragment,
                container,
                false
        )

        viewModelFactory = LobbyViewModelFactory(LobbyFragmentArgs.fromBundle(arguments!!).user)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LobbyViewModel::class.java)

        binding.buttonready.setOnClickListener { nextScreen() }

        binding.playerList.addView(createPlayerContainer("ad",1))
        binding.playerList.addView(createPlayerContainer("a",2))
        binding.playerList.addView(createPlayerContainer("ds",3))
        binding.playerList.addView(createPlayerContainer("gf",4))
        binding.playerList.addView(createPlayerContainer("f",5))

        //
        //viewModel = ViewModelProvider(this, viewModelFactory).get(LobbyViewModel::class.java)
        //println("REPONSE REUSSIE : " + this.viewModel.mainActivityBridge.getLoginVMTraveler())
       /* println("REPONSE REUSSIE : " + this.viewModel.mainActivityBridge.getLoginVMTraveler())*/

        val gameStarterObserver = Observer<SocketListener.EventType> { newState ->
            println("ALELOUIA");
        }
        viewModel.gameStarter.observe(viewLifecycleOwner, gameStarterObserver)

        return binding.root
    }


    private fun nextScreen() {
        val action = LobbyFragmentDirections.actionLobbyDestinationToGameDestination()
        NavHostFragment.findNavController(this).navigate(action)
    }


    private fun createPlayerContainer(nom : String ,id :Int) :ConstraintLayout{
        val inflater =LayoutInflater.from(this.context)
        val playertile = inflater.inflate(
            R.layout.user_container,
            null,
            false
        ) as ConstraintLayout
        playertile.setOnClickListener { toggle(id) }
        val name= playertile.findViewById<TextView>(R.id.textname)
        name.text = nom
        return playertile
    }

    private fun toggle(id:Int) {

    }


}