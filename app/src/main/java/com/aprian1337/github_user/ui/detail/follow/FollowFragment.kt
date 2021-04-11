package com.aprian1337.github_user.ui.detail.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.github_user.R
import com.aprian1337.github_user.data.api.ApiClient
import com.aprian1337.github_user.databinding.FragmentFollowBinding
import com.aprian1337.github_user.repository.MainRepository

class FollowFragment : Fragment() {

    companion object {
        private const val INDEX_TABS = "index_tabs"
        private const val USERNAME = "username"

        fun newInstance(idx: Int, username: String) =
            FollowFragment().apply {
                arguments = Bundle().apply {
                    putInt(INDEX_TABS, idx)
                    putString(USERNAME, username)
                }
            }
    }

    private lateinit var followingViewModel: FollowingViewModel
    private lateinit var followerViewModel: FollowerViewModel
    private val followsAdapter by lazy { FollowAdapter() }
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idxTabs = arguments?.getInt(INDEX_TABS)
        val username = arguments?.getString(USERNAME)
        _binding = FragmentFollowBinding.bind(view)
        
        if (idxTabs == 0) {
            setupRvFollowers()
            followerViewModel = ViewModelProvider(
                this, FollowerFragmentFactory(
                    MainRepository(ApiClient)
                )
            ).get(FollowerViewModel::class.java)
            if (username != null) {
                followerViewModel.setFollowers(username)
                showLoading(true)
            }
            followerViewModel.getFollowers().observe(viewLifecycleOwner, {
                followsAdapter.setFollows(it)
                showLoading(false)
            })
        } else {
            setupRvFollowers()
            followingViewModel = ViewModelProvider(
                this, FollowingFragmentFactory(
                    MainRepository(ApiClient)
                )
            ).get(FollowingViewModel::class.java)

            if (username != null) {
                followingViewModel.setFollowing(username)
                showLoading(true)
            }
            followingViewModel.getFollowing().observe(viewLifecycleOwner, {
                followsAdapter.setFollows(it)
                showLoading(false)
            })
        }
    }


    private fun setupRvFollowers() {
        with(binding) {
            rvFollow.adapter = followsAdapter
            rvFollow.layoutManager = LinearLayoutManager(activity)
            rvFollow.setHasFixedSize(true)
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }
}