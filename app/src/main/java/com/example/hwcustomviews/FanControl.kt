package com.example.hwcustomviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class FanControl @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : View(context, attributeSet, defStyleAttrs, defStyleRes) {

    private var size = 0
    private var fanSpeed: FanSpeed = FanSpeed.SPEED_SLOW

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    private val bodyColor = Color.GREEN
    private val dotColor = Color.BLACK
    private val textColor = Color.BLACK

    private var textBorderOffset = 0f
    private var pointerBorderOffset = 0f

    private val textSize = resources.getDimensionPixelSize(R.dimen.fanControlTextSize)

    private var dotRadius = 0f


    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(width, height, oldw, oldh)
        size = width.coerceAtMost(height)
        textBorderOffset = size / 8f
        pointerBorderOffset = size / 5.5f
        dotRadius = size / 26f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)
        drawSpeeds(canvas)
        drawSpeedPointer(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.color = bodyColor
        canvas.drawCircle(size / 2f, size / 2f, size / 2f - textBorderOffset, paint)
    }

    private fun drawSpeeds(canvas: Canvas) {
        val startingAngle = 112.5f
        val increment = 45f

        paint.color = textColor
        paint.textSize = textSize.toFloat()
        paint.isFakeBoldText = true
        //set new starting coordinates for canvas
        canvas.translate(size / 2f, size / 2f)
        enumValues<FanSpeed>().forEach {
            //save canvas state
            canvas.save()
            //rotate canvas to draw text at specified angle
            canvas.rotate(startingAngle + increment * it.ordinal)
            canvas.translate(0f, size / 2f - (textBorderOffset / 2f))
            //rotate text back
            canvas.rotate(-(startingAngle + increment * it.ordinal))
            canvas.drawText(it.label, 0f, 0f, paint)
            //restore canvas
            canvas.restore()
        }
        //fully resetting canvas coordinates
        canvas.translate(-size / 2f, -size / 2f)
    }

    private fun drawSpeedPointer(canvas: Canvas) {
        val defaultAngle = 112.5f
        val increment = 45f

        paint.color = dotColor

        canvas.translate(size / 2f, size / 2f)
        canvas.save()
        canvas.rotate(defaultAngle + increment * fanSpeed.ordinal)
        canvas.translate(0f, size / 2f - pointerBorderOffset)
        canvas.rotate(-(defaultAngle + increment * fanSpeed.ordinal))
        canvas.drawCircle(0f, 0f, dotRadius, paint)
        canvas.restore()
        canvas.translate(-size / 2f, -size / 2f)

    }

    fun increaseSpeed(): Boolean {
        return if (fanSpeed != FanSpeed.SPEED_MAX) {
            fanSpeed = enumValues<FanSpeed>()[fanSpeed.ordinal + 1]
            invalidate()
            true
        } else {
            false
        }
    }

    fun decreaseSpeed(): Boolean {
        return if (fanSpeed != FanSpeed.SPEED_SLOW) {
            fanSpeed = enumValues<FanSpeed>()[fanSpeed.ordinal - 1]
            invalidate()
            true
        } else {
            false
        }
    }

    fun getSpeed(): FanSpeed {
        return fanSpeed
    }

}