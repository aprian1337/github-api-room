package com.aprian1337.github_user.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aprian1337.github_user.User
import com.aprian1337.github_user.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER = "extra_user"
    }
    private lateinit var binding : ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle = Bundle()
        val user = intent.getStringExtra(EXTRA_USER)
        bundle.putString(EXTRA_USER, user)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        if (user != null) {
            viewModel.setDetail(user)
        }

        viewModel.getDetail().observe(this, {
            if(it!=null){
                binding.apply {
                    tvUsername.text = it.login
                    tvName.text = it.name
                    tvCompany.text = it.company ?: "null"
                    tvLocation.text = it.location ?: "null"
                    tvRepository.text = "${it.public_repos}"
                    tvFollowers.text = "${it.followers}"
                    tvFollowing.text = "${it.following}"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .dontAnimate()
                        .into(imgPhoto)
                }
            }
        })

        val sectionsPagerAdapter  = SectionsPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            pager.adapter = sectionsPagerAdapter
            tab.setupWithViewPager(pager)
        }

    }
}