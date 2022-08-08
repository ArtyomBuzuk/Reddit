package com.artyombuzuk.reddit.data

import com.artyombuzuk.reddit.favorite.FavoriteDao
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    abstract fun favoriteDao(): FavoriteDao
    @GET("/top.json")
    suspend fun getTopReddits(
        @Query("after") after : String? = null,
        @Query("limit") limit : Int = 10
    ) : Response<RemoteRedditPostList>
}
