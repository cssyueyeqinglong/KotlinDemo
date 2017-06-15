package com.cy.kotlin.demo.net

import android.util.Log
import com.cy.kotlin.demo.util.Base64Utils
import com.squareup.moshi.JsonAdapter

import java.io.IOException

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by Administrator on 2017/6/14.
 */

internal class DecodeResponseBodyConvert<T>(private val adapter: JsonAdapter<T>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        try {
            val res = value.string()
            return adapter.fromJson(Base64Utils.decode(res))
            //            return adapter.fromJson(value.source());
        } finally {
            value.close()
        }
    }
}
