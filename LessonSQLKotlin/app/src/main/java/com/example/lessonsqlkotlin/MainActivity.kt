package com.example.lessonsqlkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.lessonsqlkotlin.db.MyDbManager

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()

        val TvTest = findViewById<TextView>(R.id.tvTest)
        val dataList = myDbManager.readDbData()
        for(item in dataList ){
            TvTest.append(item)
            TvTest.append("\n")
        }
    }
    fun onClickSave(view: View) {
        val TvTest = findViewById<TextView>(R.id.tvTest)
        TvTest.text = ""
        val EdTitle = findViewById<EditText>(R.id.edTitle)
        val EdContent = findViewById<EditText>(R.id.edContent)
        myDbManager.insertToDb(EdTitle.text.toString(), EdContent.text.toString())
        val dataList = myDbManager.readDbData()
        for(item in dataList ){
            TvTest.append(item)
            TvTest.append("\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}