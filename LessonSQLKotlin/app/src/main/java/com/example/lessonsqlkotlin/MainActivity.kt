package com.example.lessonsqlkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lessonsqlkotlin.db.MyAdapter
import com.example.lessonsqlkotlin.db.MyDbManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }
    fun onClickNew(view: View) {
        val i = Intent(this, EditActivity::class.java )
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    fun init(){
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = myAdapter
    }

    fun fillAdapter(){
        val list = myDbManager.readDbData()
        myAdapter.updateAdapter(list)
        if(list.size > 0) {
            tvNoElements.visibility = View.GONE
        }else{
            tvNoElements.visibility = View.VISIBLE
        }

    }

}