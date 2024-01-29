package com.example.data.remote

import android.content.res.Resources.NotFoundException
import com.example.data.mapper.toEntity
import com.example.domain.entity.Post
import com.example.domain.exception.EmptyDataException
import com.example.domain.exception.InternalServerException
import com.example.domain.exception.NoInternetException
import com.example.domain.exception.ServerException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: ApiService) : IRemoteDataSource {

    override suspend fun getPosts(): List<Post> {
        return wrapApiResponse {
            apiService.getPosts()
        }.toEntity()
    }

    override suspend fun getPost(postId: Int): Post {
        return wrapApiResponse {
            apiService.getPost(postId)
        }.toEntity()[0]
    }

    private suspend fun <T> wrapApiResponse(
        request: suspend () -> Response<T>
    ): T {
        try {
            val response = request()
            if (response.isSuccessful) {
                return response.body() ?: throw EmptyDataException("No data")
            } else {
                throw when (response.code()) {
                    404 -> {
                        NotFoundException("Not Found")
                    }

                    500 -> {
                        InternalServerException("Internal Server Error")
                    }

                    else -> ServerException("Server error")
                }
            }
        } catch (e: UnknownHostException) {
            throw NoInternetException("No Internet")
        } catch (io: IOException) {
            throw NoInternetException(io.message)
        } catch (e: SocketTimeoutException) {
            throw NoInternetException("Time out,No internet connection")
        }
    }
}