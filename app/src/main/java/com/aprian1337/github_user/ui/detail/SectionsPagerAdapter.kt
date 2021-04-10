package com.aprian1337.github_user.ui.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aprian1337.github_user.ui.detail.follow.FollowFragment

class SectionsPagerAdapter(private val username : String, activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return FollowFragment.newInstance(position, username)
    }

}