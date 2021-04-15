package com.aprian1337.consumerapp.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.consumerapp.data.model.FavoriteUser
import com.aprian1337.consumerapp.databinding.ActivityFavoriteBinding
import com.google.android.material.snackbar.Snackbar

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private val adapter by lazy { FavoriteAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite User (Consumer)"
        setupRv()
        viewModel = ViewModelProvider(this@FavoriteActivity).get(FavoriteViewModel::class.java)
        viewModel.setFavDatas(this@FavoriteActivity)?.observe(this, {
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
        val snackBar = Snackbar.make(binding.root, "${favData.login} selected", Snackbar.LENGTH_LONG)
        snackBar.setAction("Dismiss"){
            snackBar.dismiss()
        }.show()
    }
}