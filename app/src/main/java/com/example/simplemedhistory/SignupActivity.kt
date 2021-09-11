package com.example.simplemedhistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.simplemedhistory.data.AccountDAO
import com.example.simplemedhistory.data.AccountDB
import com.example.simplemedhistory.data.model.Account
import com.google.android.material.textfield.TextInputEditText
import javax.security.auth.login.LoginException

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

    fun setDatabase() {
        val database = AccountDB.getDatabase(this)
        dao = database.getAccDAO()
    }

    fun extractStr(view : TextInputEditText) : String {
        return view.text.toString()
    }

    fun signUp(accounts : Account) {
        dao.signUp(accounts)
        Toast.makeText(this, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
        login()
    }

    fun login() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}