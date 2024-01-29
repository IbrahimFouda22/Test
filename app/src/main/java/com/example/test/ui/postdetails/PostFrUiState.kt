package com.example.test.ui.postdetails

import com.example.test.ui.shareduistate.PostUiState

data class PostFrUiState(
    val isLoading:Boolean = false,
    val error:String? = null,
    val postUiState: PostUiState? = null,
)
