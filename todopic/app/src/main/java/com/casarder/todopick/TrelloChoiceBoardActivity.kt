package com.casarder.todopick

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.casarder.todopick.adapter.BoardListAdapter
import com.casarder.todopick.adapter.ListAdapter
import com.casarder.todopick.model.Board
import com.casarder.todopick.services.TrelloRetrofitInitializer
import kotlinx.android.synthetic.main.activity_trello_choice_board.*
import kotlinx.android.synthetic.main.dialog_lists.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrelloChoiceBoardActivity : AppCompatActivity(), ClickListener {

    var tasks: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trello_choice_board)

        if(intent.hasExtra("tasks")){
            tasks = intent.getStringArrayListExtra("tasks")
        }

        board_list_recyclerview.layoutManager = LinearLayoutManager(this)

        val call = TrelloRetrofitInitializer().trelloService().getBoards(getString(R.string.app_key), getString(R.string.token))
        call.enqueue(object : Callback<List<Board>?> {
            override fun onResponse(call: Call<List<Board>?>?, response: Response<List<Board>?>?) {
                if (response != null && response.isSuccessful) {
                    val boards = response.body()

                    if (boards != null) {
                        if (boards.isNotEmpty()) {
                            configureList(boards)
                            return
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
    }

    override fun onResume() {
        super.onResume()
        val call = TrelloRetrofitInitializer().trelloService().getBoards(getString(R.string.app_key), getString(R.string.token))
        call.enqueue(object : Callback<List<Board>?> {
            override fun onResponse(call: Call<List<Board>?>?, response: Response<List<Board>?>?) {
                if (response != null && response.isSuccessful) {
                    val boards = response.body()

                    if (boards != null) {
                        if (boards.isNotEmpty()) {
                            configureList(boards)
                            return
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
    }

    private fun configureList(boards: List<Board>) {
        board_list_recyclerview.adapter = BoardListAdapter(boards, this)

    }

    override fun onClickBoard(b: Board) {
        if (b.lists != null && b.lists.isNotEmpty()) {
            showListsDialog(b.lists)
        }
    }

    override fun onSelectedList(l: com.casarder.todopick.model.List) {
        var last = false
        if(tasks!=null){
            showProgressBar()
            for(task in tasks!!){
                if(tasks!!.last() == task){
                    last = true
                }
                if(!task.isBlank()) {
                    val call = TrelloRetrofitInitializer().trelloService().postCard(l.id, task, getString(R.string.app_key), getString(R.string.token))
                    call.enqueue(object : Callback<Void> {
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Toast.makeText(applicationContext, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if (response == null || !response.isSuccessful ){
                                onErr()
                            }else{
                                if(last){
                                    onSuccess()
                                }
                            }
                        }

                    })
                }
            }
        }
    }

    override fun onClickCreateBoard() {
        val i = Intent(this, CreateBoardActivity::class.java)
        startActivity(i)
    }

    private fun showListsDialog(lists: List<com.casarder.todopick.model.List>) {
        val dialogBuilder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_lists, null)
        dialogBuilder.setView(view)
        view.recycler_lists.layoutManager = LinearLayoutManager(this)
        view.recycler_lists.adapter = ListAdapter(lists, this)
        dialogBuilder.show()
    }

    private fun onSuccess() {
        Toast.makeText(applicationContext, "Cards created!", Toast.LENGTH_LONG).show()
        hideProgressBar()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun onErr() {
        Toast.makeText(applicationContext, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
    }

    private fun emptyBody() {
        Toast.makeText(applicationContext, getString(R.string.nothing_here), Toast.LENGTH_LONG).show()
    }

    private fun showProgressBar(){

    }

    private fun hideProgressBar(){

    }
}
