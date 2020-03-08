package com.example.myapplication.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Model.Item
import com.example.myapplication.Model.convertLongToString
import com.example.myapplication.Model.getCurrentDate

private const val DB_NAME = "itemDatabase"
private const val DB_VERSION = 1

class ItemDatabaseOpenHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE Item(
            _id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            num INTEGER NOT NULL,
            date TEXT NOT NULL);
            """
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

//検索
fun queryItemList(context: Context): List<Item> {
    //読み込みデータベースを開く
    val database = ItemDatabaseOpenHelper(context).readableDatabase
    //DBから全件検索
    val cursor = database.query(
        "Item", null, null, null, null, null, null
    )
    val items = mutableListOf<Item>()
    cursor.use {
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val num = cursor.getInt(cursor.getColumnIndex("num"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            items.add(Item(id = id, name = name, num = num, date = date))
        }
    }

    database.close()
    return items
}

//挿入
fun insertItem(context: Context, name: String, num: Int) {
    val database = ItemDatabaseOpenHelper(context).writableDatabase

    database.use {
        val record = ContentValues().apply {
            put("name", name)
            put("num", num)
            put("date", convertLongToString(getCurrentDate()))
        }
        database.insert("Item", null, record)
    }
}