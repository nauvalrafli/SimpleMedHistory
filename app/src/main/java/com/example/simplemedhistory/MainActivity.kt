package com.example.simplemedhistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Database
import com.example.simplemedhistory.data.AccountDAO
import com.example.simplemedhistory.data.AccountDB
import com.example.simplemedhistory.data.MedDB
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var database : AccountDB
    private lateinit var dao : AccountDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDatabase()

        //findId
        val btnSignUp = findViewById<TextView>(R.id.btnGoSignUp)
        val btnlogin = findViewById<Button>(R.id.btnLogin)
        val edtUsername = findViewById<TextInputEditText>(R.id.edtUsername)
        val edtPassword = findViewById<TextInputEditText>(R.id.edtPassword)

        //setOnClick
        btnSignUp.setOnClickListener { signUp() }
        btnlogin.setOnClickListener {
            login(edtUsername.text.toString(), edtPassword.text.toString())
        }
    }

    private fun setDatabase() {
        database = AccountDB.getDatabase(this)
        dao = database.getAccDAO()
    }

    private fun login(x: String, y:String){
        val check = dao.login(x, y)
        Log.d("check", check.toString())
        if (check.size > 0) {
            val id = check[0].id
            Log.d("checkApakahBisa", id.toString())
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("userId", id)
            intent.putExtra("username", x)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Akun tidak sesuai", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signUp() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}