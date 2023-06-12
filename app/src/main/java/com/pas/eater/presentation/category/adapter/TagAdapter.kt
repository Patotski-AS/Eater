package com.pas.eater.presentation.category.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pas.eater.databinding.ItemTagBinding
import com.pas.eater.presentation.util.EaterItem
import com.pas.eater.presentation.util.EaterItemDiffUtil

class TagAdapter: ListAdapter<EaterItem, TagAdapter.TagViewHolder>(EaterItemDiffUtil()) {

    private var listener: TagClickListener? = null

    fun setOnClickListener(onClickListener: TagClickListener) {
        this.listener = onClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTagBinding.inflate(layoutInflater, parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val item: TagEaterItem = getItem(position) as TagEaterItem
        holder.bind(item)
    }

    inner class TagViewHolder(
        private val binding: ItemTagBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: TagEaterItem?) {
            binding.apply {
                tag?.let {item->
                    tvItem.text = item.tag
                    if(tag.onSelect){
                        flBackground.setBackgroundColor(Color.parseColor("#3364E0"))
                        tvItem.setTextColor(Color.WHITE)
                    }else{
                        flBackground.setBackgroundColor(Color.parseColor("#F8F7F5"))
                        tvItem.setTextColor(Color.BLACK)
                    }
                }
            }
            initButtonsListeners(tag)
        }

        private fun initButtonsListeners(tag: TagEaterItem?) {
            binding.flBackground.setOnClickListener {
                tag?.let {item-> listener?.onClickTag(item)}
            }
        }
    }
}

interface TagClickListener {
    fun onClickTag(item: TagEaterItem)
}
