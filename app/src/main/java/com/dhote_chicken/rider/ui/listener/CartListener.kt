package com.dhote_chicken.rider.ui.listener

import com.dhote_chicken.rider.data.model.CartItems

interface CartListener {
    fun onCartEditClick(cartItems: List<CartItems>)

    fun onCartDeleteClick()
}