package org.wit.gameinfo.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.gameinfo.helpers.exists
import org.wit.gameinfo.helpers.*
import java.util.*
import kotlin.collections.ArrayList

val JSON_FILE = "gameInfos.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<GameInfoModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class GameInfoJSONStore : GameInfoStore, AnkoLogger {

    val context: Context
    var gameInfos = mutableListOf<GameInfoModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<GameInfoModel> {
        return gameInfos
    }

    override fun create(gameInfo: GameInfoModel) {
        gameInfo.id = generateRandomId()
        gameInfos.add(gameInfo)
        serialize()
    }

    override fun delete(gameInfo: GameInfoModel) {
        gameInfos.remove(gameInfo)
        serialize()
    }

    override fun update(gameInfo: GameInfoModel) {
        val gameInfosList = findAll() as ArrayList<GameInfoModel>
        var foundGameInfo: GameInfoModel? = gameInfosList.find { p -> p.id == gameInfo.id}
        if (foundGameInfo != null) {
            foundGameInfo.title = gameInfo.title
            foundGameInfo.description = gameInfo.description
            foundGameInfo.image = gameInfo.image
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(gameInfos, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        gameInfos = Gson().fromJson(jsonString, listType)
    }
}