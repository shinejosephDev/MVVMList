package com.test.mvvmlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.loadimage.LoadImage
import com.test.mvvmlist.databinding.ItemListBinding
import com.test.mvvmlist.domain.model.ResultsItem

class ItemsAdapter : ListAdapter<ResultsItem, ItemsAdapter.ViewHolder>(DataDiffCallBack()) {

    var onItemClick: ((ResultsItem) -> Unit)? = null

    private class DataDiffCallBack : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
            oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(item: ResultsItem) {
            binding.tvName.text = item.name

            item.image_urls_thumbnails?.get(0)?.let { LoadImage.displayImage(it,binding.imageView) }

//            Glide.with(binding.imageView).load(item.image_urls_thumbnails?.get(0))
//                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}