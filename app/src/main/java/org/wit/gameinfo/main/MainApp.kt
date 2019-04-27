package org.wit.gameinfo.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.gameinfo.models.GameInfoJSONStore
import org.wit.gameinfo.models.GameInfoStore

class MainApp : Application(), AnkoLogger {

    lateinit var gameInfos : GameInfoStore

    override fun onCreate() {
        super.onCreate()
        gameInfos = GameInfoJSONStore(applicationContext)
        info("Game Main App Started")
    }
}