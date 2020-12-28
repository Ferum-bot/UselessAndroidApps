package com.github.ferum_bot.start_app.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.ferum_bot.start_app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideActionBar()
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

}