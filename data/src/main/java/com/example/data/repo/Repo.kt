package com.example.data.repo

import com.example.data.remote.RemoteDataSource
import com.example.domain.entity.Post
import com.example.domain.repo.IRepo
import javax.inject.Inject

class Repo @Inject constructor(private val remoteDataSource: RemoteDataSource) : IRepo {
    override suspend fun getPosts() = remoteDataSource.getPosts()
    override suspend fun getPost(postId: Int) = remoteDataSource.getPost(postId)


}