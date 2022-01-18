package com.example.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Git {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String?): Call<GitUser>

    data class GitUser (@SerializedName("login") val login: String?, @SerializedName("avatar_url") val ava:String?)

}