package com.example.fooddeliv

import android.content.Context
import android.widget.Toast
import java.time.Duration

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,text,duration).show()
}