package com.artyombuzuk.reddit.domain

import com.artyombuzuk.reddit.model.RedditPost

interface TopRedditRepository {
    suspend fun getTopRedditPosts(
        after : String? = null
    ) : Result<List<RedditPost>>
}