package com.example.henry.gwenthelpmate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            var email = loginEmailE.text.toString().trim()
            var password = loginPasswordE.text.toString().trim()

            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                loginUser(email, password)
            }else {
                Toast.makeText(this, "Sorry, login failed!", Toast.LENGTH_LONG)
                        .show()

            }
        }
    }

    private fun  loginUser(email: String, password: String) {

        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, StartActivity::class.java))
                        finish()

                    }else {

                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG)
                                .show()
                    }
                }


    }
}

