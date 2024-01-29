package com.example.test.ui.postdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Post
import com.example.domain.usecase.ManagePostsUseCase
import com.example.test.ui.shareduistate.toUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel @AssistedInject constructor(
    @Assisted private val postId: Int,
    private val managePostsUseCase: ManagePostsUseCase
) : ViewModel() {

    private val _postState = MutableStateFlow(PostFrUiState())
    val postState = _postState.asStateFlow()

    init {
        getPost(postId)
    }

    private fun getPost(postId: Int) {
        _postState.update {
            it.copy(isLoading = true, error = null)
        }

        viewModelScope.launch {
            try {
                onGetPostSuccess(managePostsUseCase.getPost(postId))
            } catch (e: Exception) {
                onGetPostFailure(e.message.toString())
            }
        }
    }

    private fun onGetPostSuccess(post: Post) {
        _postState.update {
            it.copy(isLoading = false, postUiState = post.toUiState())
        }
    }
    private fun onGetPostFailure(error: String?) {
        _postState.update {
            it.copy(isLoading = false, postUiState = null , error = error)
        }
    }
    @AssistedFactory
    interface PostAssistedFactory{
        fun create(postId: Int):PostViewModel
    }

    companion object{
        fun createFactory(assistedFactory: PostAssistedFactory,postId: Int):ViewModelProvider.Factory{
            return object :ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(postId) as T
                }
            }
        }
    }
}