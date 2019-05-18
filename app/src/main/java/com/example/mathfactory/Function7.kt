package com.example.mathfactory

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.rgb
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function7.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*
class Function7 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var passo:Double=0.1
    var inf:Double=0.0
    var sup:Double=0.1
    var A:Double=0.0
    var T:Double=0.0
    var rit:Double=0.0
    var o:Double=0.0
    var D:Double=0.0
    var f1:Double=0.0
    var f2:Double=0.0
    var tau1:Double=0.0
    var tau2:Double=0.0
    var omega:Double=0.0
    var real:Double=0.0
    var imm:Double=0.0
    var titolo=""
    var A2:Double=0.0
    var T2:Double=0.0
    var rit2:Double=0.0
    var o2:Double=0.0
    var D2:Double=0.0
    var f1_2:Double=0.0
    var f2_2:Double=0.0
    var tau1_2:Double=0.0
    var tau2_2:Double=0.0
    var omega2:Double=0.0
    var real2:Double=0.0
    var imm2:Double=0.0
    var titolo2=""
    var identifier:Int=0
    var turno=false
    var interpolat=false
    var interpolat2=false
    var identifier2:Int=0
    var controllo=true
    var controllo2=true
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function7)
        nav_view7.setNavigationItemSelectedListener(this)
        controllo_generale=2
        button8.setOnClickListener {if(!turno)identifier=0 else identifier2=0;set_color(18);if(!interpolat){interpolat=true;interpolat2=true;button8.text="Interpolat\nOn"}else{interpolat=false;interpolat2=false;button8.text="Interpolat\nOff"}}
        button9.setOnClickListener {if(!turno){identifier=9;set_color(identifier)}else{identifier2=9;set_color(identifier2)}}
        button10.setOnClickListener {if(!turno){identifier=10;set_color(identifier)}else{identifier2=10;set_color(identifier2)}}
        button11.setOnClickListener {if(!turno){identifier=11;set_color(identifier)}else{identifier2=11;set_color(identifier2)}}
        button30.setOnClickListener {if(!turno){identifier=12;set_color(identifier)}else{identifier2=12;set_color(identifier2)}}
        button31.setOnClickListener {if(!turno){identifier=21;set_color(identifier)}else{identifier2=21;set_color(identifier2)}}
        button32.setOnClickListener {if(!turno){identifier=22;set_color(identifier)}else{identifier2=22;set_color(identifier2)}}
        button33.setOnClickListener {if(!turno){identifier=23;set_color(identifier)}else{identifier2=23;set_color(identifier2)}}
        button37.setOnClickListener {if(!turno){identifier=24;set_color(identifier)}else{identifier2=24;set_color(identifier2)}}
        button38.setOnClickListener {if(!turno){identifier=25;set_color(identifier)}else{identifier2=25;set_color(identifier2)}}
        button39.setOnClickListener {if(!turno){identifier=26;set_color(identifier)}else{identifier2=26;set_color(identifier2)}}
        button40.setOnClickListener {if(!turno){identifier=27;set_color(identifier)}else{identifier2=27;set_color(identifier2)}}
        button20.setOnClickListener {if((identifier!=18)&&(!turno)){set_color(28);if(input_output())textView30.setTextColor(Color.RED)else textView30.setTextColor(Color.BLUE);if(identifier!=0)turno=true}}
        button22.setOnClickListener {if((identifier2!=18)&&(turno)){set_color(29);if(input_output_bis())textView23.setTextColor(Color.RED)else textView23.setTextColor(Color.BLUE);if(identifier2!=0)turno=false} }
        button17.setOnClickListener {parametri_fondamentali();val next= Intent(this,Grafici::class.java);next.putExtra("passo", passo);next.putExtra("inf", inf);next.putExtra("sup", sup);next.putExtra("identifier", identifier);next.putExtra("A", A);next.putExtra("T", T);next.putExtra("rit", rit);next.putExtra("o", o);next.putExtra("real", real);next.putExtra("f1", f1);next.putExtra("f2", f2);next.putExtra("tau1", tau1);next.putExtra("tau2", tau2);next.putExtra("imm", imm);next.putExtra("titolo",titolo);next.putExtra("interpolat",interpolat);next.putExtra("controllo",controllo);next.putExtra("identifier2", identifier2);next.putExtra("A2", A2);next.putExtra("T2", T2);next.putExtra("rit2", rit2);next.putExtra("o2", o2);next.putExtra("real2", real2);next.putExtra("f1_2", f1_2);next.putExtra("f2_2", f2_2);next.putExtra("tau1_2", tau1_2);next.putExtra("tau2_2", tau2_2);next.putExtra("imm2", imm2);next.putExtra("titolo2",titolo2);next.putExtra("interpolat2",interpolat2);next.putExtra("controllo2",controllo2);reset();startActivity(next)}
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
    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        val id = item.getItemId()
        if (id == R.id.action_four) {
            val next = Intent(this, Function1::class.java)
            startActivity(next)
            return true
        }
        if (id == R.id.action_five) {
            val next = Intent(this, Function2::class.java)
            startActivity(next)
            return true
        }
        if (id == R.id.action_six) {
            val next = Intent(this, Function3::class.java)
            startActivity(next)
            return true
        }
        if (id == R.id.action_seven) {
            val next = Intent(this, Function4::class.java)
            startActivity(next)
            return true
        }
        if (id == R.id.action_eight) {
            val next = Intent(this, Function5::class.java)
            startActivity(next)
            return true
        }
        if (id == R.id.action_nine) {
            val next = Intent(this, Function6::class.java)
            startActivity(next)
            return true
        }
        if (id == R.id.action_history_window) {
            val next = Intent(this, Function8::class.java)
            startActivity(next)
            return true
        }
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            startActivity(next)
            return true
        }
        return true
    }
    private fun set_color(id:Int)
    {
        val not_selected=rgb(81,153,167)
        val selected=rgb(255,215,0)
        val selected2=rgb(40,114,51)
        val selected3=(rgb(155,17,30))
        button9.setTextColor(not_selected)
        button10.setTextColor(not_selected)
        button11.setTextColor(not_selected)
        button30.setTextColor(not_selected)
        button31.setTextColor(not_selected)
        button32.setTextColor(not_selected)
        button33.setTextColor(not_selected)
        button37.setTextColor(not_selected)
        button38.setTextColor(not_selected)
        button39.setTextColor(not_selected)
        button40.setTextColor(not_selected)
        when(id)
        {
            18->if(!interpolat)button8.setTextColor(selected2)else button8.setTextColor(selected3)
            9->button9.setTextColor(selected)
            10->button10.setTextColor(selected)
            11->button11.setTextColor(selected)
            12->button30.setTextColor(selected)
            21->button31.setTextColor(selected)
            22->button32.setTextColor(selected)
            23->button33.setTextColor(selected)
            24->button37.setTextColor(selected)
            25->button38.setTextColor(selected)
            26->button39.setTextColor(selected)
            27->button40.setTextColor(selected)
            28->if(identifier!=0)button20.setTextColor(selected2)
            29->if(identifier!=0)button22.setTextColor(selected2)
        }
    }
    private fun reset()
    {
        passo=0.1
        inf=0.0
        sup=0.1
        identifier=0
        A=0.0
        T=0.0
        rit=0.0
        o=0.0
        D=0.0
        f1=0.0
        f2=0.0
        tau1=0.0
        tau2=0.0
        omega=0.0
        real=0.0
        imm=0.0
        identifier2=0
        A2=0.0
        T2=0.0
        rit2=0.0
        o2=0.0
        D2=0.0
        f1_2=0.0
        f2_2=0.0
        tau1_2=0.0
        tau2_2=0.0
        omega2=0.0
        real2=0.0
        imm2=0.0
        titolo=""
        titolo2=""
        textView30.text=""
        textView23.text=""
        button20.setTextColor(rgb(155,17,30))
        button22.setTextColor(rgb(155,17,30))
        button8.setTextColor(rgb(155,17,30))
        interpolat=false
        controllo=true
        interpolat2=false
        controllo2=true
        button8.text="Interpolat\nOff"
        editText4.setText("")
        editText5.setText("")
        editText6.setText("")
        editText7.setText("")
        editText8.setText("")
        editText9.setText("")
        editText10.setText("")
        turno=false
    }
    private fun input_output():Boolean
    {
        if(identifier==0)
        {
            textView30.text = "No function selected!"
            return true
        }
        else
        {
            if ((editText4.text.toString()!="")&&(editText4.text.toString()!=".")&&(editText4.text.toString()!="-"))
                A=editText4.text.toString().toDouble()
            if ((editText6.text.toString()!="")&&(editText6.text.toString()!=".")&&(editText6.text.toString()!="-"))
                T=editText6.text.toString().toDouble()
            if ((editText5.text.toString()!="")&&(editText5.text.toString()!=".")&&(editText5.text.toString()!="-"))
                rit = editText5.text.toString().toDouble()
            if ((editText11.text.toString()!="")&&(editText11.text.toString()!=".")&&(editText11.text.toString()!="-"))
                o=editText11.text.toString().toDouble()
            if ((editText12.text.toString()!="")&&(editText12.text.toString()!=".")&&(editText12.text.toString()!="-"))
                D=editText12.text.toString().toDouble()
            if ((editText10.text.toString()!="")&&(editText10.text.toString()!=".")&&(editText10.text.toString()!="-"))
                f1=editText10.text.toString().toDouble()
            if ((editText9.text.toString()!="")&&(editText9.text.toString()!=".")&&(editText9.text.toString()!="-"))
                f2 = editText9.text.toString().toDouble()
            if ((editText8.text.toString()!="")&&(editText8.text.toString()!=".")&&(editText8.text.toString()!="-"))
                tau1=editText8.text.toString().toDouble()
            if ((editText7.text.toString()!="")&&(editText7.text.toString()!=".")&&(editText7.text.toString()!="-"))
                tau2 = editText7.text.toString().toDouble()
            if ((editText13.text.toString()!="")&&(editText13.text.toString()!=".")&&(editText13.text.toString()!="-"))
                omega=editText13.text.toString().toDouble()
            if(identifier==24)
                real_imm()
            when(identifier)
            {
                9->{textView30.text="y(t)="+A+"*Π((t-("+rit+"))/"+T+")+("+o+")";titolo="Signal's Kind: Square wave"}
                10->{textView30.text="y(t)="+A+"*Λ((t-("+rit+"))/"+T+")+("+o+")";titolo="Signal's Kind: Triangular wave"}
                11->{textView30.text="y(t)="+A+"*sinc(2π*"+f1+"*(t-("+rit+")))+("+o+")";titolo="Signal's Kind: Cardinal Sinus"}
                12->{textView30.text="y(t)="+A+"*sinc(2π*"+f1+"*(t-("+rit+")))^2+("+o+")";titolo="Signal's Kind: Squared Cardinal Sinus"}
                21->{textView30.text="y(t)="+A+"*exp((-1/"+tau1+")*(t-("+rit+")))+("+o+")";titolo="Signal's Kind: First Order Evolution"}
                22->{textView30.text="y(t)=("+A+"/2)*(exp((-1/"+tau1+")*(t-("+rit+")))+exp((-1/"+tau2+")*(t-("+rit+"))))+("+o+")";titolo="Signal's Kind: Second Order Evolution"}
                23->{textView30.text="y(t)="+A+"*t*exp((-1/"+tau1+")*(t-("+rit+")))+("+o+")";titolo="Signal's Kind: Critical Evolution"}
                24->{textView30.text="y(t)="+A+"*exp("+real+"*(t-("+rit+")))*cos("+imm+"*(t-("+rit+")))+("+o+")";titolo="Signal's Kind: Harmonic Evolution"}
                25->{textView30.text="y(t)="+A+"*cos(2π*"+f1+"*(t-("+rit+")))*Π((t-("+rit+"))/"+T+")+("+o+")";titolo="Signal's Kind: Cosine Window Modulation"}
                26->{textView30.text="y(t)="+A+"*cos(2π*"+f1+"*(t-("+rit+")))*Λ((t-("+rit+"))/"+T+")+("+o+")";titolo="Signal's Kind: Cosine Modulated Triangularly"}
                27->{textView30.text="y(t)="+A+"*cos(2π*"+f1+"*(t-("+rit+")))*cos(2π*"+f2+"*(t-("+rit+"))/"+T+")+("+o+")";titolo="Signal's Kind: Cosine Harmonically Modulated"}
            }
            return false
        }
    }
    private fun input_output_bis():Boolean
    {
        if(identifier2==0)
        {
            textView23.text = "No function selected!"
            return true
        }
        else
        {
            if ((editText4.text.toString()!="")&&(editText4.text.toString()!=".")&&(editText4.text.toString()!="-"))
                A2=editText4.text.toString().toDouble()
            if ((editText6.text.toString()!="")&&(editText6.text.toString()!=".")&&(editText6.text.toString()!="-"))
                T2=editText6.text.toString().toDouble()
            if ((editText5.text.toString()!="")&&(editText5.text.toString()!=".")&&(editText5.text.toString()!="-"))
                rit2 = editText5.text.toString().toDouble()
            if ((editText11.text.toString()!="")&&(editText11.text.toString()!=".")&&(editText11.text.toString()!="-"))
                o2=editText11.text.toString().toDouble()
            if ((editText12.text.toString()!="")&&(editText12.text.toString()!=".")&&(editText12.text.toString()!="-"))
                D2=editText12.text.toString().toDouble()
            if ((editText10.text.toString()!="")&&(editText10.text.toString()!=".")&&(editText10.text.toString()!="-"))
                f1_2=editText10.text.toString().toDouble()
            if ((editText9.text.toString()!="")&&(editText9.text.toString()!=".")&&(editText9.text.toString()!="-"))
                f2_2 = editText9.text.toString().toDouble()
            if ((editText8.text.toString()!="")&&(editText8.text.toString()!=".")&&(editText8.text.toString()!="-"))
                tau1_2=editText8.text.toString().toDouble()
            if ((editText7.text.toString()!="")&&(editText7.text.toString()!=".")&&(editText7.text.toString()!="-"))
                tau2_2 = editText7.text.toString().toDouble()
            if ((editText13.text.toString()!="")&&(editText13.text.toString()!=".")&&(editText13.text.toString()!="-"))
                omega2=editText13.text.toString().toDouble()
            if(identifier2==24)
                real_imm()
            when(identifier2)
            {
                9->{textView23.text="y(t)="+A2+"*Π((t-("+rit2+"))/"+T2+")+("+o2+")";titolo2="Signal's Kind: Square wave"}
                10->{textView23.text="y(t)="+A2+"*Λ((t-("+rit2+"))/"+T2+")+("+o2+")";titolo2="Signal's Kind: Triangular wave"}
                11->{textView23.text="y(t)="+A2+"*sinc(t-("+rit2+"))+("+o2+")";titolo2="Signal's Kind: Cardinal Sinus"}
                12->{textView23.text="y(t)="+A2+"*sinc(t-("+rit2+"))^2+("+o2+")";titolo2="Signal's Kind: Squared Cardinal Sinus"}
                21->{textView23.text="y(t)="+A2+"*exp((-1/"+tau1_2+")*(t-("+rit2+")))+("+o2+")";titolo2="Signal's Kind: First Order Evolution"}
                22->{textView23.text="y(t)=("+A2+"/2)*(exp((-1/"+tau1_2+")*(t-("+rit2+")))+exp((-1/"+tau2_2+")*(t-("+rit2+"))))+("+o2+")";titolo2="Signal's Kind: Second Order Evolution"}
                23->{textView23.text="y(t)="+A2+"*t*exp((-1/"+tau1_2+")*(t-("+rit2+")))+("+o2+")";titolo2="Signal's Kind: Critical Evolution"}
                24->{textView23.text="y(t)="+A2+"*exp("+real2+"*(t-("+rit2+")))*cos("+imm2+"*(t-("+rit2+")))+("+o2+")";titolo2="Signal's Kind: Harmonic Evolution"}
                25->{textView23.text="y(t)="+A2+"*cos(2π*"+f1_2+"*(t-("+rit2+")))*Π((t-("+rit2+"))/"+T2+")+("+o2+")";titolo2="Signal's Kind: Cosine Window Modulation"}
                26->{textView23.text="y(t)="+A2+"*cos(2π*"+f1_2+"*(t-("+rit2+")))*Λ((t-("+rit2+"))/"+T2+")+("+o2+")";titolo2="Signal's Kind: Cosine Modulated Triangularly"}
                27->{textView23.text="y(t)="+A2+"*cos(2π*"+f1_2+"*(t-("+rit2+")))*cos(2π*"+f2_2+"*(t-("+rit2+")))+("+o2+")";titolo2="Signal's Kind: Cosine Harmonically Modulated"}

            }
            return false
        }
    }
    private fun real_imm()
    {
        if(D>1)
            D=1.0
        real=-1*omega*D
        imm=sqrt(1-D.pow(2))*omega
        real2=-1*omega2*D2
        imm2=sqrt(1-D2.pow(2))*omega2
    }
    private fun parametri_fondamentali()
    {
        if((identifier==9)||(identifier==25))
        {
            if(identifier==9)
                passo=0.1
            else
                passo=1/(1000*f1)
            inf=-1*2*T+rit
            sup=2*T+rit
        }
        else
        if((identifier==10)||(identifier==26))
        {
           if(identifier==10)
               passo=0.1
            else
               passo=1/(1000*f1)
            inf=-2*T+rit
            sup=2*T+rit
        }
        else
        if((identifier==11)||(identifier==12))
        {
            passo=0.01
            inf=-5/f1+rit
            sup=5/f1+rit
        }
        else
        if(identifier==27)
        {
            if(f1<f2)
            {
                passo=1/(1000*f2)
                inf = -5 / f1 + rit
                sup = 5 / f1 + rit
            }
            else
            {
                passo=1/(4*f1)
                inf = -5 / f2 + rit
                sup = 5 / f2 + rit
            }
        }
        else
        {
            passo=0.1
            inf=rit
            if(identifier==22)
            {
                if(tau1>tau2)
                    sup=4.5*tau1+rit
                else
                    sup=4.5*tau2+rit
            }
            else
                if(identifier==24)
                    sup=-4.5/real+rit
                else
                 sup=4.5*tau1+rit
        }
    }
}

