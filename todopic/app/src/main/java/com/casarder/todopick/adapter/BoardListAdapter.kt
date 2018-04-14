package com.casarder.todopick.adapter

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.casarder.todopick.ClickListener
import com.casarder.todopick.R
import com.casarder.todopick.TrelloChoiceBoardActivity
import com.casarder.todopick.model.Board
import kotlinx.android.synthetic.main.trello_button_board_layout.view.*


/**
 * Created by fabiolourenco on 14/04/18.
 */
class BoardListAdapter(private var boards: List<Board>, private var act: TrelloChoiceBoardActivity) : RecyclerView.Adapter<BoardListAdapter.ViewHolder>() {

    companion object {
        private const val BG_ITEM = 4
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView.board_layout
        val nameTxt = itemView.nameBoard
        val descTxt = itemView.descriptionBoard
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(act).inflate(R.layout.trello_button_board_layout, parent, false)

        return ViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return boards.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            val b = boards[position]
            holder.nameTxt.text = b.name
            holder.descTxt.text = b.desc
            holder.itemView.setOnClickListener {
                act.onClickBoard(b)
            }
            if (b.prefs.backgroundImageScaled == null || b.prefs.backgroundImageScaled.isEmpty()) {
                holder.itemView.setBackgroundColor(Color.parseColor(b.prefs.backgroundColor))
            } else {
                Glide.with(act.applicationContext).load(b.prefs.backgroundImageScaled!![BG_ITEM].url).into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable?, transition: Transition<in Drawable>?) {
                        holder.layout.background = resource
                    }

                })
            }


        }
    }

}