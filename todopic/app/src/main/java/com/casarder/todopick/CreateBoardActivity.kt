package com.casarder.todopick

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.casarder.todopick.model.Board
import com.casarder.todopick.services.TrelloRetrofitInitializer
import kotlinx.android.synthetic.main.activity_create_board.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateBoardActivity : AppCompatActivity() {

    lateinit var opt : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_board)

        public_opt.setOnClickListener {
            opt = public_opt.text.toString().toLowerCase()
        }

        private_opt.setOnClickListener {
            opt = private_opt.text.toString().toLowerCase()
        }

        create_button.setOnClickListener{
            createBoard()
        }
    }

    private fun createBoard(){
        val call = TrelloRetrofitInitializer().trelloService().postBoard(input_name.text.toString(), input_desc.text.toString(),opt, true)

        call.enqueue(object: Callback<Board>{
            override fun onFailure(call: Call<Board>?, t: Throwable?) {
                onErr()
            }

            override fun onResponse(call: Call<Board>?, response: Response<Board>?) {
                if(response != null && response.isSuccessful){
                    val created = response.body()
                    if(created!= null){
                        onSuccess(created)
                        return;
                    }
                }
                onErr()
            }

        })
    }

    private fun onErr()
    {
        Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
    }

    private fun onSuccess(b: Board){
        finish()
    }
}
