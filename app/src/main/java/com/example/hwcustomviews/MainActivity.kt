package com.example.hwcustomviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClickListeners()
    }

    private fun setClickListeners() {
        ibIncrease.setOnClickListener {
            if (!fcControl.increaseSpeed()) {
                Toast.makeText(this, R.string.speed_max, Toast.LENGTH_SHORT).show()
            }
            rfPropeller.setSpeed(fcControl.getSpeed())
        }

        ibDecrease.setOnClickListener {
            if (!fcControl.decreaseSpeed()) {
                Toast.makeText(this, R.string.speed_min, Toast.LENGTH_SHORT).show()
            }
            rfPropeller.setSpeed(fcControl.getSpeed())
        }

        bOpenFragment.setOnClickListener {
            val intent = CustomViewGroupActivity.newIntent(this)
            startActivity(intent)
        }
    }
}