package com.iitms.bfr.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.google.gson.Gson
import com.iitms.bfr.R
import com.iitms.bfr.data.model.*
import com.iitms.bfr.databinding.HomeFragmentBinding
import com.iitms.bfr.ui.adapter.OrdersAdapter
import com.iitms.bfr.ui.base.BaseFragment
import com.iitms.bfr.ui.listener.OrdersListener
import com.iitms.bfr.ui.listener.SubCategoryListener
import com.iitms.bfr.ui.util.Constant
import com.iitms.bfr.ui.viewModel.HomeFragmentViewModel
import com.iitms.bfr.ui.viewModel.SharedViewModel
import javax.inject.Inject


class HomeFragment @Inject constructor() :
    BaseFragment<HomeFragmentViewModel, HomeFragmentBinding>(), View.OnClickListener {

    @Inject
    lateinit var ordersAdapterIncoming: OrdersAdapter

    @Inject
    lateinit var ordersAdapterDelivered: OrdersAdapter

    var user: User? = null
    var sharedViewModel: SharedViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        observe()
    }


    private fun observe() {
        if (sharedPrefData.getString(Constant.USERID) != "") {
            viewModel.getUserData(sharedPrefData.getString(Constant.USERID))
            viewModel.getOrderList(sharedPrefData.getString(Constant.USERID))
        }

        viewModel.userData.observe(viewLifecycleOwner, Observer {
            if (it.user != null) {
                sharedPrefData.saveData(Constant.USERID, it.user!!.id.toString())
                sharedViewModel!!.sharedData.postValue(it.user!!.givenName)
            }
        })
        viewModel.homePageOrder.observe(viewLifecycleOwner, Observer {
            if (it?.completedOrder != null && it.completedOrder.isNotEmpty()) {
                binding.llCompleted.visibility = View.VISIBLE
                binding.completedAdapter = ordersAdapterDelivered
                ordersAdapterDelivered.addOrders(
                    it.completedOrder as ArrayList<OrderData>,
                    object : OrdersListener {
                        override fun onOrdersClick(order: OrderData) {
                            val bundle = Bundle()
                            bundle.putSerializable("OrderData", order)
                            navigate(R.id.navigation_order_status, bundle)
                        }
                    })
            } else {
                binding.llCompleted.visibility = View.GONE
            }

            if (it?.inProgress != null && it.inProgress.isNotEmpty()) {
                binding.llAssigned.visibility = View.VISIBLE
                binding.assignedAdapter = ordersAdapterIncoming
                ordersAdapterIncoming.addOrders(
                    it.inProgress as ArrayList<OrderData>,
                    object : OrdersListener {
                        override fun onOrdersClick(order: OrderData) {
                            val bundle = Bundle()
                            bundle.putSerializable("OrderData", order)
                            navigate(R.id.navigation_order_status, bundle)
                        }
                    })
            } else {
                binding.llAssigned.visibility = View.GONE
            }

        })




        viewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }

    }


    override fun createViewModel(): HomeFragmentViewModel {
        return ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onResume() {
        common.checkPermissionValid(requireContext())
        super.onResume()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
        }
    }


}