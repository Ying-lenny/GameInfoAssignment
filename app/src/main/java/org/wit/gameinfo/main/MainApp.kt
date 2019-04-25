package org.wit.gameinfo.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.gameinfo.models.GameInfoMemStore

class MainApp : Application(), AnkoLogger {

    val gameInfos = GameInfoMemStore()


    override fun onCreate() {
        super.onCreate()
        info("Game Main App Started")

    }
}