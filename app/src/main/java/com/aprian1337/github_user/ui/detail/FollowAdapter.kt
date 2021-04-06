package com.aprian1337.github_user.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.github_user.databinding.ItemRowFollowBinding
import com.aprian1337.github_user.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FollowAdapter: RecyclerView.Adapter<FollowAdapter.MainViewHolder>() {


    private val listUser = ArrayList<User>()

    fun setListUser(alluser: ArrayList<User>){
        listUser.clear()
        listUser.addAll(alluser)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemRowFollowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user : User){
            with(binding) {
                println(user)
                username.text = user.login
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = ItemRowFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder((view))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

}