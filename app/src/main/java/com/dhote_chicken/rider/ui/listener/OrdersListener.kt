package com.dhote_chicken.rider.ui.listener

import com.dhote_chicken.rider.data.model.OrderData

interface OrdersListener {
    fun onOrdersClick(order: OrderData)
}