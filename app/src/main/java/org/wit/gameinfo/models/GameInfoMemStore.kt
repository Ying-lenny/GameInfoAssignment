package org.wit.gameinfo.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class GameInfoMemStore : GameInfoStore, AnkoLogger {

    val gameInfos = ArrayList<GameInfoModel>()

    override fun findAll(): List<GameInfoModel> {
        return gameInfos
    }

    override fun create(gameInfo: GameInfoModel) {
        gameInfos.add(gameInfo)
    }

    override fun delete(gameInfo: GameInfoModel) {
        gameInfos.remove(gameInfo)
    }

    override fun update(gameInfo: GameInfoModel) {
        var foundGameInfo: GameInfoModel? = gameInfos.find { p -> p.id == gameInfo.id}
        if (foundGameInfo != null) {
            foundGameInfo.title = gameInfo.title
            foundGameInfo.description = gameInfo.description
            foundGameInfo.image = gameInfo.image
            logAll()
        }
    }

    fun logAll() {
        gameInfos.forEach { info("${it}") }
    }
}