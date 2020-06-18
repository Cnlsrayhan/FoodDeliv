package com.example.fooddeliv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_food_canteen.*

class FoodCanteen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_canteen)

        val bundle: Bundle? = intent.extras
        val Image  = bundle!!.getString("Firebase_Image")
        val Title = bundle.getString("Firebase_Title")

        txtView.setText(Title)

        Picasso.get().load(Image).into(imgView)
    }
}
