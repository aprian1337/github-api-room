package com.aprian1337.github_user.ui.detail.follow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.github_user.databinding.ItemRowFollowBinding
import com.aprian1337.github_user.model.User
import com.bumptech.glide.Glide

class FollowAdapter : RecyclerView.Adapter<FollowAdapter.MainViewHolder>() {

    private var follows = mutableListOf<User>()

    inner class MainViewHolder(val binding: ItemRowFollowBinding) : RecyclerView.ViewHolder(binding.root)

    fun setFollows(listUser : List<User>){
        follows = listUser.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRowFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val users = follows[position]
        holder.binding.apply {
            username.text = users.login
            Glide.with(holder.itemView.context)
                .load(users.avatar_url)
                .into(imgAvatar)
        }
    }

    override fun getItemCount(): Int = follows.size
}