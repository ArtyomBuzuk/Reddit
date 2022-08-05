package com.artyombuzuk.reddit.model

import com.artyombuzuk.reddit.data.RemoteRedditPost
import com.artyombuzuk.reddit.data.RemoteRedditPostData
import com.artyombuzuk.reddit.data.RemoteRedditPostList

fun RemoteRedditPostList.toRedditPostParent() = RedditPostParent(
    data = data.toRedditPostData(),
    kind = kind
)

fun RemoteRedditPostData.toRedditPostData() = RedditPostData(
    children = children.map { it.data.toRedditPost() },
    after = after,
    before = before
)

fun RemoteRedditPost.toRedditPost() = RedditPost(
    thumbnail = thumbnail,
    url = url,
    title = title,
    author = author,
    name = name
)