package com.example.a17maynewsappapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a17maynewsappapi.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        passData()
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)

    }

    private fun passData() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val key = URLEncoder.encode("yHAOq57AkUOboW3K7g-1JHcqqofPWeqduwN0kAEZYo5ydDUf","UTF-8")
        /*val search= URLEncoder.encode("twitter","UTF-8")*/
        val request = StringRequest(
            Request.Method.GET, "https://api.currentsapi.services/v1/latest-news?apiKey=$key", { apiResponse: String ->
                val typeToken = object : TypeToken<LatestNews>() {}
                val gson = Gson()
                Log.d("tag","$apiResponse")
                try {
                    val response: LatestNews = gson.fromJson(apiResponse, LatestNews::class.java)
                    binding.apply {

                        adapter = NewsAdapter(baseContext,response.news)
                        binding.recyclerView.adapter= adapter
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            },{ error->
                error.printStackTrace()
                Toast.makeText(this, "Error is $error", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }
}