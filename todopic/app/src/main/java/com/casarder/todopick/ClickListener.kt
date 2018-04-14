package com.casarder.todopick

import com.casarder.todopick.model.Board
import com.casarder.todopick.model.List

/**
 * Created by ritaf_000 on 15/04/2018.
 */
interface ClickListener{
    fun onClickBoard(b: Board)
    fun onSelectedList(l: List)
}