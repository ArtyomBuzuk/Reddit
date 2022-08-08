package com.artyombuzuk.reddit.favorite

import androidx.lifecycle.LiveData
import com.artyombuzuk.reddit.data.RedditApi

class FavoriteRepository(appDatabase: RedditApi) {

    private val favoriteDao = appDatabase.favoriteDao()

    val allFavorites: LiveData<List<FavoriteEntity>> = favoriteDao.getFavorites()

    fun insert(favorite: FavoriteEntity) {
        favoriteDao.insert(favorite)
    }

    fun delete(favorite: FavoriteEntity) {
        favoriteDao.delete(favorite)
    }
}