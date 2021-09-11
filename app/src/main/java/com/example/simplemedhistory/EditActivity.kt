package com.example.simplemedhistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.simplemedhistory.data.MedDAO
import com.example.simplemedhistory.data.MedDB
import com.example.simplemedhistory.data.model.MedHistory
import com.google.android.material.textfield.TextInputEditText

class EditActivity : AppCompatActivity() {

    private lateinit var dao : MedDAO
    private var strIntent : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //get intent
        val intent = intent
        val id = intent.getIntExtra("id", -1)
        val userId = intent.getIntExtra("userId", 0)

        //set database
        setDatabase()

        //get Component
        val disease : EditText = findViewById(R.id.edtDisease)
        val from : EditText = findViewById(R.id.edtFrom)
        val until : EditText = findViewById(R.id.edtUntil)
        val loc : EditText = findViewById(R.id.edtLoc)
        val result : EditText = findViewById(R.id.edtResult)
        val btnSave : Button = findViewById(R.id.btnSave)

        if (id > -1) {
            disease.setText(dao.fetchDataWithId(id).disease)
            from.setText(dao.fetchDataWithId(id).from)
            until.setText(dao.fetchDataWithId(id).until)
            loc.setText(dao.fetchDataWithId(id).location)
            result.setText(dao.fetchDataWithId(id).checkResult)
        }

        btnSave.setOnClickListener {
            save(id, userId, disease.text.toString(), from.text.toString(),
                until.text.toString(), loc.text.toString(), result.text.toString())
        }
    }

    fun setDatabase() {
        val database = MedDB.getDatabase(this)
        dao = database.getMedDAO()
    }

    fun save(id : Int, userId : Int, disease: String, from : String, until: String, loc : String, result : String) {
        //check intent data, jika null maka simpan, jika ada maka edit
        if (id < 0 ) {
            val medhis = MedHistory(userId, disease, from, until, loc, result)
            dao.add(medhis)
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        } else {
            val medhis = MedHistory(id, userId, disease, from, until, loc, result)
            dao.update(medhis)
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }

}