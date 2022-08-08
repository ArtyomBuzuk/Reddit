package com.artyombuzuk.reddit.domain

import com.artyombuzuk.reddit.model.RedditPost

interface RedditRepository {
    suspend fun getTopRedditPosts(
        after : String? = null
    ) : Result<List<RedditPost>>
}