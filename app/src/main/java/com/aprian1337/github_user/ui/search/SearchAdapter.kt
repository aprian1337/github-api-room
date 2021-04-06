package com.aprian1337.github_user.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.MainViewHolder>() {


    private val listUser = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListUser(alluser: ArrayList<User>){
        listUser.clear()
        listUser.addAll(alluser)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user : User){
            binding.root.setOnClickListener{onItemClickCallback?.onItemClicked(user)}
            with(binding) {
                println(user)
                tvUsername.text = user.login
                tvUrl.text = user.html_url
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgAvatar)
//                itemView.setOnClickListener{onItemClickCallback?.onItemClicked(user)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder((view))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}