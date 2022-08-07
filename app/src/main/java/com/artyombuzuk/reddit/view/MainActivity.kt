package com.artyombuzuk.reddit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artyombuzuk.reddit.databinding.ActivityMainBinding
import org.koin.core.component.KoinComponent


class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

}