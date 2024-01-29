package com.example.data.remote

import com.example.domain.entity.Post


interface IRemoteDataSource {
    suspend fun getPosts(): List<Post>
    suspend fun getPost(postId: Int): Post
}