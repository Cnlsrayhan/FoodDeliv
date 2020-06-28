package com.example.fooddeliv.Common

import com.example.fooddeliv.Remote.IGoogleAPIService
import com.example.fooddeliv.Remote.RetrofitClient

object Common {
    private val GOOGLE_API_URL="https://maps.googleapis.com/"

    val googleApiService:IGoogleAPIService
        get()=RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService::class.java)
}