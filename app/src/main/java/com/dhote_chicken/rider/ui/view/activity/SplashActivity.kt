package com.dhote_chicken.rider.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.dhote_chicken.rider.R
import com.dhote_chicken.rider.ui.base.BaseActivity

import com.dhote_chicken.rider.databinding.SplashActivityBinding
import com.dhote_chicken.rider.ui.util.Constant.Companion.USERID
import com.dhote_chicken.rider.ui.viewModel.LoginActivityViewModel


class SplashActivity : BaseActivity<LoginActivityViewModel, SplashActivityBinding>(), Runnable {

    lateinit var handler: Handler

    private val timeOut: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = Handler()
        handler.postDelayed(this, timeOut)
    }


    override fun createViewModel(): LoginActivityViewModel {
        return ViewModelProvider(this, factory).get(LoginActivityViewModel::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun run() {
        if(sharedPrefData.getString(USERID).isNotEmpty()){
            startActivity(Intent(this, HomeActivity::class.java))
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }

}