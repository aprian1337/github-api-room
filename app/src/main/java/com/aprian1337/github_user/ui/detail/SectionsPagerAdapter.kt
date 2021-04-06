package com.aprian1337.github_user.ui.detail

import android.content.Context
import android.icu.lang.UCharacter.JoiningGroup.BEH
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aprian1337.github_user.R
import com.aprian1337.github_user.ui.detail.follower.FollowerFragment
import com.aprian1337.github_user.ui.detail.follower.FollowingFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager, data: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fBundle: Bundle

    init{
        fBundle = data
    }

    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.tab_follower,
        R.string.tab_following
    )

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0->fragment=FollowerFragment()
            1->fragment= FollowingFragment()
        }
        fragment?.arguments = this.fBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(TAB_TITLES[position])
    }

}