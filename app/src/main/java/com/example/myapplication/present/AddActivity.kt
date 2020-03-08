package com.example.myapplication.present

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.DB.insertItem
import com.example.myapplication.R

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setResult(Activity.RESULT_CANCELED)

        val registerButton = findViewById<Button>(R.id.register)
        registerButton.setOnClickListener {
            var isValid = true

            val nameEditText = findViewById<EditText>(R.id.add_name)
            val name = nameEditText.text.toString()
            if (name.isEmpty()) {
                nameEditText.error = "名前を入力してください"
                isValid = false
            }

            val numEditText = findViewById<EditText>(R.id.add_num)
            val num = numEditText.text.toString()
            if (num.isEmpty()) {
                numEditText.error = "数を入力してください"
                isValid = false
            }

            if (isValid) {
                insertItem(context = this, name = name, num = Integer.parseInt(num))
                setResult(Activity.RESULT_OK)

                finish()
            }
        }
    }
}
