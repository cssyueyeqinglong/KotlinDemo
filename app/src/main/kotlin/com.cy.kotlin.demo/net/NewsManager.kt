package com.cy.kotlin.demo

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Administrator on 2017/6/8.
 */

class NewsManager(private val api: RestAPI = RestAPI()) {
    fun getNewList(): Observable<RedditNews> {

        return Observable.create { subscriber ->
            val callResponse = api.getListNews("", "" + 10)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataResponse = response.body().data
                val news = response.body().data.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }
                val redditNews = RedditNews(dataResponse.after ?: "", dataResponse.before ?: "", news)
                subscriber.onNext(redditNews)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }

        }
    }

    fun userLogin(head: LoginRequest): Observable<LoginData> = Observable.create { subscriber ->
        val callResponse = api.userLogin(head)
        val response = callResponse.execute()
        if (response.isSuccessful) {
            val body = response.body()
            subscriber.onNext(body)
            subscriber.onComplete()
        } else {
            subscriber.onError(Throwable(response.message()))
        }

    }

    fun usercLogin(head: LoginRequest): Observable<LoginData> = Observable.create { subscriber ->
        val callResponse = api.userCLogin(head)

        val callback = object : Callback<LoginData> {
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                subscriber.onNext(response.body())
                subscriber.onComplete()
            }

            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                subscriber.onError(t)
            }
        }

        val response = callResponse.enqueue(callback)

    }
}
