package org.wit.gameinfo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GameInfoModel (var id: Long = 0,
                          var title: String = "",
                          var description: String = "") : Parcelable