package com.cy.kotlin.demo

/**
 * Created by Administrator on 2017/6/13.
 */

data class RequestHead(var type: String? = "m") {
    val _request_time_stamp_ = System.currentTimeMillis()
    val _app_version_code_: String = "2.5.0"
    val _channel_: Int = 1
    val _dev_: String = "android"
    val _device_type_: Int = 1
}

data class ReqLoginData(val loginName: String,
                        val loginPass: String) {
    constructor(loginName: String,
                loginPass: String, accountTypeId: Int) : this(loginName, loginPass)
}
