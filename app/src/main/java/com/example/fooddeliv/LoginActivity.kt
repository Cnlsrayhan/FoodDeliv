package com.example.fooddeliv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener  {

    private val TAG = "LoginActivity"
    //declare firebase variables
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this,"Welcome", Toast.LENGTH_SHORT).show()

        //Get Firebase
        mAuth = FirebaseAuth.getInstance()



        // set the onClick Listener method upon activity creation
        login_btn.setOnClickListener(this)
        txt_link.setOnClickListener(this)
    }

    override fun onClick(view: View){
        when(view.id) {
            R.id.login_btn -> {
                val email = edt_username.text.toString()
                val password = edt_password.text.toString()
                if (validate(email, password)) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()

                        } else {
                            showToast("You must have entered the wrong email or password")
                        }
                    }
                } else {
                    showToast("Please enter email and password")
                }

            }
        }
        when(view.id) {
            R.id.txt_link -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()
}
