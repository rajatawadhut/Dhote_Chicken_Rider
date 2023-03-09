package com.iitms.bfr.ui.listener

import com.iitms.bfr.data.model.CartItems

interface CartListener {
    fun onCartEditClick(cartItems: List<CartItems>)

    fun onCartDeleteClick()
}