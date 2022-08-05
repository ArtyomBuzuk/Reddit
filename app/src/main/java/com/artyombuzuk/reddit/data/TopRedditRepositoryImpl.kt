package com.artyombuzuk.reddit.data

import com.artyombuzuk.reddit.domain.TopRedditRepository
import com.artyombuzuk.reddit.model.RedditPost
import com.artyombuzuk.reddit.model.toRedditPostParent

class TopRedditRepositoryImpl(
    private val redditApi: RedditApi
) : TopRedditRepository {

    override suspend fun getTopRedditPosts(after : String?) : Result<List<RedditPost>> {
        return try {
            val response = redditApi.getTopReddits(after = after)
            Result.success(response.body()!!.toRedditPostParent().data.children)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}