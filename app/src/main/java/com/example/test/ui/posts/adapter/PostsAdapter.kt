package com.example.test.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.LayoutItemPostBinding
import com.example.test.ui.posts.interaction.PostInteractionListener
import com.example.test.ui.shareduistate.PostUiState


class PostsAdapter(private val interactionListener: PostInteractionListener): ListAdapter<PostUiState, PostsAdapter.PostsViewHolder>(
    PostsDiffUtil()
) {
    inner class PostsViewHolder(private val layoutItemPostBinding: LayoutItemPostBinding) :RecyclerView.ViewHolder(layoutItemPostBinding.root){
        fun bind(postUiState: PostUiState){
            layoutItemPostBinding.item = postUiState
        }
    }

    class PostsDiffUtil: DiffUtil.ItemCallback<PostUiState>() {
        override fun areItemsTheSame(
            oldItem: PostUiState,
            newItem: PostUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostUiState,
            newItem: PostUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostsViewHolder(LayoutItemPostBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            interactionListener.onCLick(getItem(position).id)
        }
    }
}
