package com.example.fooddeliv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        when(view.id) {
            R.id.logout_btn -> {
                mAuth.signOut()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }
    }

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logout_btn.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()

    }
}
