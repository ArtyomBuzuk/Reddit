package com.artyombuzuk.reddit.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artyombuzuk.reddit.R
import com.artyombuzuk.reddit.databinding.RedditPostViewBinding
import com.artyombuzuk.reddit.model.RedditPost
import com.artyombuzuk.reddit.utils.getHours
import com.bumptech.glide.Glide

class RedditPostAdapter(
    private val onClickRedditPostDetails: (redditPost: RedditPost) -> Unit,
    private val shouldShowDetails: (redditPost: RedditPost) -> Boolean
) : RecyclerView.Adapter<RedditPostAdapter.RedditPostViewHolder>() {

    private val items: MutableList<RedditPost> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingRedditPost = RedditPostViewBinding.inflate(layoutInflater)

        return RedditPostViewHolder(bindingRedditPost)
    }

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.bind(item = items[position], onClickRedditPostDetails, shouldShowDetails)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun setItems(newItems: List<RedditPost>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<RedditPost>) {
        val lastPosition = items.size - 1
        items.addAll(newItems)
        notifyItemRangeChanged(lastPosition, items.size)
    }

    fun getLastItemId(): String? {
        return items.last().name
    }

    class RedditPostViewHolder(
        val binding: RedditPostViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: RedditPost,
            onClickRedditPostDetails: (redditPost: RedditPost) -> Unit,
            shoulShowDetails: (redditPost: RedditPost) -> Boolean
        ) {
            binding.listItem.setOnClickListener {
                onClickRedditPostDetails(item)
            }
            Glide.with(itemView)
                .load(item.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.redditThumbnail)
            binding.redditPostName.text = item.title
            binding.itemComments.text = item.num_comments.toString()
            binding.itemPostTime.text = item.created.getHours()
            binding.itemPostAuthor.text = item.author
        }
    }
}