package com.example.mathfactory
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function6.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*
var real1=""
var imm1=""
var real2=""
var imm2=""
var real=""
var imm=""
var real_bis=""
var imm_bis=""
var id=0
var check=false
var check2=true;
class Function6 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mediaplayer: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function6)
        nav_view6.setNavigationItemSelectedListener(this)
        somma_complessa.setOnClickListener {if(input1()){editText.setText("");editText2.setText("");id=1;setta_colore();check2=false}}
        differenza_complessa.setOnClickListener {if(input1()){editText.setText("");editText2.setText("");id=2;setta_colore();check2=false}}
        moltiplicazione_complessa.setOnClickListener {if(input1()){editText.setText("");editText2.setText("");id=3;setta_colore();check2=false}}
        divisione_complessa.setOnClickListener {if(input1()){editText.setText("");editText2.setText("");id=4;setta_colore();check2=false}}
        calculate6.setOnClickListener {if(input1()){id=5;setta_colore();check2=false}}
        calculate7.setOnClickListener {if(input1()){id=6;setta_colore();check2=false}}
        calculate2.setOnClickListener {if(check){check=false;textView17.setTextColor(Color.BLUE);textView22.setTextColor(Color.BLUE)};if(id!=0){if(input2()){elementare();if(id!=6)visualizza()else visualizza_bis();id=0;setta_colore()};editText.setText("");editText2.setText("");check2=true}else{if(input3()){visualizza()};editText.setText("");editText2.setText("")}}
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
            val next = Intent(this, Function1::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
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
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
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
    private fun input1():Boolean
    {
        if(check2) {
            real1 = editText.text.toString()
            imm1 = editText2.text.toString()
            if ((real1 == "") || (real1 == ".") || (real1 == "-") || (imm1 == "") || (imm1 == ".") || (imm1 == "-")) {
                editText.setText("")
                editText2.setText("")
                if ((real1 == "") || (real1 == ".") || (real1 == "-"))
                    editText.hint = "Error!"
                else
                    editText.hint = "Input..."
                if ((imm1 == "") || (imm1 == ".") || (imm1 == "-"))
                    editText2.hint = "Error!"
                else
                    editText2.hint = "Input..."
                return false
            } else {
                if ((round(real1.toFloat() * 100) / 100).toString() == "-0.0")
                    real1 = "0.0"
                editText.hint = "Input..."
                editText2.hint = "Input..."
                return true
            }
        }
        else
            return false
    }
    private fun input2():Boolean
    {
        real2=editText.text.toString()
        imm2=editText2.text.toString()
        if((real2=="")||(real2==".")||(real2=="-")||(imm2=="")||(imm2==".")||(imm2=="-"))
        {
            editText.setText("")
            editText2.setText("")
            if((real2=="")||(real2==".")||(real2=="-"))
                editText.hint="Error!"
            else
                editText.hint="Input..."
            if((imm2=="")||(imm2==".")||(imm2=="-"))
                editText2.hint="Error!"
            else
                editText2.hint="Input..."
            return false
        }
        else
        {
            if((round(real2.toFloat()*100)/100).toString()=="-0.0")
                real="0.0"
            editText.hint="Input..."
            editText2.hint="Input..."
            return true
        }
    }
    private fun input3():Boolean
    {
        real=editText.text.toString()
        imm=editText2.text.toString()
        if((real=="")||(real==".")||(real=="-")||(imm=="")||(imm==".")||(imm=="-"))
        {
            editText.setText("")
            editText2.setText("")
            if((real=="")||(real==".")||(real=="-"))
                editText.hint="Error!"
            else
                editText.hint="Input..."
            if((imm=="")||(imm==".")||(imm=="-"))
                editText2.hint="Error!"
            else
                editText2.hint="Input..."
            return false
        }
        else
        {
            if((round(real.toFloat()*100)/100).toString()=="-0.0")
                real="0.0"
            editText.hint="Input..."
            editText2.hint="Input..."
            return true
        }
    }
    private fun setta_colore()
    {
        somma_complessa.setTextColor(Color.rgb(81,145,167))
        differenza_complessa.setTextColor(Color.rgb(81,145,167))
        moltiplicazione_complessa.setTextColor(Color.rgb(81,145,167))
        divisione_complessa.setTextColor(Color.rgb(81,145,167))
        calculate6.setTextColor(Color.rgb(81,145,167))
        calculate7.setTextColor(Color.rgb(81,145,167))
        when(id)
        {
            1->somma_complessa.setTextColor(Color.rgb(255,215,0))
            2->differenza_complessa.setTextColor(Color.rgb(255,215,0))
            3->moltiplicazione_complessa.setTextColor(Color.rgb(255,215,0))
            4->divisione_complessa.setTextColor(Color.rgb(255,215,0))
            5->calculate6.setTextColor(Color.rgb(255,215,0))
            6->calculate7.setTextColor(Color.rgb(255,215,0))

        }
    }
    private fun visualizza()
    {
        if((round(real.toFloat()*100)/100).toString()=="-0.0")
            real="0.0"
        if((round(real.toFloat()*100)/100)!=0.toFloat())
        {
            if ((round(imm.toFloat()*100)/100) > 0)
            {

                textView6.text= (round(real.toFloat()*100)/100).toString() + "+" + (round(imm.toFloat()*100)/100).toString() + "i"
                textView21.text=(round(real.toFloat()*100)/100).toString()+"-"+ (round(imm.toFloat()*100)/100).toString()+"i"
            }
            else
                if ((round(imm.toFloat()*100)/100)< 0)
                {
                    textView6.text= (round(real.toFloat()*100)/100).toString() + "-" + ((-1*round(imm.toFloat()*100)/100)).toString() + "i"
                    textView21.text=(round(real.toFloat()*100)/100).toString()+"+"+ ((-1*round(imm.toFloat()*100)/100)).toString()+"i"
                }
                else
                {
                    textView6.text=(round(real.toFloat()*100)/100).toString()
                    textView21.text=(round(real.toFloat()*100)/100).toString()
                }
        }
        else
        {
            if ((round(imm.toFloat()*100)/100)> 0)
            {
                textView6.text= (round(imm.toFloat()*100)/100).toString() + "i"
                textView21.text="-"+ (round(imm.toFloat()*100)/100).toString()+"i"
            }
            else
                if ((round(imm.toFloat()*100)/100) < 0)
                {
                    textView6.text="-" + ((-1*round(imm.toFloat()*100)/100)).toString() + "i"
                    textView21.text=((-1*round(imm.toFloat()*100)/100)).toString()+"i"
                }
                else
                {
                    textView6.text= (round(real.toFloat()*100)/100).toString()
                    textView21.text=(round(real.toFloat()*100)/100).toString()
                }
        }
        val mod=modulo(real.toFloat(),imm.toFloat())
        val phi=fase(real.toFloat(),imm.toFloat())
        textView20.text= (round(real.toFloat()*100)/100).toString()
        textView19.text= (round(imm.toFloat()*100)/100).toString()
        textView18.text=(round(mod.toFloat()*100)/100).toString()
        if(phi!="NaN")
        {
            val phi_grad=(phi.toFloat()*180/PI.toFloat()).toString()
            textView17.text = (round(phi_grad.toFloat()*100)/100).toString()+"°"
            textView22.text=(round(mod.toFloat()*100)/100).toString() +"*exp("+(round(phi_grad.toFloat()*100)/100).toString()+"°i)"
        }
        else
        {
            textView17.text = "Phase not defined!"
            textView22.text="Exponential Form not defined!"
            check=true
            textView17.setTextColor(Color.RED)
            textView22.setTextColor(Color.RED)
        }
    }
    private fun elementare()
    {
        when(id)
        {
            1->somma_complessa(real1.toFloat(),imm1.toFloat(),real2.toFloat(),imm2.toFloat())
            2->sottrazione_complessa(real1.toFloat(),imm1.toFloat(),real2.toFloat(),imm2.toFloat())
            3->moltiplicazione_complessa(real1.toFloat(),imm1.toFloat(),real2.toFloat(),imm2.toFloat())
            4->divisione_complessa(real1.toFloat(),imm1.toFloat(),real2.toFloat(),imm2.toFloat())
            5->quadrato_complesso(real1.toFloat(),imm1.toFloat())
            6->radice_complessa(real1.toFloat(),imm1.toFloat())
        }
    }
    private fun somma_complessa(real1:Float,imm1:Float,real2:Float,imm2:Float)
    {
        real=(real1+real2).toString()
        imm=(imm1+imm2).toString()
    }
    private fun sottrazione_complessa(real1:Float,imm1:Float,real2:Float,imm2:Float)
    {
        real=(real1-real2).toString()
        imm=(imm1-imm2).toString()
}
    private fun modulo(real:Float,imm:Float):String
    {
        return sqrt(real.pow(2)+imm.pow(2)).toString()
    }
    private fun fase(real:Float,imm:Float):String
    {
        if((imm>=0)&&(real>=0))
         return atan(imm/real).toString()
        else
            if((imm>=0)&&(real<0))
              return (PI+atan(imm/real)).toString()
             else
                if((imm<0)&&(real<0))
                    return (PI+atan(imm/real)).toString()
                else
                        return (2*PI+atan(imm/real)).toString()
    }
    private fun moltiplicazione_complessa(real1:Float,imm1:Float,real2:Float,imm2:Float)
    {
        real=(modulo(real1,imm1).toFloat()*modulo(real2,imm2).toFloat()*cos(fase(real1,imm1).toFloat()+fase(real2,imm2).toFloat())).toString()
         imm=(modulo(real1,imm1).toFloat()*modulo(real2,imm2).toFloat()* sin(fase(real1,imm1).toFloat()+fase(real2,imm2).toFloat())).toString()
    }
    private fun divisione_complessa(real1:Float,imm1:Float,real2:Float,imm2:Float)
    {
        real=(modulo(real1,imm1).toFloat()/modulo(real2,imm2).toFloat()*cos(fase(real1,imm1).toFloat()-fase(real2,imm2).toFloat())).toString()
        imm=(modulo(real1,imm1).toFloat()/modulo(real2,imm2).toFloat()* sin(fase(real1,imm1).toFloat()-fase(real2,imm2).toFloat())).toString()
    }
    private fun quadrato_complesso(real1:Float,imm1:Float)
    {
        real=(modulo(real1,imm1).toFloat().pow(2)*cos(2*fase(real1,imm1).toFloat())).toString()
         imm=(modulo(real1,imm1).toFloat().pow(2)* sin(2*fase(real1,imm1).toFloat())).toString()
    }
    private fun radice_complessa(real1:Float,imm1:Float)
    {
        real=(sqrt(modulo(real1,imm1).toFloat())*cos(fase(real1,imm1).toFloat()/2)).toString()
        imm= (sqrt(modulo(real1,imm1).toFloat()) * sin(fase(real1,imm1).toFloat()/2)).toString()
        real_bis=(sqrt(modulo(real1,imm1).toFloat())*cos((fase(real1,imm1).toFloat()+2*PI.toFloat())/2)).toString()
        imm_bis= (sqrt(modulo(real1,imm1).toFloat()) * sin((fase(real1,imm1).toFloat()+2*PI.toFloat())/2)).toString()
    }
    private fun visualizza_bis()
    {
        if((round(real.toFloat()*100)/100).toString()=="-0.0")
            real="0.0"
        if((round(real_bis.toFloat()*100)/100).toString()=="-0.0")
            real_bis="0.0"
        if((round(imm_bis.toFloat()*100)/100).toString()=="-0.0")
            imm_bis="0.0"
        if((round(real_bis.toFloat()*100)/100)!=0.toFloat())
        {
            if ((round(imm.toFloat()*100)/100) > 0)
            {

                textView6.text= (round(real.toFloat()*100)/100).toString() + "+" + (round(imm.toFloat()*100)/100).toString() + "i"+"\n"+(round(real_bis.toFloat()*100)/100).toString()+"-"+ (-1*round(imm_bis.toFloat()*100)/100).toString() + "i"
                textView21.text=(round(real.toFloat()*100)/100).toString()+"-"+ (round(imm.toFloat()*100)/100).toString()+"i"+"   "+(round(real_bis.toFloat()*100)/100).toString() + "+" + (-1*round(imm_bis.toFloat()*100)/100).toString() + "i"
            }
            else
                if ((round(imm.toFloat()*100)/100)< 0)
                {
                    textView6.text= (round(real.toFloat()*100)/100).toString() + "-" + ((-1*round(imm.toFloat()*100)/100)).toString() + "i"+"\n"+(round(real_bis.toFloat()*100)/100).toString() + "+" + ((round(imm_bis.toFloat()*100)/100)).toString()
                    textView21.text=(round(real.toFloat()*100)/100).toString()+"+"+ ((-1*round(imm.toFloat()*100)/100)).toString()+"i"+"   "+(round(real_bis.toFloat()*100)/100).toString() + "-" + ((round(imm_bis.toFloat()*100)/100)).toString()
                }
                else
                {
                    textView6.text=(round(real.toFloat()*100)/100).toString()+"\n"+(round(real_bis.toFloat()*100)/100).toString()
                    textView21.text=(round(real.toFloat()*100)/100).toString()+"   "+(round(real_bis.toFloat()*100)/100).toString()
                }
        }
        else
        {
            if ((round(imm.toFloat()*100)/100)> 0)
            {
                textView6.text= (round(imm.toFloat()*100)/100).toString() + "i"+"\n"+(round(imm_bis.toFloat()*100)/100).toString()
                textView21.text="-"+ (round(imm.toFloat()*100)/100).toString()+"i"+"   "+"-"+ (round(imm_bis.toFloat()*100)/100).toString()+"i"
            }
            else
                if ((round(imm.toFloat()*100)/100) < 0)
                {
                    textView6.text="-" + ((-1*round(imm.toFloat()*100)/100)).toString() + "i"+"\n"+"+" + ((round(imm_bis.toFloat()*100)/100)).toString()
                    textView21.text=((-1*round(imm.toFloat()*100)/100)).toString()+"i"+"   -"+((round(imm_bis.toFloat()*100)/100)).toString()
                }
                else
                {
                    textView6.text= (round(real.toFloat()*100)/100).toString()+"\n"+(round(real_bis.toFloat()*100)/100).toString()
                    textView21.text=(round(real.toFloat()*100)/100).toString()+"   "+(round(real_bis.toFloat()*100)/100).toString()
                }
        }
        val mod=modulo(real.toFloat(),imm.toFloat())
        val phi=fase(real.toFloat(),imm.toFloat())
        val mod_bis=modulo(real_bis.toFloat(),imm_bis.toFloat())
        val phi_bis=fase(real_bis.toFloat(),imm_bis.toFloat())
        textView20.text= (round(real.toFloat()*100)/100).toString()+"   "+(round(real_bis.toFloat()*100)/100).toString()
        textView19.text= (round(imm.toFloat()*100)/100).toString()+"   "+(round(imm_bis.toFloat()*100)/100).toString()
        textView18.text=(round(mod.toFloat()*100)/100).toString()+"   "+(round(mod_bis.toFloat()*100)/100).toString()
        if(phi!="NaN")
        {
            val phi_grad=(phi.toFloat()*180/PI.toFloat()).toString()
            val phi_grad_bis=(phi_bis.toFloat()*180/PI.toFloat()).toString()
            textView17.text = (round(phi_grad.toFloat()*100)/100).toString()+"°   "+(round(phi_grad_bis.toFloat()*100)/100).toString()+"°"
            textView22.text=(round(mod.toFloat()*100)/100).toString() +"*exp("+(round(phi_grad.toFloat()*100)/100).toString()+"°i)"+"\n"+(round(mod_bis.toFloat()*100)/100).toString() +"*exp("+(round(phi_grad_bis.toFloat()*100)/100).toString()+"°i)"
        }
        else
        {
            textView17.text = "Phase not defined!"
            textView22.text="Exponential Form not defined!"
            check=true
            textView17.setTextColor(Color.RED)
            textView22.setTextColor(Color.RED)
        }
    }
}

