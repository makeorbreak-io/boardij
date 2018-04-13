package com.casarder.todopick.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by fabiolourenco on 13/04/18.
 */
interface TrelloService {
    @GET("1/authorize/https://trello.com/1/authorize?expiration=30days&name=SnapIt&scope=read,write,account&response_type=token&key={key}")
    fun login(@Path("key") key : String) : Call<String>
}