package com.cy.kotlin.demo.net

import android.util.Log
import com.cy.kotlin.demo.util.Base64Utils
import com.squareup.moshi.JsonAdapter

import java.io.IOException

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter

/**
 * Created by Administrator on 2017/6/14.
 */

class DecodeRequestBodyConverter<T> internal constructor(private val adapter: JsonAdapter<T>) : Converter<T, RequestBody> {
    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val res = adapter.toJson(value)
        val encode = Base64Utils.encode(res.toByteArray())
        return RequestBody.create(MEDIA_TYPE,encode)
//        val buffer = Buffer()
//        adapter.toJson(buffer, value)
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }

    companion object {
        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
    }
}
