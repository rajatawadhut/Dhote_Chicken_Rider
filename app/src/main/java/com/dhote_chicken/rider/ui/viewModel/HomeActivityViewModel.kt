package com.dhote_chicken.rider.ui.viewModel

import androidx.lifecycle.LiveData
import com.dhote_chicken.rider.data.model.UserInfo
import com.dhote_chicken.rider.data.repository.HomeActivityRepository
import com.dhote_chicken.rider.ui.base.BaseViewModel
import javax.inject.Inject

class HomeActivityViewModel  @Inject constructor(private val repository: HomeActivityRepository
) : BaseViewModel() {

    fun getUserInfo(): LiveData<UserInfo> = repository.getUserInfo()



}