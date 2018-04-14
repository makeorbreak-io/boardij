package com.casarder.todopick.adapter

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.casarder.todopick.R
import com.casarder.todopick.TrelloChoiceBoardActivity
import com.casarder.todopick.model.Board



/**
 * Created by fabiolourenco on 14/04/18.
 */
class BoardListAdapter(private var boards: List<Board>, private var act: Activity): RecyclerView.Adapter<BoardListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {}

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
            holder.itemView.setOnClickListener {
                val a = act as TrelloChoiceBoardActivity
                a.onClickBoard(b)
            }

            if(b.prefs.backgroundImageScaled.isEmpty()) {
                holder.itemView.setBackgroundColor(Color.parseColor(b.prefs.backgroundColor))
            } else {
                Glide.with(act.applicationContext).load(b.prefs.backgroundImageScaled[4].url).into(object: SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable?, transition: Transition? ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        yourRelativeLayout.setBackground(resource)
                    }
                }
                });
            }

        }
    }

}