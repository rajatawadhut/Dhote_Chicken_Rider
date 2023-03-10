package com.dhote_chicken.rider.ui.viewModel
import androidx.lifecycle.MutableLiveData
import com.dhote_chicken.rider.ui.base.BaseViewModel
import javax.inject.Inject

class SharedViewModel @Inject constructor(): BaseViewModel(){
   var sharedData = MutableLiveData<String>()
}