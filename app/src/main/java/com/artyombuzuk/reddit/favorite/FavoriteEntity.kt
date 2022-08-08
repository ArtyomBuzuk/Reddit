package com.artyombuzuk.reddit.favorite

import androidx.room.Entity

@Entity(tableName = "favorite_entity")
data class FavoriteEntity(
    val thumbnail: String?,
    val url: String?,
    val title: String?,
    val author: String?,
    val name: String?,
    val num_comments: Int?,
    val created: Double?
)