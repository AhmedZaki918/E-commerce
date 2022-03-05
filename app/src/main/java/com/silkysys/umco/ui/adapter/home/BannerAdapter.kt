package com.silkysys.umco.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.silkysys.umco.R
import com.silkysys.umco.data.model.sliders.DataItem
import com.silkysys.umco.util.setupPicasso

class BannerAdapter(private var banners: List<DataItem>) :
    RecyclerView.Adapter<BannerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_banner, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Display the image by Picasso library
        setupPicasso(banners[position].image_url, holder.ivContent)
    }

    override fun getItemCount() = banners.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivContent: ImageView = itemView.findViewById(R.id.iv_content)
    }
}