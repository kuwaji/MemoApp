package com.example.myapplication.present

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DB.queryItemList
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var position: Int = 0 // リサイクルビューのPosition
    var adapter: ListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //　ResouceのRecyclerView
//        val recyclerView = inventory_list
        if (recyclerView == null)
            recyclerView = findViewById(R.id.inventory_list)
        adapter = ListViewAdapter(this, queryItemList(this))

        //recyclerViewにadapterをセット
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)

        //区切り線
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView?.addItemDecoration(itemDecoration)

        val addButton = findViewById<Button>(R.id.add_button)

        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 1)
        }

        //Pull_to_Refresh
        val swipeRefresh = swipe_refresh_layout
        swipeRefresh.setColorSchemeResources(R.color.primary)
        swipeRefresh.setOnRefreshListener {
            itemListRefresh()
            swipe_refresh_layout.isRefreshing = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            itemListRefresh()
        }
    }

    override fun onResume() {
        super.onResume()
        //setupRecyclerView() => 画面更新させる
        (recyclerView?.layoutManager as LinearLayoutManager).scrollToPosition(position)// =>　ポジションまで移動
    }

    override fun onPause() {
        super.onPause()
        position =
            (recyclerView?.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()// ポジション保存
    }

    //itemに新しいものが追加された時、adapterのリストを更新したい
    fun itemListRefresh(){
        adapter = ListViewAdapter(this, queryItemList(this))
        recyclerView?.adapter = adapter
    }

    //メニュー表示の為の関数
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        //メニューのリソース選択
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //メニューのアイテムを押下した時の処理の関数
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //作成ボタンを押したとき
            R.id.menu_add -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivityForResult(intent, 1)
                return true
            }
            //削除ボタンを押したとき
            R.id.menu_all_delete -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}

