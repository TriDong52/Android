package com.example.zooapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animal_info.*

class AnimalInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        val b: Bundle? = intent.extras
        val name = b!!.getString("name")
        val des = b!!.getString("des")
        val image = b!!.getInt("image")
        ivAnimalImage.setImageResource(image)
        tvName.text=name
        tvDes.text=des
    }
}

