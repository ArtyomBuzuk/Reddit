package com.artyombuzuk.reddit.view.viewmodel

import androidx.lifecycle.ViewModel
import com.artyombuzuk.reddit.domain.TopRedditUseCase
import com.artyombuzuk.reddit.model.RedditPost

class TopRedditPostDetailsViewModel(
    private val topRedditUseCase: TopRedditUseCase
) : ViewModel() {

    fun shouldLoadImage(redditPost: RedditPost) : Boolean {
        return topRedditUseCase.shouldLoadImage(redditPost)
    }

}