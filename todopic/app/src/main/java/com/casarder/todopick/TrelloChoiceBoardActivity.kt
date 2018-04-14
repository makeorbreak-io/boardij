package com.casarder.todopick

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.Toast
import com.casarder.todopick.adapter.BoardListAdapter
import com.casarder.todopick.dialog.ListsDialog
import com.casarder.todopick.model.Board
import com.casarder.todopick.services.TrelloRetrofitInitializer
import kotlinx.android.synthetic.main.activity_trello_choice_board.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrelloChoiceBoardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trello_choice_board)
        board_list_recyclerview.layoutManager = LinearLayoutManager(this)

        val call = TrelloRetrofitInitializer().trelloService().getBoards(getString(R.string.app_key), getString(R.string.token))
        call.enqueue(object: Callback<List<Board>?> {
            override fun onResponse(call: Call<List<Board>?>?, response: Response<List<Board>?>?) {
                if(response != null && response.isSuccessful) {
                    val boards = response.body()
                    if(boards != null){
                        if(boards.isNotEmpty()) {
                            configureList(boards)
                            return;
                        }
                        emptyBody()
                    }
                }
                onErr()
            }

            override fun onFailure(call: Call<List<Board>?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
                onErr()
            }
        })
        Log.d("Trello", "BugFree")
    }

    private fun configureList(boards: List<Board>) {
        val recyclerView = board_list_recyclerview
        recyclerView.adapter = BoardListAdapter(boards, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

    }

    fun onClickBoard(b: Board) {
        val call = TrelloRetrofitInitializer().trelloService().getListsOfBoard(b.id,getString(R.string.app_key), getString(R.string.token))

        call.enqueue(object: Callback<List<com.casarder.todopick.model.List>>{
            override fun onResponse(call: Call<List<com.casarder.todopick.model.List>>?, response: Response<List<com.casarder.todopick.model.List>>?) {
                if(response != null && response.isSuccessful){
                    val list = response.body()
                    if(list != null) {
                        if(list.isNotEmpty()) {
                            showListsDialog(list)
                        }
                        emptyBody()
                    }
                }
                onErr()
            }

            override fun onFailure(call: Call<List<com.casarder.todopick.model.List>>?, t: Throwable?) {
                Toast.makeText(applicationContext, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
            }


        })
    }

    fun onSelectedList(l: com.casarder.todopick.model.List){
        val call = TrelloRetrofitInitializer().trelloService().postCard(l.id,getString(R.string.app_key), getString(R.string.token))
        call.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Toast.makeText(applicationContext, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                if(response != null && response.isSuccessful) {
                    onSuccess()
                }
            }

        })
    }

    private fun showListsDialog(lists: List<com.casarder.todopick.model.List>){
        val dialog = ListsDialog()
        val fm = fragmentManager

        dialog.show(fm, "New List Dialog")
        dialog.populateAdapter(lists, this)
    }

    private fun onSuccess(){
        Toast.makeText(applicationContext, "Card created!", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun onErr() {
        Toast.makeText(applicationContext, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
    }

    private fun emptyBody() {
        Toast.makeText(applicationContext, getString(R.string.nothing_here), Toast.LENGTH_LONG).show()
    }
}
