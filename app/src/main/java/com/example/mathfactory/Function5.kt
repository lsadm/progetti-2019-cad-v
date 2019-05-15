package com.example.mathfactory

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function5.*
import kotlin.random.Random
var controllo=false
class Function5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function5)
        calculate.setOnClickListener { random();editText.setText("");editText2.setText("");editText3.setText("")}
    }
    override fun onCreateOptionsMenu(menu: Menu):Boolean
    {
        menuInflater.inflate(R.menu.menu_application2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        val id=item.getItemId()
        if(id==R.id.action_one)
        {
            Toast.makeText(this,"MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if(id==R.id.action_two)
        {
            Toast.makeText(this,"Enjoy with Math!", Toast.LENGTH_LONG).show()
            return true
        }
        if(id==R.id.action_three)
        {
            Toast.makeText(this,"Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if(id==R.id.action_home)
        {
            val next= Intent(this,MainActivity::class.java)
            startActivity(next)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun random()
    {
        textView.text = ""
        if(controllo)
        {
            textView.setTextColor(Color.BLUE)
            controllo=false
        }
        if((editText.text.toString()=="")&&(editText2.text.toString()!=""))
        {
            textView.setTextColor(Color.RED)
            textView.text=" Warning: left extreme is missing!"
            controllo=true
        }
        else
            if((editText.text.toString()!="")&&(editText2.text.toString()==""))
            {
                textView.setTextColor(Color.RED)
                textView.text=" Warning: right extreme is missing!"
                controllo=true
            }
            else
                if((editText.text.toString()=="")&&(editText2.text.toString()==""))
                {
                    textView.setTextColor(Color.RED)
                    textView.text=" Warning: extremes are missing both!"
                    controllo=true
                }
                else
                {
                    if ((editText.text.toString().toDouble() > 1000000000) || (editText2.text.toString().toDouble() > 1000000000)) {
                        textView.setTextColor(Color.RED)
                        textView.text = " Warning: Error Overflow!"
                        controllo = true
                    } else {
                        val inf = editText.text.toString().toInt()
                        val sup = editText2.text.toString().toInt()
                        if (inf > sup) {
                            textView.setTextColor(Color.RED)
                            textView.text = " Warning: right extreme can't be less than\n left extreme!"
                            controllo = true
                        }
                        else
                        {
                            if(editText3.text.toString()=="") {
                                textView.setTextColor(Color.RED)
                                textView.text = " Warning: the field of random generations'\n number can't be empty!"
                                controllo=true
                            }
                            else {
                                val n = editText3.text.toString().toInt()
                                if (n > 30) {
                                    textView.setTextColor(Color.RED)
                                    textView.text = " Warning: to many numbers to generate\n randomly!"
                                    controllo = true
                                }
                                else
                                if(n==0)
                                {
                                    textView.setTextColor(Color.RED)
                                    textView.text = " Warning: I can't generate 0 random\n numbers!"
                                    controllo=true
                                }
                                else
                                {
                                    val random_value = List(n) { Random.nextInt(inf, sup + 1) }
                                    var i = 0
                                    while (i < n) {
                                        if (i < 10)
                                        {
                                            textView.text = textView.text.toString() + (i + 1).toString() + ") " + random_value[i].toString()
                                            if(i+1<n)
                                            textView.text =textView.text.toString()+"                   " + (i + 2).toString() + ") " + random_value[i + 1].toString() + "\n"
                                        }
                                            else
                                        {
                                            textView.text = textView.text.toString() + (i + 1).toString() + ") " + random_value[i].toString()
                                            if(i+1<n)
                                            textView.text =textView.text.toString()+"                 " + (i + 2).toString() + ") " + random_value[i + 1].toString() + "\n"
                                        }
                                        i = i + 2
                                    }
                                }
                            }
                        }
                    }
                }
    }
}

