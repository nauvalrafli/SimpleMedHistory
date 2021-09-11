package com.example.simplemedhistory

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemedhistory.data.MedDAO
import com.example.simplemedhistory.data.MedDB
import com.example.simplemedhistory.data.RvAdapter
import com.example.simplemedhistory.data.model.MedHistory

class DashboardActivity : AppCompatActivity() {

    private lateinit var listView : RecyclerView
    private lateinit var dao : MedDAO
    private lateinit var list : List<MedHistory>
    private lateinit var adapter : RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setDatabase()

        val userId = Global.userId
        val username = Global.username

        val btnAdd : ImageButton = findViewById(R.id.btAdd)
        val btRefresh : ImageButton = findViewById(R.id.btRefresh)
        val tvPengguna : TextView = findViewById(R.id.tvPengguna)
        listView = findViewById(R.id.rvHistory)

        //tampilkan username
        tvPengguna.text = username

        //configure list adapter
        refresh(userId)

        btnAdd.setOnClickListener { addData(userId, username!!) }
        btRefresh.setOnClickListener { refresh(userId) }
    }

    fun setDatabase() {
        val database = MedDB.getDatabase(this)
        dao = database.getMedDAO()
    }

    fun addData(id : Int, username: String) {
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
    }

    fun refresh(userId : Int) {
        list = dao.getAll(userId)
        adapter = RvAdapter(list)
        listView.adapter = adapter
        adapter.callableOnClick(object : RvAdapter.IOonClicked {
            override fun onClicked(medhis: MedHistory) {
                val intents = Intent(this@DashboardActivity, DetailActivity::class.java)
                startActivity(intents)
            }

            override fun onDelete(medhis: MedHistory) {
                dao.delete(medhis.id)
                refresh(userId)
            }

        })
    }
}