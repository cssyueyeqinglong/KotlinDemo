package com.cy.kotlin.demo

import android.util.Log
import com.cy.kotlin.demo.net.DecodeConvertFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient


/**
 * Created by Administrator on 2017/6/8.
 */
class RestAPI() {
    private val redditApi: RedditApi
    private val loginApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(MoshiConverterFactory.create())//解析json数据
                .build()

        redditApi = retrofit.create(RedditApi::class.java)

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            //打印retrofit日志
            Log.i("RetrofitLog", "retrofitBack = " + message)
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofitLogin = Retrofit.Builder()
                .baseUrl("http://www.reddit.com/")
                .client(client)
                .addConverterFactory(DecodeConvertFactory.create().asLenient())//解析json数据
                .build()
        loginApi = retrofitLogin.create(RedditApi::class.java)
    }

    fun getListNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }

    fun userLogin(head: LoginRequest): Call<LoginData> = loginApi.userLogin(head)
    fun userCLogin(head: LoginRequest): Call<LoginData> = loginApi.userCLogin(head)
}
