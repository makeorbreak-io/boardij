package com.casarder.todopick

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.casarder.todopick.utils.ScanResultParser

class ConfirmTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_text)

        val edit = findViewById<EditText>(R.id.result_edit_text)
        val confirmBtn = findViewById<Button>(R.id.confirm_result_button)

        confirmBtn.setOnClickListener {
            val tasks = ScanResultParser.divideTasks(edit.text.toString())

            val intent = Intent(this, TrelloChoiceBoardActivity::class.java)
            intent.putStringArrayListExtra("tasks", ArrayList(tasks))
            startActivity(intent)
        }
    }
}
