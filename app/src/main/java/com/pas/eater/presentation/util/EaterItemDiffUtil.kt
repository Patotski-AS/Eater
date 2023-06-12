package com.pas.eater.presentation.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil


class EaterItemDiffUtil: DiffUtil.ItemCallback<EaterItem>() {
    override fun areItemsTheSame(oldItem: EaterItem, newItem: EaterItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: EaterItem, newItem: EaterItem): Boolean {
        return oldItem == newItem
    }
}