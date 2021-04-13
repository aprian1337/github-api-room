package com.aprian1337.github_user.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.github_user.databinding.ItemRowUserBinding
import com.aprian1337.github_user.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.MainViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MainViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    private var users = mutableListOf<User>()

    fun setUsers(listUser : List<User>){
        users = listUser.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val users = users[position]
        with(holder.binding) {
            tvUsername.text = users.login
            tvUrl.text = users.html_url
            Glide.with(root.context)
                .load(users.avatar_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgAvatar)
            }

        holder.itemView.setOnClickListener{
            onItemClickCallback?.onItemClicked(users)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}