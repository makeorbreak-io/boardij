package com.casarder.todopick.services

import com.casarder.todopick.model.Board
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    ?key={key}&token={token}
    @Path("key")key: String, @Path("token")token: String
     */

    @GET("1/members/me/boards")
    fun getBoards(@Query("key")key: String, @Query("token")token: String) : Call<List<Board>>

    @POST("/1/boards/{id}/lists")
    fun getListsOfBoard(@Path("id")id: String,@Query("key")key: String, @Query("token")token: String): Call<List<com.casarder.todopick.model.List>>

    @POST("1/cards?idList={idList}")
    fun postCard(@Path("idList")id: String,@Query("key")key: String, @Query("token")token: String): Call<Void>

}