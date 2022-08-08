package com.artyombuzuk.reddit.data

import com.artyombuzuk.reddit.domain.RedditRepository
import com.artyombuzuk.reddit.model.RedditPost
import com.artyombuzuk.reddit.model.toRedditPostParent

class RedditRepositoryImpl(
    private val redditApi: RedditApi
) : RedditRepository {

    override suspend fun getTopRedditPosts(after : String?) : Result<List<RedditPost>> {
        return try {
            val response = redditApi.getTopReddits(after = after)
            Result.success(response.body()!!.toRedditPostParent().data.children)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}