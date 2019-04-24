package org.wit.gameinfo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_gameinfo.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.gameinfo.R
import org.wit.gameinfo.main.MainApp
import org.wit.gameinfo.models.GameInfoModel

class GameInfoActivity : AppCompatActivity(), AnkoLogger {

    var gameInfo = GameInfoModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameinfo)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            gameInfo.title = gameInfoTitle.text.toString()
            gameInfo.description= description.text.toString()
            if (gameInfo.title.isNotEmpty()) {
                app.gameInfos.add(gameInfo.copy())
                info("add Button Pressed: $gameInfo")
                app.gameInfos.forEach{ info("add Button Pressed: ${it.title}")}
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please enter a title ya dummy")
            }
        }

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gameinfo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
