package org.wit.gameinfo.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_gameinfo.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.gameinfo.R
import org.wit.gameinfo.helpers.readImage
import org.wit.gameinfo.helpers.readImageFromPath
import org.wit.gameinfo.helpers.showImagePicker
import org.wit.gameinfo.main.MainApp
import org.wit.gameinfo.models.GameInfoModel

class GameInfoActivity : AppCompatActivity(), AnkoLogger {

    var gameInfo = GameInfoModel()
    lateinit var app : MainApp
    var edit = false
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameinfo)
        app = application as MainApp


        if (intent.hasExtra("gameInfo_edit"))
        {
            gameInfo = intent.extras.getParcelable<GameInfoModel>("gameInfo_edit")
            gameInfoTitle.setText(gameInfo.title)
            description.setText(gameInfo.description)
            gameInfoImage.setImageBitmap(readImageFromPath(this, gameInfo.image))
            edit = true
            btnAdd.setText(R.string.save_gameInfo)
        }

        btnAdd.setOnClickListener()
        {
            gameInfo.title = gameInfoTitle.text.toString()
            gameInfo.description = description.text.toString()
            if (gameInfo.title.isNotEmpty()) {
                if (edit) {
                    app.gameInfos.update(gameInfo.copy())
                } else {
                    app.gameInfos.create(gameInfo.copy())
                }
                info("add Button Pressed: $gameInfoTitle")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast (R.string.enter_gameInfo_title)
            }
        }

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    gameInfo.image = data.getData().toString()
                    gameInfoImage.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
    }
}
