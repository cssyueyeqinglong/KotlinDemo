package com.cy.kotlin.demo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.squareup.moshi.Moshi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_net.*

class NetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)

        btn_login.setOnClickListener { v ->
            requestNews(0)
        }

        btn_loginc.setOnClickListener {
            v ->
            requestNews(1)
        }
    }

    fun requestNews(type: Int) {

        val name = edit_name.text.toString().trim()
        val pwd = edit_pwd.text.toString().trim()
        val head = RequestHead(if (type == 1) "c" else {
            "m"
        })
        head.type = null
        val requ = LoginRequest(head, ReqLoginData(name, pwd))


        val build = Moshi.Builder().build()
        val adapter = build.adapter(LoginRequest::class.java)
        val result = adapter.toJson(requ)

        val manager = NewsManager()
        val userLogin = if (type == 0) manager.userLogin(requ) else {
            manager.usercLogin(requ)
        }
        val subcription = userLogin.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            loginData ->
                            tv_content.text = loginData.data.toString()
                        },
                        {
                            e ->
                            e.printStackTrace()
                            Log.d(this.javaClass.simpleName, e.message ?: "")
                            Snackbar.make(btn_login, e.message ?: "", Snackbar.LENGTH_SHORT).show()
                        }

                )
//        subscriptions.add(subcription)
    }


    private val newManger by lazy { NewsManager() }
}
