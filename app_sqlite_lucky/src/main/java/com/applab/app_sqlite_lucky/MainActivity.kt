package com.applab.app_sqlite_lucky

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.applab.app_sqlite_lucky.db.DBHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val dbHelper = DBHelper(this)
    lateinit var recyclerAdapterLucky: RecyclerAdapterLucky
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        init()
        recyclerAdapterLucky.submitList(dbHelper.readOdds())

        // FAB
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            dbHelper.createLucky("Black", 99)
            recyclerAdapterLucky.submitList(dbHelper.readOdds())
            recyclerAdapterLucky.notifyDataSetChanged()
        }
    }

    private fun init() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerAdapterLucky = RecyclerAdapterLucky()
            adapter = recyclerAdapterLucky
        }

    }
}
