package com.artyombuzuk.reddit.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.artyombuzuk.reddit.R
import com.artyombuzuk.reddit.databinding.ActivityMainBinding
import com.artyombuzuk.reddit.databinding.FragmentPostDetailsBinding
import com.artyombuzuk.reddit.di.BASE_URL
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

private lateinit var binding: FragmentPostDetailsBinding

fun DownloadImageFromPath(path: String?) {
    var `in`: InputStream? = null
    var bmp: Bitmap? = null
    binding.thumbnail
    val iv = binding.thumbnail
//    findViewById<View>(R.id.thumbnail) as ImageView
    var responseCode = -1
    try {
        val url = URL(BASE_URL)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.setDoInput(true)
        con.connect()
        responseCode = con.getResponseCode()
        if (responseCode == HttpURLConnection.HTTP_OK) {
            `in` = con.getInputStream()
            bmp = BitmapFactory.decodeStream(`in`)
            `in`.close()
            iv.setImageBitmap(bmp)
        }
    } catch (ex: Exception) {
        Log.e("Exception", ex.toString())
    }
}