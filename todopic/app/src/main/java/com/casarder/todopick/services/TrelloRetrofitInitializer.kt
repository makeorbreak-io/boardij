package com.casarder.todopick.services

/**
 * Created by fabiolourenco on 13/04/18.
 */
class RetrofitInitializer {
    fun init() {
        Retrofit.Builder().baseUrl("http://192.168.0.23:8080/")
    }
}