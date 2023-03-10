package com.dhote_chicken.rider.ui.view.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dhote_chicken.rider.R
import com.dhote_chicken.rider.data.model.*
import com.dhote_chicken.rider.ui.base.BaseActivity

import com.dhote_chicken.rider.databinding.PaymentActivityBinding
import com.dhote_chicken.rider.ui.viewModel.PaymentActivityViewModel
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject


class PaymentActivity : BaseActivity<PaymentActivityViewModel, PaymentActivityBinding>(), View.OnClickListener,
    PaymentResultWithDataListener, ExternalWalletListener, DialogInterface.OnClickListener{


    var amount: String? = null
    var cartId: String? = null
    var userId: String? = null
    var place: Place? = null
    var timeSlot: DeliveryTimeSlot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amount = intent.getStringExtra("Amount")
        cartId = intent.getStringExtra("CartId")
        place = intent.getSerializableExtra("Address") as Place
        timeSlot = intent.getSerializableExtra("TimeSlot") as DeliveryTimeSlot
        userId = intent.getStringExtra("UserId")
        observe()
    }

    private fun observe() {
        Checkout.preload(this@PaymentActivity)
        startPayment()
        viewModel.isLoading.observe(this, Observer {
            isLoading(it)
        })

        viewModel.failed.observe(this, Observer {
            showSnackBar(it.message.toString())
        })

        viewModel.placedOrder.observe(this, Observer {
            startActivity(Intent(this@PaymentActivity, HomeActivity::class.java).putExtra("IntentNavigation", "Order").putExtra("Amount", amount))
            finish()
            showSnackBar(it.message.toString())
        })
    }

    override fun createViewModel(): PaymentActivityViewModel {
        return ViewModelProvider(this, factory).get(PaymentActivityViewModel::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.activity_payment
    }

    override fun onClick(p0: View?) {
       when(p0!!.id) {
       }
    }

    private fun startPayment() {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity: Activity = this@PaymentActivity
        val co = Checkout()
/*        val etApiKey = findViewById<EditText>(R.id.et_api_key)
        val etCustomOptions = findViewById<EditText>(R.id.et_custom_options)*/
/*        if (!TextUtils.isEmpty(etApiKey.text.toString())){
            co.setKeyID(etApiKey.text.toString())
        }*/
        try {
            var options = JSONObject()
/*            if (!TextUtils.isEmpty(etCustomOptions.text.toString())){
                options = JSONObject(etCustomOptions.text.toString())
            }else{*/
            options.put("name", "Razorpay Corp")
            options.put("description", "Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency", "INR")
            options.put("amount", amount + "00")
            options.put("send_sms_hash", true);

            val prefill = JSONObject()
            prefill.put("email", "abc@gmail.com")
            prefill.put("contact", "8585858585")

            options.put("prefill", prefill)
//            }
            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        try {
            placedOrder()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun placedOrder() {
        if(place != null && userId != null && cartId != null && timeSlot != null){
            val order = OrderList()
            order.paymentMode = "Online"
            order.paymentStatus = "Submit"
            order.shoppingCartId = cartId
            order.userId = userId
            order.totalAmount = amount.toString().toInt()

            order.deliveryAddress = getDeliveryAddress()
            order.deliveryTimeSlot = getDeliveryTimeSlot()

            viewModel.placedOrder(order)
        }
    }

    private fun getDeliveryTimeSlot(): DeliveryTimeSlot? {
        val slot = DeliveryTimeSlot()
        slot.deliveryTimeSlot = timeSlot!!.deliveryTimeSlot
        slot.deliveryDate = timeSlot!!.deliveryDate
        return slot
    }

    private fun getDeliveryAddress(): DeliveryAddress? {
        val address = DeliveryAddress()
        address.addressType = place!!.addressType
        address.city = place!!.city
        address.country = place!!.country
        address.defaultDeliveryAddress = true
        address.fullAddress = place!!.fullAddress
        address.locality = place!!.locality
        address.pinCode = place!!.pinCode
        address.state = place!!.state
        address.streetName = place!!.streetName
        address.geoLocationRefOrValue = place!!.geoLocationRefOrValue
        return address
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        try {
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {
        try {
            /*   alertDialogBuilder.setMessage("External wallet was selected : Payment Data: ${p1?.data}")
               alertDialogBuilder.show()*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
    }

}