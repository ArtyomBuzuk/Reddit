package com.artyombuzuk.reddit.favorite

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FavoriteDao {
    @Query("SELECT * from favorite_entity ")
    fun getFavorites(): LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteEntity: FavoriteEntity)

    @Delete()
    fun delete(favoriteEntity: FavoriteEntity)
}