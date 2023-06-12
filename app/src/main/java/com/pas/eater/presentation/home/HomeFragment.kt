package com.pas.eater.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pas.eater.databinding.FragmentHomeBinding
import com.pas.eater.presentation.home.adapter.CategoryAdapter
import com.pas.eater.presentation.home.adapter.CategoryClickListener
import com.pas.eater.presentation.home.adapter.CategoryEaterItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment(),
    CategoryClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val adapter = CategoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnClickListener(this)


        binding.rvCategory.adapter = adapter

        lifecycleScope.launch {
            viewModel.categories.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickDish(item: CategoryEaterItem) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationCategory(item.name)
        )
    }
}