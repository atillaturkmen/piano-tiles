package com.tayyar.pianotiles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get high scores and show them as a toast
        val sharedPref = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            MODE_PRIVATE
        ) ?: return
        val highScore = sharedPref.all.toSortedMap()
        Toast.makeText(this, "High Scores: \n$highScore", Toast.LENGTH_LONG).show()
    }

}
