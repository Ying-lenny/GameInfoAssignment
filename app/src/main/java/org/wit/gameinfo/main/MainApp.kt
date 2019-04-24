package org.wit.gameinfo.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.gameinfo.models.GameInfoModel

class MainApp : Application(), AnkoLogger {

    val gameInfos = ArrayList<GameInfoModel>()


    override fun onCreate() {
        super.onCreate()
        info("Game Main App Started")
//        gameInfos.add(GameInfoModel("One","so how 'bout you?"))
//        gameInfos.add(GameInfoModel("Two","so how 'bout me?"))
//        gameInfos.add(GameInfoModel("Three","so how 'bout us?"))
    }
}