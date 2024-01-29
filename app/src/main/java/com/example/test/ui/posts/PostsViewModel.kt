package com.example.test.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Post
import com.example.domain.usecase.ManagePostsUseCase
import com.example.test.ui.shareduistate.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val managePostsUseCase: ManagePostsUseCase
) : ViewModel() {

    private val _postsState = MutableStateFlow(PostsUiState())
    val postsState = _postsState.asStateFlow()

    init {
        getPosts()
    }

    private fun getPosts() {
        _postsState.update {
            it.copy(isLoading = true, error = null)
        }

        viewModelScope.launch {
            try {
                onGetPostsSuccess(managePostsUseCase.getPosts())
            } catch (e: Exception) {
                onGetPostsFailure(e.message.toString())
            }
        }
    }

    private fun onGetPostsSuccess(posts: List<Post>) {
        _postsState.update {
            it.copy(isLoading = false, postUiState = posts.map { post->
                post.toUiState()
            })
        }
    }
    private fun onGetPostsFailure(error: String?) {
        _postsState.update {
            it.copy(isLoading = false, postUiState = emptyList() , error = error)
        }
    }

    fun navigateToPostDetails(postId:Int){
        _postsState.update {
            it.copy(navigateToPostDetails = true, postId = postId)
        }
    }

    fun navigateToPostDetailsDone(){
        _postsState.update {
            it.copy(navigateToPostDetails = false, postId = -1)
        }
    }
}