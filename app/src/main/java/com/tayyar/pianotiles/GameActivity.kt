package com.tayyar.pianotiles

import android.os.Bundle


class GameActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val speed = intent.getStringExtra("speed")
        val music = intent.getBooleanExtra("music", true)
        val vibration = intent.getBooleanExtra("vibration", true)

        GameView.music = music
        GameView.vibration = vibration

        if (speed != "") {
            Tile.speed = speed!!.toInt()
        }

        setContentView(R.layout.activity_game)
    }
}
