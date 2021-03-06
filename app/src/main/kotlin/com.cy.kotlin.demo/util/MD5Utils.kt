package com.cy.kotlin.demo

import java.security.MessageDigest
import kotlin.experimental.and

/**
 * Created by Administrator on 2017/6/13.
 */
fun getHexMD5(str: String): String? = getMD5(str.toByteArray())

fun getMD5(source: ByteArray): String? {
    var s: String? = null
    var hexDigits: CharArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f')
    try {
        val md = MessageDigest
                .getInstance("MD5")
        md.update(source)
        val tmp = md.digest() // MD5 的计算结果是一个 128 位的长整数，
        // 用字节表示就是 16 个字节
        val str = CharArray(16 * 2) // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        var k = 0 // 表示转换结果中对应的字符位置
        for (i in 0..15) { // 从第一个字节开始，对 MD5 的每一个字节
            // 转换成 16 进制字符的转换
            val byte0 = tmp[i] // 取第 i 个字节
            str[k++] = hexDigits[byte0.toInt().ushr(4) and 0xf] // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[(byte0 and 0xf).toInt()] // 取字节中低 4 位的数字转换
        }
        s = String(str) // 换后的结果转换为字符串
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return s
}
