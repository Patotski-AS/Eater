package com.pas.eater.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pas.eater.R
import com.pas.eater.databinding.ActivityMainBinding
import com.pas.eater.databinding.FragmentCategoryBinding
import com.pas.eater.presentation.MainActivity
import com.pas.eater.presentation.category.adapter.DishAdapter
import com.pas.eater.presentation.category.adapter.DishClickListener
import com.pas.eater.presentation.category.adapter.DishEaterItem
import com.pas.eater.presentation.category.adapter.TagAdapter
import com.pas.eater.presentation.category.adapter.TagClickListener
import com.pas.eater.presentation.category.adapter.TagEaterItem
import com.pas.eater.presentation.util.toPrice
import com.pas.eater.presentation.util.toWeight
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment: Fragment(),
    TagClickListener,
    DishClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoryViewModel by viewModels()
    private val tagAdapter = TagAdapter()
    private val dishAdapter = DishAdapter()
    private var activityBinding: ActivityMainBinding? = null
    private val categoryTitle by lazy {CategoryFragmentArgs.fromBundle(requireArguments()).title}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val activity = requireActivity() as MainActivity
        activityBinding = activity.binding

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if(activityBinding?.detail?.root?.isVisible == true) viewModel.setDishDetail(null)
            else findNavController().navigateUp()
        }

        tagAdapter.setOnClickListener(this)
        dishAdapter.setOnClickListener(this)


        binding.rvTag.adapter = tagAdapter
        binding.rvDish.adapter = dishAdapter
        binding.toolbar.toolbarCategory.apply {
            setNavigationOnClickListener {findNavController().navigateUp()}
            title = categoryTitle
        }

        lifecycleScope.launch {
            viewModel.tags.collectLatest {
                tagAdapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            viewModel.dishes.collectLatest {
                dishAdapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            viewModel.dishDetail.collectLatest {item->
                activityBinding?.detail?.apply {
                    if(item == null) {
                        root.visibility = View.GONE
                    } else {
                        root.visibility = View.VISIBLE

                        Glide.with(binding.root)
                            .load(item.imageUrl)
                            .placeholder(R.drawable.loading_animation)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .fallback(R.drawable.ic_dining)
                            .fitCenter()
                            .into(imageView)
                        tvPrice.text = item.price.toPrice()
                        tvWeight.text = item.weight.toWeight()
                        tvDescription.text = item.description
                        tvTitle.text = item.name
                    }
                }
            }
        }
        activityBinding?.let {
            it.detail.apply {
                ivFavorite.setOnClickListener { }
                ivClose.setOnClickListener {viewModel.setDishDetail(null)}
                button.setOnClickListener {viewModel.addToBasket()}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickTag(item: TagEaterItem) {
        viewModel.setTag(item.tag)
    }

    override fun onClickDish(item: DishEaterItem) {
        viewModel.setDishDetail(item)
    }
}


