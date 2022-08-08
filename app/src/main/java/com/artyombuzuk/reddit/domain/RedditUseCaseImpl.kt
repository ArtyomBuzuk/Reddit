package com.artyombuzuk.reddit.domain

import com.artyombuzuk.reddit.model.RedditPost

class RedditUseCaseImpl(
    private val redditRepository: RedditRepository
) : RedditUseCase {

    override suspend fun getTopRedditPosts(after : String?): Result<List<RedditPost>> {
        return redditRepository.getTopRedditPosts(after)
    }

    override fun shouldLoadImage(redditPost: RedditPost) : Boolean = redditPost.thumbnail?.contains("\\w+[.][jpg|png]".toRegex()) == true

}