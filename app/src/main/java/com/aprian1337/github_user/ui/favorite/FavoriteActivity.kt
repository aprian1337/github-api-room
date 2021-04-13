package com.aprian1337.github_user.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.github_user.data.room.FavoriteUser
import com.aprian1337.github_user.databinding.ActivityFavoriteBinding
import com.aprian1337.github_user.ui.detail.DetailUserActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private val adapter by lazy { FavoriteAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite User"
        setupRv()
        viewModel = ViewModelProvider(this@FavoriteActivity).get(FavoriteViewModel::class.java)
        viewModel.setFavDatas(this@FavoriteActivity)
        viewModel.getFavDatas().observe(this, {
            adapter.setFavData(it)
            showLoading(false)
        })
        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(favData: FavoriteUser) {
                selectedUser(favData)
            }
        })
    }

    private fun setupRv() {
        with(binding) {
            rvFavorite.adapter = adapter
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavorite.setHasFixedSize(true)
        }
    }


    fun showLoading(show: Boolean) {
        if (show) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

    private fun selectedUser(favData: FavoriteUser) {
        Intent(this@FavoriteActivity, DetailUserActivity::class.java).apply {
            putExtra(DetailUserActivity.EXTRA_USERNAME, favData.login)
            startActivity(this)
        }
    }
}