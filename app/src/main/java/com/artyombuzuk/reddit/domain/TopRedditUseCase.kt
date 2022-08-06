package com.artyombuzuk.reddit.domain

import com.artyombuzuk.reddit.model.RedditPost

interface TopRedditUseCase {
    suspend fun getTopRedditPosts(after: String? = null): Result<List<RedditPost>>
    fun shouldLoadImage(redditPost: RedditPost): Boolean
}