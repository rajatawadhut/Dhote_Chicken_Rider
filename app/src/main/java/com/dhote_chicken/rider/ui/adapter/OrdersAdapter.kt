package com.dhote_chicken.rider.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dhote_chicken.rider.R
import com.dhote_chicken.rider.data.model.OrderData
import com.dhote_chicken.rider.databinding.OrdersAdapterBinding
import com.dhote_chicken.rider.ui.listener.OrdersListener
import javax.inject.Inject

class OrdersAdapter @Inject constructor() :
    RecyclerView.Adapter<OrdersAdapter.CourseViewHolder>() {

    var mContext : Context? = null
    var orderList = ArrayList<OrderData>()

    lateinit var listner : OrdersListener


    class CourseViewHolder(var binding: OrdersAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding: OrdersAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_orders, parent, false
        )
        mContext = parent.context
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val data = orderList[position]
        holder.binding.data = data
        holder.itemView.setOnClickListener {
            listner.onOrdersClick(data)
        }

    }


    override fun getItemCount(): Int {
        return orderList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addOrders(list: ArrayList<OrderData>, listner : OrdersListener) {
        this.orderList = list
        this.listner = listner
        notifyDataSetChanged()
    }





}