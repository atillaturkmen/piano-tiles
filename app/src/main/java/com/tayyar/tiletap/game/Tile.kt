package com.tayyar.tiletap.game

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import com.tayyar.tiletap.game.GameView.Companion.screenWidth
import com.tayyar.tiletap.game.GameView.Companion.screenHeight
import kotlin.math.roundToInt

/**
 * Tile Class.
 * It goes from top to bottom
 * Purpose of the game is to press the tile
 */

class Tile(blackPaint : Paint, private var pressedTileColor: Paint, private var redPaint: Paint, row : Int) {

    companion object {
        var speed = 30.0
        var speedIncrease = false
    }

    private var startX: Int = 0
    var startY: Int = 0
    private var endX: Int = 0
    var endY: Int = 0

    var pressed: Boolean = false

    var outOfScreen = false
    private var outOfBounds = false
    var gameOver = false

    private var tileColor = blackPaint


    init {
        startX = row * (screenWidth/4)
        startY = -screenHeight/4
        endX = screenWidth/4 + startX
        endY = screenHeight/4 + startY
    }

    /**
     * Draws the object on to the canvas.
     */
    fun draw(canvas: Canvas) {
        canvas.drawRect(Rect(startX, startY, endX, endY), tileColor)
    }

    /**
     * update properties for the game object
     */
    fun update(frameNo: Int) {

        //stop the tile if it reaches the end
        if (false) {
            tileColor = redPaint
            outOfBounds = true
            speed = -40.0
        }
        if (outOfBounds && endY <= screenHeight) {
            gameOver = true
        }
        if (startY >= screenHeight && pressed) {
            outOfScreen = true
        }
        Log.d("atii", speed.toString())
        if (speedIncrease && speed != 0.0 && frameNo % 60 == 0 && speed < 50) {
            speed += 1 / (speed * 20)
        }
        startY += (speed.roundToInt())
        endY += (speed.roundToInt())

    }

    fun checkTouch (x: Float, y: Float) : Boolean {
        if (x > startX - screenWidth/30 && x < endX + screenWidth/30 && y < endY && y > startY && !pressed) {
            tileColor = pressedTileColor
            GameView.score++
            pressed = true
            return pressed
        }
        return false
    }

}