package org.wit.gameinfo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_gameinfo.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.gameinfo.R
import org.wit.gameinfo.models.GameInfoModel

class GameInfoActivity : AppCompatActivity(), AnkoLogger {

    var gameInfo = GameInfoModel()
    val gameInfos = ArrayList<GameInfoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameinfo)
        info("Game Time started..")

        btnAdd.setOnClickListener() {
            gameInfo.title = gameInfoTitle.text.toString()
            if (gameInfo.title.isNotEmpty()) {
                gameInfos.add(gameInfo.copy())
                info("add Button Pressed: $gameInfo")
                gameInfos.forEach{ info("add Button Pressed: ${it.title}")}
            } else {
                toast("Please Enter a title ya dummy")
            }
        }
    }
}
