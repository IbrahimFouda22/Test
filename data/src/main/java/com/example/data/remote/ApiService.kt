package com.example.data.remote

import com.example.data.dto.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    @GET("posts/")
    suspend fun getPost(
        @Query("id") postId: Int
    ): Response<List<PostDto>>
}