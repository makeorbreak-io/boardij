package com.casarder.todopick.dialog

import android.app.Activity
import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.casarder.todopick.R
import com.casarder.todopick.adapter.ListAdapter
import kotlinx.android.synthetic.main.dialog_lists.*

/**
 * Created by fabiolourenco on 14/04/18.
 */
class ListsDialog: DialogFragment(){

    val recycler = recycler_lists

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater?.inflate(R.layout.dialog_lists, container, false)
    }

    fun populateAdapter(lists: List<com.casarder.todopick.model.List>, act: Activity){
        recycler.layoutManager = LinearLayoutManager(act)
        recycler.adapter = ListAdapter(lists, act)
    }

}