package com.example.domain.repo

import com.example.domain.entity.Post

interface IRepo {
    suspend fun getPosts(): List<Post>
    suspend fun getPost(postId: Int): Post
}