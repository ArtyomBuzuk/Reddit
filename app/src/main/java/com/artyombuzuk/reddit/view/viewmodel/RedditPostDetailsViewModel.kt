package com.artyombuzuk.reddit.view.viewmodel

import androidx.lifecycle.ViewModel
import com.artyombuzuk.reddit.domain.RedditUseCase
import com.artyombuzuk.reddit.model.RedditPost

class RedditPostDetailsViewModel(
    private val redditUseCase: RedditUseCase
) : ViewModel() {

    fun shouldLoadImage(redditPost: RedditPost): Boolean {
        return redditUseCase.shouldLoadImage(redditPost)
    }

}