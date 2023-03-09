package com.iitms.bfr.data

import com.iitms.bfr.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {


    @POST("user/customerLogin/{phone}")
    suspend fun login(
        @Path(value = "phone")  phone_number : String,
    ): Response<LoginData>


    @POST("user/validateUser/{phone}/{otp}")
    @FormUrlEncoded
    suspend fun verifyOtp(
        @Path(value = "phone")  phone_number : String,
        @Path(value = "otp")  otp : String,
    ): Response<LoginData>



    @GET("query/category")
    suspend fun getCategory(
    ): Response<Category>




    @GET("query/coupon")
    suspend fun getCoupon(
        @Query("order") order : String
    ): Response<Coupon>



    @POST("user/")
    suspend fun saveUserDate(
        @Body user: User
    ): Response<User>


    @PATCH("user/{userId}")
    suspend fun updateUserData(
        @Path(value = "userId")  userId : String,
        @Body user: User
    ): Response<User>


    @GET("query/order/rider/{userId}")
    suspend fun getOrderList(
        @Path(value = "userId")  userId : String,
    ): Response<HomePageOrder>


    @GET("query/user/{userId}")
    suspend fun getUserData(
        @Path(value = "userId")  userId : String,
    ): Response<UserData>



    @GET("query/order/{orderId}")
    suspend fun getOrderById(
        @Path(value = "orderId")  orderId : String,
    ): Response<OrderDataById>


    @PATCH("order/{orderId}")
    suspend fun deliveredOrder(
        @Path(value = "orderId")  orderId : String,
        @Body orderCancel: OrderStatus,
    ): Response<OrderStatus>

    @GET("query/shopping-cart/customer/{userId}")
    suspend fun getCartDataFromServer(
        @Path(value = "userId")  userId : String,
    ): Response<ShoppingCart>

    @GET("query/product")
    suspend fun getSubCategory(
        @Query("order") order : String,
        @Query("orderBy") orderBy : String
    ): Response<SubCategory>

    @GET("query/banner")
    suspend fun getBanner(
        @Query("order") order : String
    ): Response<Banner>

    @POST("shopping-cart/")
    suspend fun addCart(
        @Body cart : Cart
    ): Response<Cart>

    @POST("user/validateUser/{phone}/{otp}")
    suspend fun validateUser(
        @Path(value = "phone")  phone : String,
        @Path(value = "otp")  otp : String,
    ): Response<ValidateOTP>

    @POST("order/")
    suspend fun placedOrder(
        @Body order : OrderList
    ): Response<Status>

    @DELETE("shopping-cart/{id}")
    suspend fun deleteCart(@Path(value = "id")  id: String) : Response<Cart>



}