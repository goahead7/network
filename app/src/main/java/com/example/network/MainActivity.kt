package com.example.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val git: Git = retrofit.create(Git::class.java)

        git.getUser("goahead7").enqueue(object : Callback<Git.GitUser> {
            override fun onFailure(call: Call<Git.GitUser>, t: Throwable) {

            }

            override fun onResponse(call: Call<Git.GitUser>, response: Response<Git.GitUser>) {
                val user: Git.GitUser? = response.body()

                Log.d("Login", "${user?.login}")
                Log.d("Avatar", "${user?.ava}")


                val imageView: ImageView = findViewById<View>(R.id.imageView) as ImageView

                Picasso.get()
                    .load(user?.ava)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);
            }
        })
    }

}