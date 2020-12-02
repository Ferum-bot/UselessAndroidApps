package com.example.criminalintent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.criminalintent.R
import com.example.criminalintent.ui.crimeList.CrimeListFragment

class MainActivity : AppCompatActivity(), CrimeListFragment.CallBacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCrimeListFragmentOpened() {
        supportActionBar?.show()
    }

    override fun onCrimeListFragmentClosed() {
        supportActionBar?.hide()
    }
}