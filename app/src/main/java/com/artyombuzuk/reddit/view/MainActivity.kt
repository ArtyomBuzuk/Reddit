package com.artyombuzuk.reddit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.artyombuzuk.reddit.R
import com.artyombuzuk.reddit.databinding.ActivityMainBinding
import com.artyombuzuk.reddit.favorite.FavoriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.core.component.KoinComponent


class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigationClickListener()
        setSupportActionBar(binding.toolbar)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
    }

    private fun setBottomNavigationClickListener() {
//       binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.topNewsButton -> {
                    replaceFragment(RedditPostFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favoriteNewsButton -> {
                    replaceFragment(FavoriteFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

}