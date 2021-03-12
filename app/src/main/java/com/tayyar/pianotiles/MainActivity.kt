package com.tayyar.pianotiles

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove notification bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)
    }

    fun startGame(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val musicBox = findViewById<CheckBox>(R.id.checkBox)
        val vibrationBox = findViewById<CheckBox>(R.id.checkBox2)

        val speed = editText.text.toString()
        val music = musicBox.isChecked
        val vibration = vibrationBox.isChecked

        val intent = Intent(this, GameActivity::class.java).apply {
            putExtra("speed", speed)
            putExtra("music", music)
            putExtra("vibration", vibration)
        }
        startActivity(intent)
    }
}
