package com.example.myapplication.Model

import androidx.annotation.LongDef
import java.text.SimpleDateFormat
import java.util.Date

data class Item(
    val id: Long,
    val name: String,
    val num: Int,
    var date: String = convertLongToString(getCurrentDate())
)

fun getCurrentDate() : Long{
    return System.currentTimeMillis()
}
/**
 * Long型の日付を引数にとって日付文字列を返す
 * データベースから取得した値を「yyyy/MM/dd HH:mm:ss」の形の文字列として扱うことができる
 */
fun convertLongToString(dateLong : Long) : String{
    val date = Date(dateLong)
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    return simpleDateFormat.format(date)
}