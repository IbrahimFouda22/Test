package com.example.test.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.databinding.FragmentPostsBinding
import com.example.test.ui.posts.adapter.PostsAdapter
import com.example.test.ui.posts.interaction.PostInteractionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsFragment : Fragment(),PostInteractionListener {
    private lateinit var binding:FragmentPostsBinding
    private val viewModel by viewModels<PostsViewModel> (  )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PostsAdapter(this)
        binding.recyclerPosts.adapter = adapter

        lifecycleScope.launch {
            viewModel.postsState.collectLatest {
                if(!it.error.isNullOrEmpty()) {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }

                if(it.navigateToPostDetails){
                    findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(it.postId))
                    viewModel.navigateToPostDetailsDone()
                }

                if (it.postUiState.isNotEmpty()) {
                    adapter.submitList(it.postUiState)
                }
            }
        }

        return binding.root
    }

    override fun onCLick(postId: Int) {
        viewModel.navigateToPostDetails(postId)
    }

}