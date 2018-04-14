package com.casarder.todopick.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.casarder.todopick.R
import com.casarder.todopick.TrelloChoiceBoardActivity
import kotlinx.android.synthetic.main.single_list_dialog.view.*

/**
 * Created by fabiolourenco on 14/04/18.
 */
class ListAdapter(var lists: List<com.casarder.todopick.model.List>, val act :TrelloChoiceBoardActivity): RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            val l = lists[position]

            holder.nameTxt.text = l.name
            holder.itemView.setOnClickListener {
                act.onSelectedList(l)
            }
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(act).inflate(R.layout.single_list_dialog, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val nameTxt: TextView = item.list_name
    }
}