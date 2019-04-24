package org.wit.gameinfo.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_gameinfo_list.*
import org.jetbrains.anko.startActivityForResult
import org.wit.gameinfo.R
import org.wit.gameinfo.main.MainApp

class GameInfoListActivity: AppCompatActivity() {

    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameinfo_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = GameInfoAdapter(app.gameInfos)

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<GameInfoActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }
}
