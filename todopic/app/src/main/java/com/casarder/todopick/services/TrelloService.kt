package com.casarder.todopick.services

import android.app.ActivityManager
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
     */

    /**
     * Gets all the open boards of the user
     *
     */
    @GET("1/members/me/boards?fields=id,name,desc,prefs,lists&filter=open&lists=open")
    fun getBoards(@Query("key")key: String, @Query("token")token: String) : Call<List<Board>>

    /**
     * Creates a new card
     * @id - id of the list
     * @name - card name
     *
     */
    @POST("1/cards")
    fun postCard(@Query("idList")id: String,@Query("name")name: String,@Query("key")key: String, @Query("token")token: String): Call<Void>

    /**
     * Creates a new board
     * @name - Board name
     * @desc - Board description
     * @prefs_permissionLevel - public/private
     *
     */
    @POST("1/boards/")
    fun postBoard(@Query("name")name:String, @Query("desc") description: String,
                  @Query("prefs_permissionLevel") perm: String, @Query("defaultLists") lists: Boolean,
                  @Query("key")key: String, @Query("token")token: String): Call<Board>
}