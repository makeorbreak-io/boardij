package com.casarder.todopick.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by fabiolourenco on 13/04/18.
 */
interface TrelloService {

    //user token e4b2cf28c60a1cdc4af322757b5aeb71

    /*
    {
        "name": "CARD NAME",
        "des": "Card description",
        "pos": "top",
    }
     */

    @GET("")
    fun login(@Path("key") key : String) : Call<String>
}