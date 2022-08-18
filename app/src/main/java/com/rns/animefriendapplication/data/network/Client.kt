package com.rns.animefriendapplication.data.network

import com.google.gson.Gson
import com.rns.animefriendapplication.data.Status
import com.rns.animefriendapplication.utils.Constants
import com.rns.animefriendapplication.data.response.AnimeResponse
import okhttp3.*

class Client {
    private val okHttpClient = OkHttpClient()

    fun getAnimeResponse(): Status<AnimeResponse> {
        val request = Request.Builder().url(Constants.URL).build()
        val response = okHttpClient.newCall(request).execute()
        return if (response.isSuccessful) {

            val result = Gson().fromJson(response.body!!.string(), AnimeResponse::class.java)
            Status.Success(result)
        } else {
            Status.Error(response.message)
        }
    }
}