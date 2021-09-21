package com.tayyar.tiletap

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tayyar.tiletap.databinding.FragmentMainBinding
import com.tayyar.tiletap.game.GameActivity

/** Homepage of the app */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

        // Add on click listener to the button to start the game
        binding.startButton.setOnClickListener {
            val speed = binding.speedInput.text.toString()
            val music = binding.musicBox.isChecked
            val vibration = binding.vibrationBox.isChecked
            val speedIncrease = binding.speedIncreaseBox.isChecked

            if (speed == "" || speed == "0") {
                Toast.makeText(context, "You have to select a speed", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(context, GameActivity::class.java).apply {
                    putExtra("speed", speed)
                    putExtra("music", music)
                    putExtra("vibration", vibration)
                    putExtra("speedIncrease", speedIncrease)
                }
                startActivity(intent)
            }
        }

        return binding.root
    }
}