package com.example.hwcustomviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.custom_view_group_activity.*

class CustomViewGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_view_group_activity)

        setClickListeners()
    }

    private fun setClickListeners() {
        bChangeTile.setOnClickListener {
            mtvExample.setGenreText("Animation")
            mtvExample.setTitleText(R.string.title_example)
            mtvExample.setPriceText("25.99$")
            mtvExample.setPosterDrawable(R.drawable.propeler)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CustomViewGroupActivity::class.java)
        }
    }
}