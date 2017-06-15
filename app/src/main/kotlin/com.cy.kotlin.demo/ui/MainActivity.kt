package com.cy.kotlin.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Administrator on 2017/6/7.
 */

class MainActivity : AppCompatActivity() {

    var a: Int = 0
    val s: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        val mTvTitle = findViewById(R.id.tv_title) as TextView
        mTvTitle.setOnClickListener { v: View -> toNext(v) }

        supportFragmentManager.beginTransaction().replace(R.id.fl_content, MainFragment()).commit()
    }

    fun toNext(v: View) {
        Toast.makeText(this, "测试我", Toast.LENGTH_SHORT).show()
    }
}
