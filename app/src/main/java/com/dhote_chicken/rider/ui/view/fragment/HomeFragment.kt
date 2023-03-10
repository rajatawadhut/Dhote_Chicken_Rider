package com.dhote_chicken.rider.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dhote_chicken.rider.R
import com.dhote_chicken.rider.data.model.*
import com.dhote_chicken.rider.databinding.HomeFragmentBinding
import com.dhote_chicken.rider.ui.adapter.OrdersAdapter
import com.dhote_chicken.rider.ui.base.BaseFragment
import com.dhote_chicken.rider.ui.listener.OrdersListener
import com.dhote_chicken.rider.ui.util.Constant
import com.dhote_chicken.rider.ui.viewModel.HomeFragmentViewModel
import com.dhote_chicken.rider.ui.viewModel.SharedViewModel
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

            if(it.completedOrder.isEmpty() && it.inProgress.isEmpty()){
                binding.llAssigned.visibility = View.GONE
                binding.llCompleted.visibility = View.GONE
                binding.llNoData.visibility = View.VISIBLE

            }else{
                binding.llNoData.visibility = View.GONE
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
        if (sharedPrefData.getString(Constant.USERID) != "") {
            viewModel.getUserData(sharedPrefData.getString(Constant.USERID))
            viewModel.getOrderList(sharedPrefData.getString(Constant.USERID))
        }
        super.onResume()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
        }
    }


}