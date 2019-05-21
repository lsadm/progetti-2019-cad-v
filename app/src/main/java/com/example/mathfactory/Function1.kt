package com.example.mathfactory

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function1.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*

class Function1 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mediaplayer: MediaPlayer?=null
    private var switch= arrayOf(true,true,true,true,true,true)
    var identifier:Int=0
    var controllo:Boolean=false
    var parametri=arrayOf(0.0,0.0,0.0,0.0)
    var passo:Double=0.0
    var inf:Double=0.0
    var sup:Double=0.0
    var titolo:String=""
    var titolo2:String=""
    var controllo_reset=false
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function1)
        nav_view1.setNavigationItemSelectedListener(this)
        controllo_generale=3
        button8.setOnClickListener {if((switch[0]&&switch[1]&&switch[2]&&switch[3]&&switch[4]&&switch[5])||!switch[0]){reset();identifier=1;if(switch[identifier-1]){setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=false;set_color(identifier)}else{setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=true;set_color(0)}}}
        button9.setOnClickListener {if((switch[0]&&switch[1]&&switch[2]&&switch[3]&&switch[4]&&switch[5])||!switch[1]){reset();identifier=2;if(switch[identifier-1]){setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=false;set_color(identifier)}else{setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=true;set_color(0)}}}
        button10.setOnClickListener {if((switch[0]&&switch[1]&&switch[2]&&switch[3]&&switch[4]&&switch[5])||!switch[2]){reset();identifier=3;if(switch[identifier-1]){setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=false;set_color(identifier)}else{setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=true;set_color(0)}}}
        button30.setOnClickListener {if((switch[0]&&switch[1]&&switch[2]&&switch[3]&&switch[4]&&switch[5])||!switch[3]){reset();identifier=4;if(switch[identifier-1]){setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=false;set_color(identifier)}else{setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=true;set_color(0)}}}
        button31.setOnClickListener {if((switch[0]&&switch[1]&&switch[2]&&switch[3]&&switch[4]&&switch[5])||!switch[4]){reset();identifier=5;if(switch[identifier-1]){setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=false;set_color(identifier)}else{setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=true;set_color(0)}}}
        button11.setOnClickListener {if((switch[0]&&switch[1]&&switch[2]&&switch[3]&&switch[4]&&switch[5])||!switch[5]){reset();identifier=6;if(switch[identifier-1]){setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=false;set_color(identifier)}else{setta_elementi(switch[identifier-1],identifier);switch[identifier-1]=true;set_color(0)}}}
        button20.setOnClickListener { controllo=input_output(identifier);if(controllo){set_color(7);set_color(identifier)} }
        button17.setOnClickListener {if(controllo){controllo_reset=true;parametri_fondamentali();val next=Intent(this,Grafici::class.java);next.putExtra("passo", passo);next.putExtra("inf", inf);next.putExtra("sup", sup);next.putExtra("identifier", identifier+27);if((identifier==1)||(identifier==3)||(identifier==5)||(identifier==6)){next.putExtra("identifier2", identifier+27);next.putExtra("titolo2", titolo2)};next.putExtra("titolo", titolo);next.putExtra("primo", parametri[0]);next.putExtra("secondo", parametri[1]);next.putExtra("terzo", parametri[2]);next.putExtra("quarto", parametri[3]);reset();setta_elementi(false,0);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_graph_sound);mediaplayer?.start()}}
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
            Toast.makeText(this,"Hi, do you want to see any\ngeometric places?", Toast.LENGTH_LONG).show()
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
            mediaplayer=MediaPlayer.create(this,R.raw.move_home_sound)
            mediaplayer?.start()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        val id = item.getItemId()
        if (id == R.id.action_four) {
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_five) {
            val next = Intent(this, Function2::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_six) {
            val next = Intent(this, Function3::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_seven) {
            val next = Intent(this, Function4::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_eight) {
            val next = Intent(this, Function5::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_nine) {
            val next = Intent(this, Function6::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_ten) {
            val next = Intent(this, Function7::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_history_window) {
            val next = Intent(this, Function8::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        return true
    }
    private fun setta_elementi(controllo:Boolean,tipo:Int)
    {
        if(controllo)
        {
            button20.visibility= View.VISIBLE
            button17.visibility= View.VISIBLE
            textView30.visibility= View.VISIBLE
            when(tipo)
            {
                1->
                {
                    textView24.visibility=View.VISIBLE
                    textView26.visibility=View.VISIBLE
                    editText4.visibility=View.VISIBLE
                    editText6.visibility=View.VISIBLE
                    textView29.visibility=View.VISIBLE
                    editText10.visibility=View.VISIBLE
                    textView24.text="First Side Lenght:"
                    textView26.text="Second Side Lenght:"
                    textView29.text="Corner Width\n(Degrees):"
                    titolo="Tracing of first two sides of the Triangle"
                    titolo2="Tracing of last one side of the Triangle"
                }
                2->
                {
                    textView24.visibility=View.VISIBLE
                    textView26.visibility=View.VISIBLE
                    editText4.visibility=View.VISIBLE
                    editText6.visibility=View.VISIBLE
                    textView24.text="Angular Coefficient:"
                    textView26.text="Origin\nIntercept:"
                    titolo="Tracing of Straight"
                }
                3->
                {
                    textView24.visibility=View.VISIBLE
                    textView26.visibility=View.VISIBLE
                    editText4.visibility=View.VISIBLE
                    editText6.visibility=View.VISIBLE
                    textView29.visibility=View.VISIBLE
                    editText10.visibility=View.VISIBLE
                    textView24.text="Origin\nAbscissa:"
                    textView26.text="Origin\nOrdinate:"
                    textView29.text="Radius:"
                    titolo="Tracing of down Semi-Circumference"
                    titolo2="Tracing of up Semi-Circumference"
                }
                4->
                {
                    textView24.visibility=View.VISIBLE
                    textView26.visibility=View.VISIBLE
                    editText4.visibility=View.VISIBLE
                    editText6.visibility=View.VISIBLE
                    textView29.visibility=View.VISIBLE
                    editText10.visibility=View.VISIBLE
                    textView24.text="a:"
                    textView26.text="b:"
                    textView29.text="c:"
                    titolo="Tracing of Parable"
                }
                5,6->
                {
                    textView24.visibility=View.VISIBLE
                    textView26.visibility=View.VISIBLE
                    editText4.visibility=View.VISIBLE
                    editText6.visibility=View.VISIBLE
                    textView29.visibility=View.VISIBLE
                    textView28.visibility=View.VISIBLE
                    editText10.visibility=View.VISIBLE
                    editText9.visibility=View.VISIBLE
                    textView24.text="Origin\nAbscissa:"
                    textView26.text="Origin\nOrdinate:"
                    textView29.text="First\nSemi-Axis:"
                    textView28.text="Second\nSemi-Axis:"
                    if(identifier==5)
                    {
                        titolo = "Tracing of down Semi-Ellipse"
                        titolo2 = "Tracing of up Semi-Ellipse"
                    }
                    else
                    {
                        titolo = "Tracing of Hyperbole in 1° and 3° Clock Face"
                        titolo2 = "Tracing of Hyperbole in 2° and 4° Clock Face"
                    }
                }
            }
        }
        else
        {
            button20.visibility= View.INVISIBLE
            button17.visibility= View.INVISIBLE
            textView30.visibility= View.INVISIBLE
            textView24.visibility=View.INVISIBLE
            textView26.visibility=View.INVISIBLE
            editText4.visibility=View.INVISIBLE
            editText6.visibility=View.INVISIBLE
            textView29.visibility=View.INVISIBLE
            textView28.visibility=View.INVISIBLE
            editText10.visibility=View.INVISIBLE
            editText9.visibility=View.INVISIBLE
        }
    }
    private fun set_color(id:Int)
    {
        val not_selected=rgb(81,153,167)
        val selected=rgb(255,215,0)
        val selected2=rgb(40,114,51)
        val selected3=(rgb(155,17,30))
        button8.setTextColor(not_selected)
        button9.setTextColor(not_selected)
        button10.setTextColor(not_selected)
        button30.setTextColor(not_selected)
        button31.setTextColor(not_selected)
        button11.setTextColor(not_selected)
        when(id)
        {
            1->button8.setTextColor(selected)
            2->button9.setTextColor(selected)
            3->button10.setTextColor(selected)
            4->button30.setTextColor(selected)
            5->button31.setTextColor(selected)
            6->button11.setTextColor(selected)
            7->button20.setTextColor(selected2)
            8->button20.setTextColor(selected3)
        }
    }
    private fun input_output(id:Int):Boolean
    {
       if(identifier!=0)
       {
           when (id) {
               1,3, 4 -> {
                   if ((editText4.text.toString() != "") && (editText4.text.toString() != ".") && (editText4.text.toString() != "-") && (editText6.text.toString() != "") && (editText6.text.toString() != ".") && (editText6.text.toString() != "-") && (editText10.text.toString() != "") && (editText10.text.toString() != ".") && (editText10.text.toString() != "-")) {
                       textView30.setTextColor(Color.BLUE)
                       parametri[0] = editText4.text.toString().toDouble()
                       if((parametri[0]==0.0)&&(identifier==4))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter a can't be null!"
                           return false
                       }
                       if((parametri[0]<=0.0)&&(identifier==1))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter First Side Lenght can't be equal or less than 0!"
                       }
                       parametri[1] = editText6.text.toString().toDouble()
                       if((parametri[1]<=0.0)&&(identifier==1))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter Second Side Lenght can't be equal or less than 0!"
                       }
                       parametri[2] = editText10.text.toString().toDouble()
                       if((parametri[2]<=0.0)&&(identifier==3))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter Radius can't be equal or less than 0!"
                       }
                       if((parametri[2]==0.0)&&(identifier==1))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter Corner Width (Degrees) can't be equal than 0!"
                       }
                       if((parametri[0]<=0.0)&&(parametri[1]<=0.0)&&(identifier==1))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter First Side Lenght and Second Side Lenght can't be equal or less than 0!"
                       }
                       if((parametri[0]<=0.0)&&(parametri[2]==0.0)&&(identifier==1))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter First Side Lenght can't be equal or less than 0 and Corner Width (Degrees) can't be equal than 0!"
                       }
                       if((parametri[1]<=0.0)&&(parametri[2]==0.0)&&(identifier==1))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter Second Side Lenght can't be equal or less than 0 and Corner Width (Degrees) can't be equal than 0!"
                       }
                       if((parametri[0]<=0.0)&&(parametri[1]<=0.0)&&(parametri[2]==0.0)&&(identifier==1))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter First Side Lenght and Second Side Lenght can't be equal or less than 0 and Corner Width (Degrees) can't be equal than 0!"
                       }
                       if(((parametri[0]<=0.0)||(parametri[1]<=0.0)||(parametri[2]==0.0))&&(identifier==1))
                           return false
                       if(identifier==1)
                           textView30.text="Triangles is ready to be plotted!"
                       if(identifier==3)
                           textView30.text="Circumferences is ready to be plotted!"
                       if(identifier==4)
                           textView30.text="Parables is ready to be plotted!"
                       return true
                   }
                   else
                   {
                       textView30.setTextColor(Color.RED)
                       textView30.text="Wrong or Missing Parameters!"
                       return false
                   }
               }
               2 -> {
                   if ((editText4.text.toString() != "") && (editText4.text.toString() != ".") && (editText4.text.toString() != "-") && (editText6.text.toString() != "") && (editText6.text.toString() != ".") && (editText6.text.toString() != "-")) {
                       textView30.setTextColor(Color.BLUE)
                       parametri[0] = editText4.text.toString().toDouble()
                       parametri[1] = editText6.text.toString().toDouble()
                       if(identifier==2)
                           textView30.text="Straights is ready to be plotted!"
                       return true
                   } else
                   {
                       textView30.setTextColor(Color.RED)
                       textView30.text="Parameters Missing!"
                       return false
                   }
               }
               5, 6 -> {
                   if ((editText4.text.toString() != "") && (editText4.text.toString() != ".") && (editText4.text.toString() != "-") && (editText6.text.toString() != "") && (editText6.text.toString() != ".") && (editText6.text.toString() != "-") && (editText10.text.toString() != "") && (editText10.text.toString() != ".") && (editText10.text.toString() != "-") && (editText9.text.toString() != "") && (editText9.text.toString() != ".") && (editText9.text.toString() != "-")) {
                       textView30.setTextColor(Color.BLUE)
                       parametri[0] = editText4.text.toString().toDouble()
                       parametri[1] = editText6.text.toString().toDouble()
                       parametri[2] = editText10.text.toString().toDouble()
                       if((parametri[2]<=0.0))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter First Semi-Axis can't be equal or less than 0!"
                       }
                       parametri[3] = editText9.text.toString().toDouble()
                       if((parametri[3]<=0.0))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameter Second Semi-Axis can't be equal or less than 0!"
                       }
                       if((parametri[2]<=0.0)&&(parametri[3]<=0.0))
                       {
                           textView30.setTextColor(Color.RED)
                           textView30.text="Error: parameters First Semi-Axis and Second\nSemi-Axis can't be equal or less than 0!"
                           return false
                       }
                       if((parametri[2]<=0.0)||(parametri[3]<=0.0))
                           return false
                       if(identifier==5)
                           textView30.text="Ellipses is ready to be plotted!"
                       if(identifier==6)
                           textView30.text="Hyperboles is ready to be plotted!"
                       return true
                   } else
                   {
                       textView30.setTextColor(Color.RED)
                       textView30.text="Parameters Missing!"
                       return false
                   }
               }
               else->return false
           }
       }
      else
           return false
    }
    private fun reset()
    {
        set_color(8)
        textView30.text=""
        editText4.setText("")
        editText6.setText("")
        editText10.setText("")
        editText9.setText("")
        if(controllo_reset)
        for(i in 0..5)
            switch[i]=true
        controllo_reset=false
    }
    private fun parametri_fondamentali()
    {
        when(identifier)
        {
            1->
            {
                passo=1.0
                inf=0.0
                sup=3.0
            }
            2->
            {
                passo=0.1
                inf=-10.0-parametri[1]
                sup=10.0+parametri[1]
            }
            3,5->
            {
                passo=0.01
                inf=-3.14
                sup=0.0
            }
            4->
            {
                passo=0.1
                inf=-parametri[1]/(2*parametri[0])-10
                sup=-parametri[1]/(2*parametri[0])+10
            }
            6->
            {
                passo=0.0001
                inf=-3.1415
                sup=3.1415
            }
        }
    }
}

