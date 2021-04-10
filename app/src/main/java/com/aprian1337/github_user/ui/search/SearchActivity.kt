package com.aprian1337.github_user.ui.search

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.github_user.R
import com.aprian1337.github_user.api.ApiClient
import com.aprian1337.github_user.databinding.ActivitySearchBinding
import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.repository.MainRepository
import com.aprian1337.github_user.ui.detail.DetailUserActivity
import kotlinx.coroutines.*

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private val adapter by lazy { SearchAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val emptyMessage : String = applicationContext.getString(R.string.empty_data)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, SearchViewModelFactory(MainRepository(ApiClient))).get(
            SearchViewModel::class.java
        )
        setupRv()

        with(binding) {
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        showLoading(true)
                        CoroutineScope(Dispatchers.Default).launch {
                            viewModel.setSearchUsers(query)
                            runOnUiThread{
                                showLoading(true)
                            }
                        }

                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
            viewModel.getSearchUser().observe(this@SearchActivity, {
                if(it.isNullOrEmpty()){
                    Toast.makeText(applicationContext, emptyMessage, Toast.LENGTH_LONG).show()
                    showLoading(false)
                }else {
                    adapter.setUsers(it)
                    showLoading(false)
                    binding.apply {
                        subtitle.visibility = View.GONE
                        imageView.visibility = View.GONE
                    }
                }
            })
        }

        adapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                selectedUser(data)
            }
        })

    }

    fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBars.visibility = View.VISIBLE
        } else {
            binding.progressBars.visibility = View.GONE
        }
    }

    private fun setupRv() {
        with(binding) {
            rvUser.adapter = adapter
            rvUser.layoutManager = LinearLayoutManager(this@SearchActivity)
            rvUser.setHasFixedSize(true)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_1) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun selectedUser(user: User) {
        Intent(this@SearchActivity, DetailUserActivity::class.java).apply {
            putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
            startActivity(this)
        }
    }
}