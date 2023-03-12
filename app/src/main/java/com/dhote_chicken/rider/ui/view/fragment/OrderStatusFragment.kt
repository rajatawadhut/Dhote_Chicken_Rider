package com.dhote_chicken.rider.ui.view.fragment

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.dhote_chicken.rider.R
import com.dhote_chicken.rider.data.DeliveryStatus
import com.dhote_chicken.rider.data.model.OrderData
import com.dhote_chicken.rider.data.model.OrderStatus
import com.dhote_chicken.rider.data.model.User
import com.dhote_chicken.rider.databinding.OrderStatusFragmentBinding
import com.dhote_chicken.rider.ui.adapter.OrderCartAdapter
import com.dhote_chicken.rider.ui.adapter.OrderLifeCycleAdapter
import com.dhote_chicken.rider.ui.base.BaseFragment
import com.dhote_chicken.rider.ui.viewModel.OrderStatusFragmentViewModel
import java.util.*
import javax.inject.Inject


class OrderStatusFragment @Inject constructor() :
    BaseFragment<OrderStatusFragmentViewModel, OrderStatusFragmentBinding>(), View.OnClickListener {


    @Inject
    lateinit var orderCartAdapter: OrderCartAdapter

    @Inject
    lateinit var orderLifeCycleAdapter: OrderLifeCycleAdapter

    var user: User? = null
    var call : String? = null
    var status : Boolean = false
    var orderData: OrderData? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderData = requireArguments().getSerializable("OrderData") as OrderData
        observe()
    }


    private fun observe() {
        binding.btnDeliveredOrder.setOnClickListener(this)
        binding.ivCall.setOnClickListener(this)
        binding.ivMap.setOnClickListener(this)

        viewModel.getUserInfo().observe(viewLifecycleOwner) {
            if (it != null) {
                user = Gson().fromJson(it.listToJson, User::class.java)
                if (user != null && orderData != null) {
                    viewModel.getOrderById(orderData!!.id.toString())
                }
            }
        }

        viewModel.userData.observe(viewLifecycleOwner) {
            if (it.user != null) {
                binding.llDeliveryDetail.visibility = View.VISIBLE
                binding.tvLblDeliveryDetail.visibility = View.VISIBLE
                if(it!!.user!!.contactMedium != null) {
                    call = it!!.user!!.contactMedium!!.phoneNumber
                }
                binding.userData = it.user
            }else{
                binding.llDeliveryDetail.visibility = View.GONE
                binding.tvLblDeliveryDetail.visibility = View.GONE
            }
        }



        viewModel.orderDataById.observe(viewLifecycleOwner) {
            if (it?.order != null) {
                binding.data = it.order

                if (it.order!!.shoppingCart != null && it.order!!.shoppingCart!!.cartItems.isNotEmpty()) {
                    orderCartAdapter.addCategory(it.order!!.shoppingCart!!.cartItems)
                    binding.orderCartAdapter = orderCartAdapter
                }

                if (it.order!!.orderLifeCycleStatus != null && it.order!!.orderLifeCycleStatus!!.isNotEmpty()) {
                    orderLifeCycleAdapter.addOrderLifeCycle(it.order!!.orderLifeCycleStatus)
                    binding.orderLifeCyclerAdapter = orderLifeCycleAdapter


                    for (cycle in it.order!!.orderLifeCycleStatus) {
                        if (cycle.status == DeliveryStatus.Delivered.value) {
                            status = true
                            break
                        }
                    }

                    if (orderData != null && !status) {
                        /*
                        * Get customer user details
                        * */
                        binding.btnDeliveredOrder.visibility = View.VISIBLE
                        viewModel.getUserData(orderData!!.userId.toString())
                    }

                }
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }


        viewModel.orderDelivered.observe(viewLifecycleOwner) {
            if (it != null && it.status == DeliveryStatus.Delivered.value) {
                requireActivity().onBackPressed()
            }
        }


    }


    override fun createViewModel(): OrderStatusFragmentViewModel {
        return ViewModelProvider(
            this,
            viewModelFactory
        ).get(OrderStatusFragmentViewModel::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_order_status
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            binding.btnDeliveredOrder.id -> {
                if (orderData != null) {
                    val builder = MaterialAlertDialogBuilder(requireContext())
                    builder.setTitle(getString(R.string.title_delivered_order))
                    builder.setMessage(getString(R.string.msg_delivered_order))
                    builder.setPositiveButton(
                        getString(R.string.action_yes)
                    ) { dialog: DialogInterface?, which: Int ->
                        viewModel.deliveredOrder(orderData!!.id.toString(), OrderStatus())
                    }
                    builder.setNegativeButton(
                        getString(R.string.action_no)
                    ) { dialog: DialogInterface?, which: Int -> dialog!!.dismiss() }
                    builder.show()
                }
            }
            binding.ivMap.id -> {
                if (orderData != null && orderData!!.deliveryAddress != null &&
                    orderData!!.deliveryAddress!!.geoLocationRefOrValue != null &&
                    orderData!!.deliveryAddress!!.geoLocationRefOrValue!!.geometry != null &&
                    orderData!!.deliveryAddress!!.geoLocationRefOrValue!!.geometry!!.latitude != null
                ) {
                    val uri: String = "http://maps.google.com/maps?q=loc:" +
                            orderData!!.deliveryAddress!!.geoLocationRefOrValue!!.geometry!!.latitude.toString() + "," +
                            orderData!!.deliveryAddress!!.geoLocationRefOrValue!!.geometry!!.longitude.toString()
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    requireActivity().startActivity(intent)
                }
            }
            binding.ivCall.id -> {
                if (call != null && call!!.isNotEmpty()) {
                    val callIntent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", call, null))
                    requireActivity().startActivity(callIntent)
                }
            }
        }
    }


}