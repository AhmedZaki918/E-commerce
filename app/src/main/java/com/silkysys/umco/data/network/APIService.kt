package com.silkysys.umco.data.network

import com.silkysys.umco.data.model.address.UpdateResponse
import com.silkysys.umco.data.model.address.all.AllResponse
import com.silkysys.umco.data.model.address.create.CreateResponse
import com.silkysys.umco.data.model.address.save.request.SaveAddressRequest
import com.silkysys.umco.data.model.address.save.response.SaveAddressResponse
import com.silkysys.umco.data.model.auth.login.LoginResponse
import com.silkysys.umco.data.model.auth.logout.LogoutResponse
import com.silkysys.umco.data.model.cart.DetailsResponse
import com.silkysys.umco.data.model.categories.all.AllCategoriesResponse
import com.silkysys.umco.data.model.categories.descendant.DescendantResponse
import com.silkysys.umco.data.model.order.create.PlaceOrderResponse
import com.silkysys.umco.data.model.order.get.AllOrdersResponse
import com.silkysys.umco.data.model.payment.request.PaymentRequest
import com.silkysys.umco.data.model.payment.response.PaymentResponse
import com.silkysys.umco.data.model.products.byCategory.ProductsResponse
import com.silkysys.umco.data.model.shipping.ShippingResponse
import com.silkysys.umco.data.model.sliders.SlidersResponse
import retrofit2.http.*

interface APIService {


    // Auth api
    @FormUrlEncoded
    @POST("/api/customer/login")
    suspend fun login(
        @Query("token") token: Boolean,
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("/api/customer/logout")
    suspend fun logout(
        @Query("token") token: Boolean
    ): LogoutResponse

    @GET("/api/customer/get")
    suspend fun getUser(
        @Query("token") token: Boolean
    ): LoginResponse


    // Products and Categories api
    @GET("/api/sliders")
    suspend fun getSliders(): SlidersResponse

    @GET("/api/descendant-categories")
    suspend fun getDescendant(
        @Query("parent_id") parentId: Int
    ): DescendantResponse

    @GET("/api/products")
    suspend fun getProductsByCategory(
        @Query("page") page: Int,
        @Query("category_id") category_id: Int
    ): ProductsResponse

    @GET("/api/categories")
    suspend fun getAllCategories(
        @Query("pagination") pagination: Int
    ): AllCategoriesResponse


    // Cart api
    @FormUrlEncoded
    @POST("/api/checkout/cart/add/{id}")
    suspend fun updateCart(
        @Path("id") id: Int,
        @Query("token") token: Boolean,
        @Field("quantity") qty: Int,
        @Field("product_id") productId: Int
    ): DetailsResponse

    @GET("/api/checkout/cart")
    suspend fun getCartDetails(
        @Query("token") token: Boolean
    ): DetailsResponse

    @GET("/api/checkout/cart/remove-item/{id}")
    suspend fun removeProduct(
        @Path("id") id: Int,
        @Query("token") token: Boolean
    ): DetailsResponse


    // Address api
    @FormUrlEncoded
    @POST("/api/addresses/create")
    suspend fun createAddress(
        @Query("token") token: Boolean,
        @Field("address1[0]") addresses: String,
        @Field("city") city: String,
        @Field("country_name") countryName: String,
        @Field("country") country: String,
        @Field("phone") phone: String,
        @Field("postcode") postcode: String,
        @Field("state") state: String
    ): CreateResponse

    @GET("/api/addresses")
    suspend fun getAllAddresses(
        @Query("token") token: Boolean
    ): AllResponse

    @PUT("/api/addresses/{address_id}")
    suspend fun updateAddress(
        @Path("address_id") id: Int,
        @Query("token") token: Boolean,
        @Query("address1[0]") addresses: String,
        @Query("city") city: String,
        @Query("country_name") countryName: String,
        @Query("country") country: String,
        @Query("phone") phone: String,
        @Query("postcode") postcode: String,
        @Query("state") state: String
    ): UpdateResponse

    @POST("/api/checkout/save-address")
    suspend fun saveAddress(
        @Query("token") token: Boolean,
        @Body saveAddressRequest: SaveAddressRequest
    ): SaveAddressResponse


    // Shipping api
    @POST("/api/checkout/save-shipping")
    suspend fun saveShipping(
        @Query("token") token: Boolean,
        @Query("shipping_method") shippingMethod: String?
    ): ShippingResponse


    // Payment api
    @POST("/api/checkout/save-payment")
    suspend fun savePayment(
        @Query("token") token: Boolean,
        @Body paymentRequest: PaymentRequest
    ): PaymentResponse


    // Order api
    @POST("/api/checkout/save-order")
    suspend fun placeOrder(
        @Query("token") token: Boolean,
    ): PlaceOrderResponse

    @GET("/api/orders")
    suspend fun getAllOrders(
        @Query("token") token: Boolean,
        @Query("pagination") pagination: Int
    ): AllOrdersResponse
}