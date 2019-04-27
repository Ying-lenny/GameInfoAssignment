package org.wit.gameinfo.models

interface GameInfoStore
{
    fun findAll(): List<GameInfoModel>

    fun create(gameInfo: GameInfoModel)

    fun update(gameInfo: GameInfoModel)

    fun delete(gameInfo: GameInfoModel)

}