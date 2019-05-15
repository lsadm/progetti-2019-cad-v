package com.example.mathfactory

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function2.*

class Function2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function2)
        var obj=Equazioni()
        calculate.setOnClickListener {if(obj.controllo_algebrico){textView.setTextColor(Color.BLUE);obj.controllo_algebrico=false};obj.a=editText.text.toString();obj.b=editText2.text.toString();obj.c=editText3.text.toString();obj.risolvi();if(obj.controllo_algebrico)textView.setTextColor(Color.RED);textView.text=obj.soluzione_algebrica;editText.setText("");editText2.setText("");editText3.setText("")}
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
}

