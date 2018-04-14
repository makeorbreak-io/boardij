package com.casarder.todopick.services

import com.casarder.todopick.model.Board
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by fabiolourenco on 13/04/18.
 */
interface TrelloService {

    //user token e4b2cf28c60a1cdc4af322757b5aeb71
    //key bf37bcbe022448ef6e9770ebed79f3b3

    /*
    {
        "name": "CARD NAME",
        "des": "Card description",
        "pos": "top",
    }
     */

    @GET("https://api.trello.com/1/members/me/boards?key={key}&token={token}")
    fun getBoards(@Path("key")key: String, @Path("token")token: String) : Call<List<Board>>

    @POST("https://api.trello.com//1/boards/{id}/lists?key={key}&token={token}")
    fun getListsOfBoard(@Path("id")id: String,@Path("key")key: String, @Path("token")token: String): Call<List<com.casarder.todopick.model.List>>

    @POST("https://api.trello.com/1/cards?idList={idList}?key={key}&token={token}")
    fun postCard(@Path("idList")id: String,@Path("key")key: String, @Path("token")token: String): Call<Void>

}