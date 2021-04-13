package com.aprian1337.github_user.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.aprian1337.github_user.R
import com.aprian1337.github_user.data.api.ApiClient
import com.aprian1337.github_user.data.room.FavoriteUser
import com.aprian1337.github_user.databinding.ActivityDetailUserBinding
import com.aprian1337.github_user.repository.MainRepository
import com.aprian1337.github_user.ui.setting.SettingActivity
import com.aprian1337.github_user.ui.favorite.FavoriteActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.runBlocking

class DetailUserActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.tab_following
        )
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var favoriteUser: FavoriteUser
    private var flag: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        supportActionBar?.title = "Detail User"
        setContentView(binding.root)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        viewModel = ViewModelProvider(this, DetailViewModelFactory(MainRepository(ApiClient))).get(
            DetailUserViewModel::class.java
        )
        showLoading(true)
        if (username != null) {
            viewModel.setUser(username)
        }
        runBlocking {
            viewModel.getUser().observe(this@DetailUserActivity, {
                if (it != null) {
                    binding.apply {
                        tvName.text = it.name
                        tvFollowing.text = it.following.toString()
                        tvFollowers.text = it.followers.toString()
                        tvRepository.text = it.public_repos.toString()
                        if (it.location == null) {
                            tvLocation.visibility = View.GONE
                        } else {
                            tvLocation.text = it.location
                        }
                        tvUsername.text = it.login
                        if (it.company == null) {
                            tvCompany.visibility = View.GONE
                        } else {
                            tvCompany.text = it.company
                        }
                        Glide.with(this@DetailUserActivity)
                            .load(it.avatar_url)
                            .into(imgPhoto)
                    }
                    favoriteUser = FavoriteUser(it.id, it.login, it.avatar_url)
                }

                viewModel.getFav(favoriteUser.id, this@DetailUserActivity).observe(this@DetailUserActivity, {check->
                    if(check == 1){
                        binding.favBtn.setImageDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.ic_baseline_favorite_24
                            )
                        )
                        viewModel.addFav(favoriteUser, this@DetailUserActivity)
                        flag = 1
                    }
                })

                showLoading(false)

            })
        }


        binding.favBtn.setOnClickListener {
            if (flag == 1) {
                binding.favBtn.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_favorite_border_24
                    )
                )
                viewModel.deleteFav(favoriteUser, this)

                val snackBar = Snackbar.make(
                    it, "User removed from favorite",
                    Snackbar.LENGTH_LONG
                )
                with(snackBar) {
                    this.setAction("DISMISS"){
                        this.dismiss()
                    }
                    this.show()
                }
                flag = 0
            } else {
                binding.favBtn.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_favorite_24
                    )
                )
                viewModel.addFav(favoriteUser, this)
                val snackBar = Snackbar.make(
                    it, "User successfully added to favorite",
                    Snackbar.LENGTH_LONG
                )
                with(snackBar) {
                    this.setAction("DISMISS"){
                        this.dismiss()
                    }
                    this.show()
                }
                flag = 1
            }
        }

        val sectionsPagerAdapter =
            username?.let { SectionsPagerAdapter(it, this@DetailUserActivity) }
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tab
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBars.visibility = View.VISIBLE
        } else {
            binding.progressBars.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_fav){
            Intent(this@DetailUserActivity, FavoriteActivity::class.java).apply {
                startActivity(this)
            }
        }else if(item.itemId == R.id.menu_setting){
            Intent(this@DetailUserActivity, SettingActivity::class.java).apply {
                startActivity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}