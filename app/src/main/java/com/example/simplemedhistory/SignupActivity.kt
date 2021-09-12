package com.example.simplemedhistory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplemedhistory.data.AccountDAO
import com.example.simplemedhistory.data.AccountDB
import com.example.simplemedhistory.data.model.Account
import com.google.android.material.textfield.TextInputEditText

class SignupActivity : AppCompatActivity() {

    private lateinit var dao : AccountDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //set database
        setDatabase()

        //find Id
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val btnLogin = findViewById<TextView>(R.id.btnGoLogin)
        val edtUsername = findViewById<TextInputEditText>(R.id.edtUsername)
        val edtPassword = findViewById<TextInputEditText>(R.id.edtPassword)

        //setOnClick
        btnSignUp.setOnClickListener {
            signUp(Account(extractStr(edtUsername), extractStr(edtPassword)))
        }
        btnLogin.setOnClickListener { login() }
    }

    private fun setDatabase() {
        val database = AccountDB.getDatabase(this)
        dao = database.getAccDAO()
    }

    private fun extractStr(view : TextInputEditText) : String {
        return view.text.toString()
    }

    private fun signUp(accounts : Account) {
        dao.signUp(accounts)
        Toast.makeText(this, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
        login()
    }

    private fun login() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}