package com.example.upshoolcapstoneproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.upshoolcapstoneproject.MainApplication
import com.example.upshoolcapstoneproject.common.viewBinding
import com.example.upshoolcapstoneproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}