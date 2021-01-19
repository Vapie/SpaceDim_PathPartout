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

package com.example.spacedimvisuel.old

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.spacedimvisuel.api.User
import com.squareup.moshi.Moshi

import com.example.spacedimvisuel.MainActivity
import com.example.spacedimvisuel.api.SocketListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener


/**
 * ViewModel containing all the logic needed to run the game
 */

class LobbyViewModel(player: User) : ViewModel() {

    val listener = SocketListener()
    var webSocket: WebSocket? = null
    val gameStarter:MutableLiveData<SocketListener.EventType> = listener.gameState

    fun joinRoom(roomName:String){
        //OKHTTP
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://spacedim.async-agency.com:8081/ws/join/" + roomName + "/1").build();

        //WBS
        webSocket = client.newWebSocket(request, listener)

        webSocket?.send("{\"type\":\"READY\", \"value\":true}");
    }
}
