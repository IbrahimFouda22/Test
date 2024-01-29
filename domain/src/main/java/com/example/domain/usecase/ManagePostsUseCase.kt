package com.example.domain.usecase

import com.example.domain.entity.Post
import com.example.domain.repo.IRepo
import javax.inject.Inject

class ManagePostsUseCase @Inject constructor(private val iRepo: IRepo) {
    suspend fun getPosts() = iRepo.getPosts()
    suspend fun getPost(postId: Int) = iRepo.getPost(postId)
}