package com.dhote_chicken.rider.ui.view.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.dhote_chicken.rider.R
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.location.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.dhote_chicken.rider.data.model.UserInfo
import com.dhote_chicken.rider.databinding.HomeActivityBinding
import com.dhote_chicken.rider.ui.base.BaseActivity
import com.dhote_chicken.rider.ui.viewModel.HomeActivityViewModel
import com.dhote_chicken.rider.ui.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.layout_bottom_nav.view.*
import java.util.*


class HomeActivity : BaseActivity<HomeActivityViewModel, HomeActivityBinding>() {

    private lateinit var controller: NavController // don't forget to initialize
    var userInfo :UserInfo? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = findNavController(R.id.nav_host_fragment)


        observe()
        val bottomBarBackground = binding.navLayout.bottomBar.background as MaterialShapeDrawable
        bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, 70.0f)
            .setTopLeftCorner(CornerFamily.ROUNDED, 70.0f)
            .build()
        binding.navLayout.bottomNavigation.setOnItemSelectedListener { menuItem ->
            Log.v("BottomManu", menuItem.itemId.toString())
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    binding.toolbarModel = setToolbarData(true, "Home", false)
                    navigate(R.id.nav_host_fragment, R.id.navigation_home, null)
                    true
                }

                else -> false
            }
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun observe() {

        val model = ViewModelProvider(this@HomeActivity).get(SharedViewModel::class.java)
        model.sharedData.observe(this) {
            binding.userName = it
        }

        binding.ivLogout.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this)
            builder.setTitle(getString(R.string.title_log_out))
            builder.setMessage(getString(R.string.msg_logout))
            builder.setPositiveButton(
                getString(R.string.action_yes)
            ) { dialog: DialogInterface?, which: Int ->
                sharedPrefData.removeData()
                startActivity(Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
            builder.setNegativeButton(
                getString(R.string.action_no)
            ) { dialog: DialogInterface?, which: Int -> dialog!!.dismiss() }
            builder.show()
        }

    }


    override fun createViewModel(): HomeActivityViewModel {
        return ViewModelProvider(this, factory).get(HomeActivityViewModel::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.activity_home
    }

    /**
     * This listener is used to get fragment destination(Current Fragment)
     */
    private val listener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            Log.v("FragmentChange", destination.displayName)

            if (destination.id == R.id.navigation_home)
                binding.navLayout.visibility = View.GONE
             else  binding.navLayout.visibility = View.GONE

            if (destination.id == R.id.navigation_home) {
                binding.toolbarModel = setToolbarData(true, getDayWish(), false)
                binding.navLayout.bottomNavigation.menu.findItem(R.id.menu_home).isChecked = true
            }else{
                binding.toolbarModel = setToolbarData(false, destination.label.toString(), true)
            }
        }

    override fun onResume() {
        super.onResume()
        controller.addOnDestinationChangedListener(listener)
//        common.checkPermissionValid(this@HomeActivity)
    }

    override fun onPause() {
        controller.removeOnDestinationChangedListener(listener)
        super.onPause()
    }

    override fun onBackPressed() {
        val currentFragment = controller.currentDestination!!.id

        if (currentFragment == R.id.navigation_home){
  /*          val builder = MaterialAlertDialogBuilder(this)
            builder.setTitle(getString(R.string.title_log_out))
            builder.setMessage(getString(R.string.msg_logout))
            builder.setPositiveButton(
                getString(R.string.action_yes)
            ) { dialog: DialogInterface?, which: Int ->
                sharedPrefData.removeData()
                startActivity(Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
            builder.setNegativeButton(
                getString(R.string.action_no)
            ) { dialog: DialogInterface?, which: Int -> dialog!!.dismiss() }
            builder.show()*/
            finishAffinity()
        }else {
            super.onBackPressed()
        }
    }


    private fun getDayWish() : String {
        val calender = Calendar.getInstance()
        val timeOfDay = calender[Calendar.HOUR_OF_DAY]
        val wish =
            if (timeOfDay < 12) "Good Morning" else if (timeOfDay < 16) "Good Afternoon" else if (timeOfDay < 21) "Good Evening" else "Welcome"
        return wish
    }
}