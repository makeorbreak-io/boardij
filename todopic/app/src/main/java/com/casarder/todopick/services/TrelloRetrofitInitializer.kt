package com.casarder.todopick.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by fabiolourenco on 13/04/18.
 */
class TrelloRetrofitInitializer {
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.trello.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun trelloService () : TrelloService = retrofit.create(TrelloService::class.java)
}