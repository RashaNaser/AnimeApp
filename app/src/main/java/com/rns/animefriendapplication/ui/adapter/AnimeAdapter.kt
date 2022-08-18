package com.rns.animefriendapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rns.animefriendapplication.R
import com.rns.animefriendapplication.databinding.ItemAnimeListBinding
import com.rns.animefriendapplication.data.response.Data
import com.rns.animefriendapplication.ui.interfaces.OnClickListener

class AnimeAdapter(
    private val dataList: List<Data>,
    private var listener: OnClickListener
) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_anime_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.binding.root).load(dataList[position].images.jpg.image_url)
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.binding.imageCover)

            textTitle.text = dataList[position].title_english

            root.setOnClickListener { listener.onClick(dataList[position]) }
        }
    }

    override fun getItemCount() = dataList.size

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemAnimeListBinding.bind(itemView)
    }
}