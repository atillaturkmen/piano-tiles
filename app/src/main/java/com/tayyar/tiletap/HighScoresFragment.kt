package com.tayyar.tiletap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tayyar.tiletap.databinding.FragmentHighScoresBinding

/** Shows high scores */
class HighScoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHighScoresBinding =
            FragmentHighScoresBinding.inflate(inflater, container, false)

        // get high scores and show in table
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_name),
            AppCompatActivity.MODE_PRIVATE
        )
        val highScores = sharedPref?.all?.toSortedMap(compareBy<String> { it.toInt() })

        for (score in highScores!!.iterator()) {
            val item = inflater.inflate(R.layout.list_item, binding.highScoresTable, false)
            item.findViewById<TextView>(R.id.speed).text = score.key
            item.findViewById<TextView>(R.id.score).text = score.value.toString()
            binding.highScoresTable.addView(item)
        }

        return binding.root
    }
}