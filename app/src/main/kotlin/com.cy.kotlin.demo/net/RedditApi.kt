@file: JvmName("RedditApi")

package com.cy.kotlin.demo

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Administrator on 2017/6/8.
 */
interface RedditApi {
    @GET("/top.json")
    fun getTop(@Query("after") after: String, @Query("limit") limit: String): Call<RedditNewsResponse>

    @POST("/mapi/user/business/login.json")
    fun userLogin(@Body request: LoginRequest): Call<LoginData>

    @POST("/capi/user/login/loginOnV2.json")
    fun userCLogin(@Body request: LoginRequest): Call<LoginData>
}