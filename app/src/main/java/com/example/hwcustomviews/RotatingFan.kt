package com.example.hwcustomviews

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RotatingFan @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : AppCompatImageView(context, attributeSet, defStyleAttrs) {

    private var size = 0
    private var rotatingDegrees = 0f
    private var speed: FanSpeed = FanSpeed.SPEED_SLOW


    init {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.propeler)
        setImageBitmap(bitmap)
    }

    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(width, height, oldw, oldh)

        size = width.coerceAtMost(height)
    }

    override fun onDraw(canvas: Canvas) {
        rotate(canvas)

        super.onDraw(canvas)
    }

    private fun rotate(canvas: Canvas) {
        canvas.translate(size / 2f, size / 2f)
        canvas.rotate(calcRotation(speed.speed))
        canvas.translate(-size / 2f, -size / 2f)
        postInvalidateOnAnimation()
    }

    private fun calcRotation(delta: Float): Float {
        rotatingDegrees = (rotatingDegrees + delta) % 360
        return rotatingDegrees
    }

    fun setSpeed(speed: FanSpeed) {
        this.speed = speed
    }
}