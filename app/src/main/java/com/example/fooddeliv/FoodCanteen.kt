package com.example.fooddeliv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_food_canteen.*

class FoodCanteen : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_canteen)

        val bundle: Bundle? = intent.extras
        val Image   = bundle!!.getString("Firebase_Image")
        val Title   = bundle.getString("Firebase_Title")

        txtView.setText(Title)

        Picasso.get().load(Image).into(imgView)


        //get data from firebase foodList
        ref = FirebaseDatabase.getInstance().getReference().child("listcanteen")


        var recyclerView:RecyclerView = findViewById(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL, false)

        val option = FirebaseRecyclerOptions.Builder<ModelListCanteen>()
            .setQuery(ref, ModelListCanteen::class.java)
            .build()

        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<ModelListCanteen, MyViewHolder>(option) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_list_item_canteen,parent,false)

                return MyViewHolder(view)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int, modelListCanteen: ModelListCanteen) {
                val refid = getRef(position).key.toString()

                ref.child(refid).addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {


                    }

                    override fun onDataChange(p0: DataSnapshot) {

                        holder.mprice.setText(modelListCanteen.Price)
                        holder.mtitle.setText(modelListCanteen.Title)
                        Picasso.get().load(modelListCanteen.Image).into(holder.mimage)


                    }

                })


            }

        }

        recyclerView.adapter = firebaseRecyclerAdapter
        firebaseRecyclerAdapter.startListening()

    }

    //show title and image
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var mprice: TextView = itemView.findViewById<TextView>(R.id.tvDesc)
        var mtitle: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        var mimage: ImageView = itemView.findViewById(R.id.profile_img)










    }



}





