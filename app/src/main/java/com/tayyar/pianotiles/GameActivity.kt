package com.tayyar.pianotiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

/**
 * This activity calls game view
 */
class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ati", "GameActivity onCreate")

        // remove notification bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

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

    override fun onResume() {
        super.onResume()
        Log.d("ati", "GameActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ati", "GameActivity onPause")
    }
}
