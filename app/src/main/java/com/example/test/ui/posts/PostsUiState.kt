package com.example.test.ui.posts

import com.example.test.ui.shareduistate.PostUiState

data class PostsUiState(
    val isLoading:Boolean = false,
    val error:String? = null,
    val postId:Int = -1,
    val postUiState: List<PostUiState> = emptyList(),
    val navigateToPostDetails: Boolean = false
)
