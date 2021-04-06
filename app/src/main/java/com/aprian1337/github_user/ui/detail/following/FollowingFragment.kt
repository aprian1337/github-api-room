package com.aprian1337.github_user.ui.detail.follower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.github_user.R
import com.aprian1337.github_user.databinding.FragmentFollowBinding
import com.aprian1337.github_user.ui.detail.DetailUserActivity
import com.aprian1337.github_user.ui.detail.following.FollowingViewModel
import com.aprian1337.github_user.ui.search.FollowAdapter
import com.aprian1337.github_user.ui.search.SearchAdapter

class FollowingFragment : Fragment(R.layout.fragment_follow) {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FollowingViewModel
    private lateinit var adapter : FollowAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USER).toString()
        _binding = FragmentFollowBinding.bind(view)
        adapter = FollowAdapter()
        adapter.notifyDataSetChanged()
        with(binding){
            rvFollow.setHasFixedSize(true)
            rvFollow.layoutManager = LinearLayoutManager(activity)
            rvFollow.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setFollowing(username)
        viewModel.getFollowing().observe(viewLifecycleOwner, {
            if(it!=null){
                adapter.setListUser(it)
                showLoading(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }
}
