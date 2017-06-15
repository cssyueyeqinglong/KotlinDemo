package com.cy.kotlin.demo.net

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by Administrator on 2017/6/14.
 */

class DecodeConvertFactory private constructor(private val moshi: Moshi, private val lenient: Boolean) : Converter.Factory() {

    init {
        if (moshi == null) throw NullPointerException("moshi == null")
    }

    /** Return a new factory which uses [lenient][JsonAdapter.lenient] adapters.  */
    fun asLenient(): DecodeConvertFactory {
        return DecodeConvertFactory(moshi, true)
    }


    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>?,
                                       retrofit: Retrofit?): Converter<ResponseBody, *> {
        var adapter:JsonAdapter<*> = moshi.adapter<Any?>(type)
        if (lenient) {
            adapter = adapter.lenient()
        }
        return DecodeResponseBodyConvert(adapter)
    }

    override fun requestBodyConverter(type: Type?,
                                      parameterAnnotations: Array<Annotation>?, methodAnnotations: Array<Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody> {
        var adapter = moshi.adapter<Any?>(type)
        if (lenient) {
            adapter = adapter.lenient()
        }
        return DecodeRequestBodyConverter(adapter)
    }

    companion object {

        /** Create an instance using `moshi` for conversion.  */
        @JvmOverloads fun create(moshi: Moshi = Moshi.Builder().build()): DecodeConvertFactory {
            return DecodeConvertFactory(moshi, false)
        }
    }
}
/** Create an instance using a default [Moshi] instance for conversion.  */
