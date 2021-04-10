package com.aprian1337.github_user.ui.detail.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aprian1337.github_user.repository.MainRepository

class FollowerFragmentFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FollowerViewModel(repository) as T
}