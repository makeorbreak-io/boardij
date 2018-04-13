package com.casarder.todopick.services

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by fabiolourenco on 13/04/18.
 */
interface TrelloService {
    @GET("1/authorize/")
    fun login() : Call<String>
}