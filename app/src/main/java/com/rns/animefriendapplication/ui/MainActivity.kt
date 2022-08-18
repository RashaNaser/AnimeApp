package com.rns.animefriendapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rns.animefriendapplication.R
import com.rns.animefriendapplication.ui.fragments.HomeFragment
import com.rns.animefriendapplication.utils.navigateTo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateTo(HomeFragment())
    }
}