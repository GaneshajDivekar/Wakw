package com.a.demoapplication.ui.mainmodule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.a.demoapplication.data.api.PlaceHolderModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demoapplication.R


class ItemAdapter(private val mCtx: Context) :
    PagedListAdapter<PlaceHolderModel, ItemAdapter.ItemViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(mCtx).inflate(R.layout.single_grid_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.textView.text = item.title

            try {
                val requestOptions = RequestOptions()
                requestOptions.placeholder(R.mipmap.ic_launcher)
                requestOptions.error(R.mipmap.ic_launcher)
                Glide
                    .with(mCtx)
                    .setDefaultRequestOptions(requestOptions)
                    .load(item.thumbnailUrl)
                    .into(holder.imageView)
            } catch (e: Exception) {
                e.printStackTrace()
            }
         /*   Glide.with(mCtx)
                .load(item.thumbnailUrl)
                .into(holder.imageView)
      */
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show()
        }
    }

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var imageView: ImageView

        init {
            textView = itemView.findViewById(R.id.txtGridName)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<PlaceHolderModel> =
            object : DiffUtil.ItemCallback<PlaceHolderModel>() {
                override fun areItemsTheSame(
                    oldItem: PlaceHolderModel,
                    newItem: PlaceHolderModel
                ): Boolean {
                    return oldItem.albumId == newItem.albumId
                }

                override fun areContentsTheSame(
                    oldItem: PlaceHolderModel,
                    newItem: PlaceHolderModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}