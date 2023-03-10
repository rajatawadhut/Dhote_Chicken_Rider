package com.dhote_chicken.rider.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dhote_chicken.rider.R
import com.dhote_chicken.rider.data.model.OrderLifeCycleStatus
import com.dhote_chicken.rider.databinding.OrderLifeCycleAdapterBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class OrderLifeCycleAdapter @Inject constructor() :
    RecyclerView.Adapter<OrderLifeCycleAdapter.OrderLifeCycleViewHolder>() {

    var mContext : Context? = null
    var orderLifeList = ArrayList<OrderLifeCycleStatus>()


    class OrderLifeCycleViewHolder(var binding: OrderLifeCycleAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderLifeCycleViewHolder {
        val binding: OrderLifeCycleAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_order_life_cycle, parent, false
        )
        mContext = parent.context
        return OrderLifeCycleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderLifeCycleViewHolder, position: Int) {
        val data = orderLifeList[position]
        if(position == (itemCount - 1)){
            holder.binding.view.visibility = View.GONE
        }else{
            holder.binding.view.visibility = View.VISIBLE
        }
        holder.binding.tvName.text = data.status
        val formatedDate : Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(data.lastUpdate)
        holder.binding.tvTime.text = SimpleDateFormat("dd, MMM yyyy hh:mm a").format(formatedDate)
    }
    
    override fun getItemCount(): Int {
        return orderLifeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addOrderLifeCycle(list: ArrayList<OrderLifeCycleStatus>) {
        this.orderLifeList = list
        notifyDataSetChanged()
    }





}