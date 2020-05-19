package com.a.demoapplication.ui.mainmodule.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.a.demoapplication.PicassoTrustAll
import com.a.demoapplication.data.api.PlaceHolderModel
import com.a.demoapplication.ui.mainmodule.MainActivity
import com.bumptech.glide.Glide
import com.demoapplication.R
import com.squareup.picasso.Picasso


class PlaceHolderAdapter(
    var placeholdersList: ArrayList<PlaceHolderModel>,
    var context: Context,
    var mainNavigator: MainActivity
) : RecyclerView.Adapter<PlaceHolderAdapter.CartItemDetailViewHolder>() {


   /* class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgGrid: ImageView? = null
        var txtGridName: TextView? = null

        init {
            imgGrid = itemView.imgGrid
            txtGridName = itemView.txtGridName
        }
    }
*/
    inner class CartItemDetailViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtGridName: TextView = itemView.findViewById(R.id.txtGridName)
        var imgGrid: ImageView = itemView.findViewById(R.id.imgGrid)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceHolderAdapter.CartItemDetailViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.single_grid_view, parent, false)

        return CartItemDetailViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return placeholdersList.size

    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: PlaceHolderAdapter.CartItemDetailViewHolder, position: Int) {
        var placeHolderModel = placeholdersList.get(position)
        holder.txtGridName.text = placeholdersList.get(position).title

        if (placeholdersList.get(position).thumbnailUrl.equals("")|| placeholdersList.get(position).thumbnailUrl==null){
            holder.imgGrid.setImageResource(R.drawable.ic_no_image)
        }else {
            var url=placeholdersList.get(position).thumbnailUrl/*+".png"*/
            PicassoTrustAll.getInstance(context)
                .load(url)
                .into(holder.imgGrid);

/*
            Glide.with(context)
                .load(aUrl)
                .placeholder(R.drawable.ic_no_image)
                .into(holder.imgGrid)
*/
        }


        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mainNavigator.passdataactivity(placeHolderModel)
            }

        })


    }
    fun addLoading() {

        placeholdersList.add(PlaceHolderModel())
        notifyItemInserted(placeholdersList.size- 1)
    }

    fun removeLoading() {

        val position: Int = placeholdersList.size - 1
        val item: PlaceHolderModel? = getItem(position)
        if (item != null) {
            placeholdersList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
    fun getItem(position: Int): PlaceHolderModel? {
        return placeholdersList.get(position)
    }

}