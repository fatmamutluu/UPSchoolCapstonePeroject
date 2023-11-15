package com.example.upshoolcapstoneproject.data

import com.example.upshoolcapstoneproject.data.model.response.GetProductDetailResponse
import com.example.upshoolcapstoneproject.data.model.response.GetProductsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

        @GET("get_products.php")
        suspend fun getProducts(): Response<GetProductsResponse>

        @GET("get_product_detail.php")
        suspend fun getProductDetail(
                @Query("id") id: Int
        ): Response<GetProductDetailResponse>
}