package com.casarder.todopick

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.casarder.todopick.utils.ScanResultParser
import kotlinx.android.synthetic.main.activity_confirm_text.*

class ConfirmTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_text)

        if (intent.hasExtra("result")) {
            val result: String = intent.getStringExtra("result")
            result_edit_text.setText(ScanResultParser.addNewTaskChar(result))
        }

        confirm_result_button.setOnClickListener {
            val tasks = ScanResultParser.divideTasks(result_edit_text.text.toString())

            val intent = Intent(applicationContext, TrelloChoiceBoardActivity::class.java)
            intent.putStringArrayListExtra("tasks", ArrayList(tasks))
            startActivity(intent)
        }
    }
}
