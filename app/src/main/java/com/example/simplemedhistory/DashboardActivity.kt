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

        val intent = intent
        val userId = intent.getIntExtra("userId", 0)
        val username = intent.getStringExtra("username")

        val btnAdd : ImageButton = findViewById(R.id.btAdd)
        val btRefresh : ImageButton = findViewById(R.id.btRefresh)
        val tvPengguna : TextView = findViewById(R.id.tvPengguna)
        listView = findViewById(R.id.rvHistory)

        //tampilkan username
        tvPengguna.text = username

        //configure list adapter
        list = dao.getAll(userId)
        adapter = RvAdapter(list)
        listView.adapter = adapter
        adapter.callableOnClick(object : RvAdapter.IOonClicked {
            override fun onClicked(medhis: MedHistory) {
                val intents = Intent(this@DashboardActivity, DetailActivity::class.java)
                intents.putExtra("id", medhis.id)
                intents.putExtra("userId", userId)
                startActivity(intents)
            }

            override fun onDelete(medhis: MedHistory) {
                dao.delete(medhis.id)
                refresh(userId, username!!)
            }

        })

        btnAdd.setOnClickListener { addData(userId) }
        btRefresh.setOnClickListener { refresh(userId, username!!) }
    }

    fun setDatabase() {
        val database = MedDB.getDatabase(this)
        dao = database.getMedDAO()
    }

    fun addData(id : Int) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("userId", id)
        startActivity(intent)
    }

    fun refresh(id: Int, username : String) {
        val i = Intent(this, DashboardActivity::class.java)
        i.putExtra("userId", id)
        i.putExtra("username", username)
        finish()
        overridePendingTransition(0, 0)
        startActivity(i)
        overridePendingTransition(0, 0)
    }
}