package com.tayyar.pianotiles.game

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.tayyar.pianotiles.R

class GameActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var img: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        val speed = intent.getStringExtra("speed")
        val music = intent.getBooleanExtra("music", true)
        val vibration = intent.getBooleanExtra("vibration", true)

        GameView.music = music
        GameView.vibration = vibration

        if (speed != "") {
            Tile.speed = speed!!.toInt()
        }

        val screen = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        gameView = GameView(this)
        gameView.layoutParams =
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        screen.addView(gameView)

        img = layoutInflater.inflate(R.layout.centered_image, screen, false)
        img.visibility = View.GONE
        img.setOnClickListener {
            gameView.restart()
        }
        screen.addView(img)

        // remove notification bar
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    fun showReplayButton() {
        this@GameActivity.runOnUiThread {
            img.visibility = View.VISIBLE
        }
    }

    fun hideReplayButton() {
        this@GameActivity.runOnUiThread {
            img.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        gameView.destroy()
        super.onDestroy()
    }
}
