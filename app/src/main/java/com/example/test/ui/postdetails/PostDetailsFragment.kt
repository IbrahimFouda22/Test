package com.example.test.ui.postdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.test.R
import com.example.test.databinding.FragmentPostDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private lateinit var binding:FragmentPostDetailsBinding
    private val navArgs by navArgs<PostDetailsFragmentArgs>()
    @Inject lateinit var factory: PostViewModel.PostAssistedFactory
    private val viewModel by viewModels<PostViewModel>{
        PostViewModel.createFactory(factory,navArgs.postId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostDetailsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}