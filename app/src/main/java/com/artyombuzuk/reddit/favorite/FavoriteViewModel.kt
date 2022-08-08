package com.artyombuzuk.reddit.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {


    fun getFavorites(): LiveData<List<FavoriteEntity>>? {
        return favoriteRepository.allFavorites
    }

    fun insert(favoriteEntity: FavoriteEntity) {
        favoriteRepository.insert(favoriteEntity)
    }

    fun delete(favoriteEntity: FavoriteEntity) {
        favoriteRepository.delete(favoriteEntity)
    }

}