package com.example.simplemedhistory

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemedhistory.data.MedDAO
import com.example.simplemedhistory.data.RvAdapter
import com.example.simplemedhistory.data.model.MedHistory

class DashboardActivity : AppCompatActivity() {

    private lateinit var listView : RecyclerView
    private lateinit var list : List<MedHistory>
    private lateinit var adapter : RvAdapter
    private var dao : MedDAO = Global.medDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userId = Global.userId
        val username = Global.username

        val btnAdd : ImageButton = findViewById(R.id.btAdd)
        val btRefresh : ImageButton = findViewById(R.id.btRefresh)
        val tvPengguna : TextView = findViewById(R.id.tvPengguna)
        val btLogOut : ImageButton = findViewById(R.id.btLogOut)
        listView = findViewById(R.id.rvHistory)

        //tampilkan username
        tvPengguna.text = username

        //configure list adapter
        refresh(userId)

        btnAdd.setOnClickListener { addData() }
        btRefresh.setOnClickListener { refresh(userId) }
        btLogOut.setOnClickListener { logOut() }
    }

    private fun addData() {
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
    }

    private fun refresh(userId : Int) {
        list = dao.getAll(userId)
        adapter = RvAdapter(list)
        listView.adapter = adapter
        adapter.callableOnClick(object : RvAdapter.IOonClicked {
            override fun onClicked(medhis: MedHistory) {
                val intents = Intent(this@DashboardActivity, DetailActivity::class.java)
                Global.dataId = medhis.id
                startActivity(intents)
            }

            override fun onDelete(medhis: MedHistory) {
                dao.delete(medhis.id)
                refresh(userId)
            }

        })
    }

    fun logOut() {
        val intent = Intent(this, MainActivity::class.java)
        Global.userId = -1
        Global.username = ""
        Global.dataId = -1
        startActivity(intent)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "This is the root of application", Toast.LENGTH_SHORT).show()
    }
}