package com.dhote_chicken.rider.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhote_chicken.rider.R
import com.dhote_chicken.rider.data.model.CartItems
import com.dhote_chicken.rider.databinding.OrderCartAdapterBinding
import com.dhote_chicken.rider.ui.listener.CartListener
import javax.inject.Inject

class OrderCartAdapter @Inject constructor() :
    RecyclerView.Adapter<OrderCartAdapter.OrderCartViewHolder>() {

    var mContext : Context? = null
    var cartItems = ArrayList<CartItems>()

    lateinit var listner : CartListener


    class OrderCartViewHolder(var binding: OrderCartAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderCartViewHolder {
        val binding: OrderCartAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_order_cart, parent, false
        )
        mContext = parent.context
        return OrderCartViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderCartViewHolder, position: Int) {
        val data = cartItems[position]
        holder.binding.data = data

        if(data.liveChickenPreparation != null) {
            if(data.liveChickenPreparation!!.additionalAmount > 0) {
                holder.binding.tvExtraAmount.visibility = View.VISIBLE
                holder.binding.tvExtraAmount.text = "Additional Charge  ₹  ${data.liveChickenPreparation!!.additionalAmount.toString()}"
            }else {
                holder.binding.tvExtraAmount.visibility = View.GONE
            }
            holder.binding.tvPreparation.text = data.liveChickenPreparation!!.pieceSize.toString()
            holder.binding.tvPreparation.visibility = View.VISIBLE
            if(data.liveChickenPreparation!!.weight == 0) {
                holder.binding.tvCategoryName.text = "${data.productName}"
            }else{
                holder.binding.tvCategoryName.text = "${data.productName} (Approx : ${data.liveChickenPreparation!!.weight})"
            }        }else {
            holder.binding.tvCategoryName.text = "${data.productName}"
            holder.binding.tvExtraAmount.visibility = View.GONE
            holder.binding.tvPreparation.visibility = View.GONE
        }

        if(data.notes != null && data.notes!!.isNotEmpty()){
            Glide.with(mContext!!)
                .load(data.notes)
                .centerCrop()
                .error(R.drawable.ic_chicken_logo)
                .into(holder.binding.ivCategory)
        }else{
            holder.binding.ivCategory.setImageDrawable(ContextCompat.getDrawable(mContext!!, R.drawable.ic_chicken_logo))
        }



    }


    override fun getItemCount(): Int {
        return cartItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCategory(list: ArrayList<CartItems>) {
        this.cartItems = list
        notifyDataSetChanged()
    }


}