package com.example.test.ui.shareduistate

import com.example.domain.entity.Post
import java.io.Serializable

data class PostUiState(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String,
): Serializable

fun Post.toUiState():PostUiState{
    return PostUiState(
        userId, id, title, body
    )
}