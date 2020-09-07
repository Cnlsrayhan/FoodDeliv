package com.example.fooddeliv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class ListCanteen : AppCompatActivity() {
    lateinit var mrecyclerview : RecyclerView
    lateinit var ref : DatabaseReference
    lateinit var show_progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_canteen)

        mrecyclerview = findViewById(R.id.recyclerview)

        //get data from firebase foodList
        ref = FirebaseDatabase.getInstance().getReference().child("foodlist")

        mrecyclerview.layoutManager= LinearLayoutManager(this)

        show_progress = findViewById(R.id.progress_bar)

        

        val option = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(ref, Model::class.java)
            .build()

        val firebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Model, MyViewHolder>(option) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(this@ListCanteen).inflate(R.layout.cardview,parent,false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Model) {
                val refid = getRef(position).key.toString()

                ref.child(refid).addValueEventListener(object: ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {


                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        show_progress.visibility = if (itemCount == 0) View.VISIBLE else View.GONE

                        holder.mtitle.setText(model.Name)
                        Picasso.get().load(model.Image).into(holder.mimage)

                        //Click Event
                        holder.itemView.setOnClickListener {
                            val intent = Intent(this@ListCanteen, FoodCanteen::class.java)
                            intent.putExtra("Firebase_Image",model.Image)
                            intent.putExtra("Firebase_Title",model.Name)
                            startActivity(intent)
                        }

                    }

                })


            }

        }

        mrecyclerview.adapter = firebaseRecyclerAdapter
        firebaseRecyclerAdapter.startListening()



    }

    //show title and image
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var mtitle: TextView = itemView.findViewById<TextView>(R.id.Display_title)
        var mimage: ImageView = itemView.findViewById(R.id.Display_img)


    }

}
