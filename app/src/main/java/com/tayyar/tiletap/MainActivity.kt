package com.tayyar.tiletap

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.tayyar.tiletap.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        // set drawer
        navController = findNavController(R.id.pianoTilesNavHostFragment)
        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        // menu can only be opened in main menu
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id == controller.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    // Close keyboard when tapped outside of edittext
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus is EditText) {
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            currentFocus?.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

}
