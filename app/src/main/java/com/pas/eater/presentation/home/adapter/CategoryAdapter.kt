package com.pas.eater.presentation.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.pas.eater.databinding.ItemCategoryBinding
import com.pas.eater.presentation.util.EaterItem
import com.pas.eater.presentation.util.EaterItemDiffUtil


class CategoryAdapter :
    ListAdapter<EaterItem, CategoryAdapter.CategoryViewHolder>(EaterItemDiffUtil()) {

    private var listener: CategoryClickListener? = null

    fun setOnClickListener(onClickListener: CategoryClickListener) {
        this.listener = onClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item: CategoryEaterItem = getItem(position) as CategoryEaterItem
        holder.bind(item)
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryEaterItem?) {
            binding.apply {

                Glide.with(binding.root)
                    .load(category?.imageUrl)
                    .into(object : CustomTarget<Drawable?>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable?>?
                        ) {
                            binding.cardItem.background = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {          }

                    })

                tvTitle.text = category?.name
            }
            initButtonsListeners(category)
        }

        private fun initButtonsListeners(tag: CategoryEaterItem?) {
            binding.root.setOnClickListener {
                tag?.let { item -> listener?.onClickDish(item) }
            }

        }
    }
}

interface CategoryClickListener {
    fun onClickDish(item: CategoryEaterItem)
}
