package com.aprian1337.consumerapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.consumerapp.data.model.FavoriteUser
import com.aprian1337.consumerapp.databinding.ItemRowUserWithCircleImageBinding
import com.bumptech.glide.Glide

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.MainViewHolder>() {

    private var favData = mutableListOf<FavoriteUser>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MainViewHolder(val binding: ItemRowUserWithCircleImageBinding) : RecyclerView.ViewHolder(binding.root)

    fun setFavData(listFavData : List<FavoriteUser>){
        favData = listFavData.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRowUserWithCircleImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val favData = favData[position]
        holder.binding.apply {
            username.text = favData.login
            Glide.with(root.context)
                .load(favData.avatar_url)
                .into(imgAvatar)
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback?.onItemClicked(favData)
        }
    }

    override fun getItemCount(): Int = favData.size

    interface OnItemClickCallback {
        fun onItemClicked(data: FavoriteUser)
    }
}