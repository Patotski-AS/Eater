package com.pas.eater.presentation.basket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pas.eater.R
import com.pas.eater.databinding.ItemBasketBinding
import com.pas.eater.presentation.util.EaterItem
import com.pas.eater.presentation.util.EaterItemDiffUtil
import com.pas.eater.presentation.util.toPrice
import com.pas.eater.presentation.util.toWeight

class BasketAdapter: ListAdapter<EaterItem, BasketAdapter.BasketViewHolder>(EaterItemDiffUtil()) {

    private var listener: BasketClickListener? = null

    fun setOnClickListener(onClickListener: BasketClickListener) {
        this.listener = onClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBasketBinding.inflate(layoutInflater, parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val item: BasketEaterItem = getItem(position) as BasketEaterItem
        holder.bind(item)
    }

    inner class BasketViewHolder(
        private val binding: ItemBasketBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BasketEaterItem?) {
            binding.apply {

                Glide.with(binding.root)
                    .load(item?.imageUrl)
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .fallback(R.drawable.ic_dining)
                    .fitCenter()
                    .into(binding.ivImage)

                item?.let {item->
                    tvCount.text = item.count.toString()
                    tvPrice.text = item.price.toPrice()
                    tvWeight.text = item.weight.toWeight()
                    tvTitle.text = item.name
                }
            }
            initButtonsListeners(item)
        }

        private fun initButtonsListeners(tag: BasketEaterItem?) {
            binding.ivMinus.setOnClickListener {
                tag?.let {item-> listener?.onClickMinus(item)}
            }

            binding.ivPlus.setOnClickListener {
                tag?.let {item-> listener?.onClickPlus(item)}
            }

        }
    }
}

interface BasketClickListener {
    fun onClickPlus(item: BasketEaterItem)
    fun onClickMinus(item: BasketEaterItem)
}
