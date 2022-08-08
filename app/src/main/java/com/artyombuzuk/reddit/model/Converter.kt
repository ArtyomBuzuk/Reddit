package com.artyombuzuk.reddit.model

import com.artyombuzuk.reddit.data.RemoteRedditPost
import com.artyombuzuk.reddit.data.RemoteRedditPostData
import com.artyombuzuk.reddit.data.RemoteRedditPostList
import com.artyombuzuk.reddit.favorite.FavoriteEntity
import com.artyombuzuk.reddit.utils.getHours

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
    name = name,
    num_comments = num_comments,
    created = created
)

fun favEntitiesToModels(favoriteEntity: List<FavoriteEntity?>): List<RedditPost> {
    val modelList = arrayListOf<RedditPost>()

    favoriteEntity.forEach {
        modelList.add(
            RedditPost(
                thumbnail = it?.thumbnail,
                url = it?.url,
                title = it?.title,
                author = it?.author,
                name = it?.name,
                num_comments = it?.num_comments,
                created = it?.created.getHours().toDouble()
            )
        )
    }
    return modelList
}

fun favEntityToModel(redditPost: RedditPost): FavoriteEntity? {
    return redditPost.thumbnail?.let {
        redditPost.url?.let { it1 ->
            redditPost.title?.let { it2 ->
                redditPost.author?.let { it3 ->
                    redditPost.name?.let { it4 ->
                        redditPost.num_comments?.let { it5 ->
                            redditPost.created?.let { it6 ->
                                FavoriteEntity(
                                    thumbnail = it,
                                    url = it1,
                                    title = it2,
                                    author = it3,
                                    name = it4,
                                    num_comments = it5.toInt(),
                                    created = it6.toDouble()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun commonEntitiesToComments(favoriteEntity: List<RemoteRedditPost?>): List<RedditPost> {
    val comments = arrayListOf<RedditPost>()

    favoriteEntity.forEach {
        comments.add(
            RedditPost(
                it?.thumbnail,
                it?.url,
                it?.title,
                it?.author,
                it?.name,
                it?.num_comments,
                it?.created
            )
        )
    }
    return comments
}


fun commentsToCommonEntities(comments: List<RedditPost?>): List<RemoteRedditPost> {
    val entities = arrayListOf<RemoteRedditPost>()
    comments.forEach {
        entities.add(
            RemoteRedditPost(
                it?.thumbnail,
                it?.url,
                it?.title,
                it?.author,
                it?.name,
                it?.num_comments,
                it?.created
            )
        )
    }
    return entities
}