package com.casarder.todopick.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.casarder.todopick.R
import com.casarder.todopick.TrelloChoiceBoardActivity
import com.casarder.todopick.model.Board

/**
 * Created by fabiolourenco on 14/04/18.
 */
class BoardListAdapter(private var boards: List<Board>, private var act: Activity): RecyclerView.Adapter<BoardListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(act).inflate(R.layout.trello_buttom_board_layout, parent, false)
        return ViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return boards.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            val b = boards[position]
            holder.itemView.setOnClickListener {
                val a = act as TrelloChoiceBoardActivity
                a.onClickBoard(b)
            }
        }
    }

}