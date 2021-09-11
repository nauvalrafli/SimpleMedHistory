package com.example.simplemedhistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.simplemedhistory.data.MedDAO
import com.example.simplemedhistory.data.MedDB
import com.example.simplemedhistory.data.model.MedHistory

class DetailActivity : AppCompatActivity() {

    private lateinit var dao : MedDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val id = intent.getIntExtra("id", -1)

        setDatabase()

        //get component
        val disease : TextView = findViewById(R.id.diseaseTitle)
        val from : TextView = findViewById(R.id.fromData)
        val until : TextView = findViewById(R.id.untilData)
        val loc : TextView = findViewById(R.id.locationData)
        val result : TextView = findViewById(R.id.resultData)
        val btEdit : ImageButton = findViewById(R.id.btEdit)

        //get data with id
        val data = fetchDataWithId(id)

        //fill tv with Data
        disease.text = data.disease
        from.text = data.from
        until.text = data.until
        loc.text = data.location
        result.text = data.checkResult

        btEdit.setOnClickListener { goEdit(id) }
    }

    fun setDatabase() {
        val database = MedDB.getDatabase(this)
        dao = database.getMedDAO()
    }

    fun fetchDataWithId(id : Int) : MedHistory {
        val data = dao.fetchDataWithId(id)
        return data
    }

    fun goEdit(id : Int) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}