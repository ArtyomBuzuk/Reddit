package com.artyombuzuk.reddit.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artyombuzuk.reddit.R
import com.artyombuzuk.reddit.model.RedditPost
import com.artyombuzuk.reddit.utils.getHours

class FavoriteAdapter : ListAdapter<RedditPost, FavoriteAdapter.MyViewHolder>(
    FavoriteDiffCallBacks()
) {

    var onClick: ((RedditPost) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.favorite_post_view, parent, false)
    )


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = getItem(position)
//        holder.thumbnail = item.thumbnail
        holder.title.text = "${item?.title}"
        holder.author.text = "${item?.author}"
        holder.postTime.text = item.created.getHours()
//        holder.comments = item.num_comments.toString()

        holder.favorite.setOnClickListener {
            item?.let { it1 -> onClick?.invoke(it1) }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnail: AppCompatTextView = itemView.findViewById(R.id.reddit_thumbnail)
        val title: AppCompatTextView = itemView.findViewById(R.id.reddit_post_name)
        val author: AppCompatTextView = itemView.findViewById(R.id.itemPostAuthor)
        var postTime: AppCompatTextView = itemView.findViewById(R.id.itemPostTime)
        var comments: AppCompatImageView = itemView.findViewById(R.id.itemComments)
        val favorite: AppCompatImageView = itemView.findViewById(R.id.favorite)
    }
}

private class FavoriteDiffCallBacks : DiffUtil.ItemCallback<RedditPost>() {

    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return newItem == oldItem
    }
}