package com.example.simplemedhistory

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.simplemedhistory.data.model.MedHistory

class DetailActivity : AppCompatActivity() {

    private val dao = Global.medDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val id = Global.dataId

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

    private fun fetchDataWithId(id: Int): MedHistory {
        return dao.fetchDataWithId(id)
    }

    private fun goEdit(id : Int) {
        val intent = Intent(this, EditActivity::class.java)
        Global.dataId = id
        startActivity(intent)
    }
}