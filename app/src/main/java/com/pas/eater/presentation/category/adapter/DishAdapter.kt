package com.pas.eater.presentation.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pas.eater.R
import com.pas.eater.databinding.ItemDishBinding
import com.pas.eater.presentation.util.EaterItem
import com.pas.eater.presentation.util.EaterItemDiffUtil

class DishAdapter: ListAdapter<EaterItem, DishAdapter.DishViewHolder>(EaterItemDiffUtil()) {

    private var listener: DishClickListener? = null

    fun setOnClickListener(onClickListener: DishClickListener) {
        this.listener = onClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDishBinding.inflate(layoutInflater, parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val item: DishEaterItem = getItem(position) as DishEaterItem
        holder.bind(item)
    }

    inner class DishViewHolder(
        private val binding: ItemDishBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(dish: DishEaterItem?) {
            binding.apply {

                Glide.with(binding.root)
                    .load(dish?.imageUrl)
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fallback(R.drawable.ic_dining)
                    .fitCenter()
                    .into(binding.ivDish)

                dish?.let {item->
                    tvDescription.text = item.name
                }
            }
            initButtonsListeners(dish)
        }

        private fun initButtonsListeners(tag: DishEaterItem?) {
            binding.root.setOnClickListener {
                tag?.let {item-> listener?.onClickDish(item)}
            }

        }
    }
}

interface DishClickListener {
    fun onClickDish(item: DishEaterItem)
}
