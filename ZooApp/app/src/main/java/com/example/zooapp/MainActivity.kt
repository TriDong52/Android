package com.example.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {
    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfAnimals.add(
            Animal("Babon","Babon live in zoo DongHa",R.drawable.baboon,false))
        listOfAnimals.add(
            Animal("Bulldog","Bulldog live in zoo DongHa",R.drawable.bulldog,false))
        listOfAnimals.add(
            Animal("Panda","Panda live in zoo DongHa",R.drawable.panda,false))
        listOfAnimals.add(
            Animal("White Tiger","White Tiger live in zoo DongHa",R.drawable.white_tiger,true))
        listOfAnimals.add(
            Animal("Zebra","Zebra live in zoo DongHa",R.drawable.zebra,false))
        listOfAnimals.add(
            Animal("Swallow bird","Swallow bird live in zoo DongHa",R.drawable.swallow_bird,false))

        adapter = AnimalAdapter(this,listOfAnimals)
        tvListAnimal.adapter = adapter
    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalAdapter:BaseAdapter {
        var listOfAnimals = ArrayList<Animal>()
        var context:Context?=null
        constructor(context:Context,listOfAnimals: ArrayList<Animal>):super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }
        override fun getCount(): Int {
            return listOfAnimals.size
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listOfAnimals[p0]
            if( animal.isKiller == true ){
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimal.setImageResource(animal.image!!)
                myView.setOnClickListener {
                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }else{
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimal.setImageResource(animal.image!!)
                myView.setOnClickListener {
                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }
        }

    }
}

