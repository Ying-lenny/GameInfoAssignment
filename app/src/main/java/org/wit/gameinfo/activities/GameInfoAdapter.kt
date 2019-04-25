package org.wit.gameinfo.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_gameinfo.view.*
import org.wit.gameinfo.R
import org.wit.gameinfo.helpers.readImageFromPath
import org.wit.gameinfo.models.GameInfoModel

interface GameInfoListener {
    fun onGameInfoClick(gameInfo: GameInfoModel)
}

class GameInfoAdapter constructor(private var gameInfos: List<GameInfoModel>,
                                  private val listener: GameInfoListener) : RecyclerView.Adapter<GameInfoAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_gameinfo, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val gameInfo = gameInfos[holder.adapterPosition]
        holder.bind(gameInfo, listener)
    }

    override fun getItemCount(): Int = gameInfos.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(gameInfo: GameInfoModel, listener: GameInfoListener) {
            itemView.gameInfoTitle.text = gameInfo.title
            itemView.description.text = gameInfo.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, gameInfo.image))
            itemView.setOnClickListener {listener.onGameInfoClick(gameInfo)}
        }
    }
}