@file:JvmName("ExtensionsUtils")

package com.cy.kotlin.demo

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Administrator on 2017/6/8.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

}

fun ImageView.loadImg(url: String) {
    Glide.with(context).load(url).into(this)
}

inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }

fun getVersionName(context: Context): String {
    val manager = context.packageManager
    try {
        val packageInfo = manager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    } catch(e: Exception) {
        return "1.0"
    }
}