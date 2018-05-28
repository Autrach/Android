package com.example.henry.gwenthelpmate


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            var email = accountEmailE.text.toString().trim()
            var password = accountPasswordE.text.toString().trim()
            var displayName = accountDisplayE.text.toString().trim()

            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)
                    || !TextUtils.isEmpty(displayName)) {
                createAccount(email, password, displayName)
            }else {
                Toast.makeText(this, "Please fill out the fields", Toast.LENGTH_LONG)
                        .show()
            }
        }

    }

    fun createAccount( email: String, password: String, displayName: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        startActivity(Intent(this, StartActivity::class.java))
                        finish()


                    } else {

                        Toast.makeText(this, "User Not Created!", Toast.LENGTH_LONG)
                                .show()

                    }

                }
    }
}
