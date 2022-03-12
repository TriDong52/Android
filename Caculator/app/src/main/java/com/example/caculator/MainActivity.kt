package com.example.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buNumEvent(view:View){
        if (isNewOp){
            etShowNumber.setText("")
        }
        isNewOp=false
        val buSelected = view as Button
        var buVal:String = etShowNumber.text.toString()
        when(buSelected.id){
            bu0.id->{
                buVal+="0"
            }
            bu2.id->{
                buVal+="2"
            }
            bu1.id->{
                buVal+="1"
            }
            bu3.id->{
                buVal+="3"
            }
            bu4.id->{
                buVal+="4"
            }
            bu5.id->{
                buVal+="5"
            }
            bu6.id->{
                buVal+="6"
            }
            bu7.id->{
                buVal+="7"
            }
            bu8.id->{
                buVal+="8"
            }
            bu9.id->{
                buVal+="9"
            }
            buDot.id->{
                buVal+="."
            }
            buPlusMins.id->{
                buVal="-"+buVal
            }
        }
        etShowNumber.setText(buVal)
    }
var op="*"
var oldNumber = ""
var isNewOp = true
    fun buOpEvent(view:View){
        val buSelected = view as Button
        when(buSelected.id) {
            buDiv.id -> {
                op = "/"
            }
            buMul.id -> {
                op = "*"
            }
            buSub.id -> {
                op = "-"
            }
            buSum.id -> {
                op = "+"
            }
        }
        oldNumber = etShowNumber.text.toString()
        isNewOp = true

    }

    fun buEqualEvent(view: View){
        val newNumber=etShowNumber.text.toString()
        var finalNumber:Double?=null
        when(op){
            "*"->{
                finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            }
            "+"->{
                finalNumber = oldNumber.toDouble() + newNumber.toDouble()
            }
            "-"->{
                finalNumber = oldNumber.toDouble() - newNumber.toDouble()
            }
            "/"->{
                finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            }
        }
        etShowNumber.setText(finalNumber.toString())
        isNewOp=true
    }

    fun buClean(view: View){
        etShowNumber.setText("0")
        isNewOp = true
    }
}