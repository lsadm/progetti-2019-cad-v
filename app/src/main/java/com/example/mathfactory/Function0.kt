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
import kotlinx.android.synthetic.main.activity_function0.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*
import android.media.MediaPlayer
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle

class Function0 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
    private var mediaplayer:MediaPlayer?=null
    var risultato="Warning: bad syntax!"
    var primo=""
    var secondo=""
    var generale=""
    val operazioni="+-x:"
    var controllo=true
    var controllo2=false
    var controllo3=true
    var controllo4=true
    var controllo5=false
    var controllo6=true
    var controllo7=true
    var controllo8=false
    var controllo9=true
    var controllo10=false
    var controllo11=true
    var controllo12=false
    var controllo13=false
    var controllo14=true
    var controllo15=true
    var controllo16=true
    var controllo17=false
    var controllo18=false
    var controllo19=true
    var controllo20=true
    var controllo21=true
    var counter=0
    var posizione=0
    var posizione2=0
    var identificatore=0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function0)
        nav_view0.setNavigationItemSelectedListener(this)
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        b0.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;controllore(identificatore);counter++;controllo15=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="0";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;if(controllo7)controllo6=false;controllo8=true} }
        b1.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="1";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b2.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="2";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b3.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="3";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b4.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="4";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b5.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="5";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b6.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="6";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b7.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="7";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b8.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="8";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        b9.setOnClickListener { if((controllo)&&(controllo6)&&(controllo9)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;if(controllo14)controllo13=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="9";val s3=s1+s2;textView2.text=s3;if(controllo3)controllo2=true;controllo4=false;controllo5=true;controllo7=false;controllo8=true} }
        punto.setOnClickListener { if((controllo)&&(controllo2)&&(counter<11)&&(!controllo17)){controllo19=false;counter++;posizione=counter;controllo15=true;val s1=textView2.text.toString();val s2=".";val s3=s1+s2;textView2.text=s3;controllo2=false;controllo3=false;controllo4=false;controllo6=true;controllo7=false;controllo8=false} }
        meno.setOnClickListener { if((controllo)&&(controllo4)&&(counter<11)&&(!controllo17)){if(controllo20){textView2.text="";controllo20=false;textView2.setTextColor(Color.BLUE)};controllo19=false;counter++;controllo15=true;textView2.textSize=24.toFloat();val s1=textView2.text.toString();val s2="-";val s3=s1+s2;textView2.text=s3;controllo4=false} }
        delate.setOnClickListener {controllo21=true;controllo20=true;controllo12=false;controllo19=true;controllo18=false;controllo17=false;controllo16=true;textView2.setTextColor(Color.BLUE);posizione=0;counter=0;controllo15=true;colore_bottoni(0);risultato="Warning: bad syntax!"; textView2.text="";controllo=true;controllo2=false;controllo3=true;controllo4=true;controllo5=false;controllo6=true;controllo7=true;controllo8=false;controllo9=true;controllo11=true;controllo13=false;controllo14=true }
        uguale.setOnClickListener {if(!controllo17){ controllo13=false;controllo2=false;controllore2();if(controllo16)textView2.setTextColor(Color.RED);colore_bottoni(0);controllo15=false;textView2.textSize=24.toFloat();if(controllo10){controllo=true;controllo5=true;controllo8=true;controllo16=false;risultato=elementare(identificatore);if(controllo16)textView2.setTextColor(Color.RED);controllore2()};controllo12=true;textView2.text=risultato;identificatore=0;primo="";secondo="";controllo10=false}}
        button18.setOnClickListener { if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(1);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text="|"+generale+"|";val risposta=modulo(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false;controllo11=false}}else {controllo18=true;help(1);colore_bottoni(1)}}
        button19.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(2);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text="φ("+generale+")";val risposta=fase(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false;controllo11=false}}else {controllo18=true;help(2);colore_bottoni(2)}}
        button6.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5&&(controllo8)&&(controllo21))){colore_bottoni(3);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text=generale+"^2";val risposta=potenza(generale.toDouble());risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else {controllo18=true;help(3);colore_bottoni(3)}}
        button.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(4);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="√"+generale;val risposta=radice(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else {controllo18=true;help(4);colore_bottoni(4)}}
        button11.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false; if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(5);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text="ctg("+generale+")";val risposta=cotangente(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else {controllo18=true;help(5);colore_bottoni(5)}}
        button10.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false; if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(6);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="tg("+generale+")";val risposta=tangente(generale.toDouble());risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else {controllo18=true;help(6);colore_bottoni(6)}}
        button9.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(7);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="cos("+generale+")";val risposta=coseno(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false ;controllo11=false}}else {controllo18=true;help(7);colore_bottoni(7)}}
        button8.setOnClickListener {if(!controllo17) {if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(8);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="sen("+generale+")";val risposta=seno(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else {controllo18=true;help(8);colore_bottoni(8)}}
        button15.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(9);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text="log("+generale+")";val risposta=logaritmo_base_10(generale.toDouble());risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else{controllo18=true; help(9);colore_bottoni(9)}}
        button12.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(10);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="10^("+generale+")";val risposta=esponenziale_base_10(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false;controllo11=false }}else{controllo18=true; help(10);colore_bottoni(10)}}
        button14.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(11);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="ln("+generale+")";val risposta=logaritmo_neperiano(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false ;controllo11=false}}else {controllo18=true;help(11);colore_bottoni(11)}}
        button13.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){ colore_bottoni(12);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="e^("+generale+")";val risposta=esponenziale_neperiano(generale.toDouble());risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else{controllo18=true; help(12);colore_bottoni(12)}}
        button2.setOnClickListener { if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){posizione=0;counter=0;colore_bottoni(13);controllo15=false;textView2.textSize=24.toFloat();if((controllo11)||(controllo12))primo=textView2.text.toString()else primo=generale;controllo12=false;textView2.text=primo+"+";generale=primo;controllo10=true;controllo9=true;controllo6=true;controllo7=true;controllo4=true;controllo11=false;controllo2=false;controllo3=true;controllo13=false;controllo14=true;identificatore=1;controllore(identificatore)}}else {controllo18=true;help(13);colore_bottoni(13)}}
        button3.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false; if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){posizione=0;counter=0;colore_bottoni(14);controllo15=false;textView2.textSize=24.toFloat();if((controllo11)||(controllo12))primo=textView2.text.toString()else primo=generale;controllo12=false; textView2.text=primo+"-";generale=primo;controllo10=true;controllo9=true;controllo6=true;controllo7=true;controllo4=true;controllo11=false;controllo2=false;controllo3=true;controllo13=false;controllo14=true;identificatore=2;controllore(identificatore)}}else{controllo18=true; help(14);colore_bottoni(14)}}
        button4.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false; if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){posizione=0;counter=0;colore_bottoni(15);controllo15=false;textView2.textSize=24.toFloat();if((controllo11)||(controllo12))primo=textView2.text.toString()else primo=generale;controllo12=false; textView2.text=primo+"x";generale=primo;controllo10=true;controllo9=true;controllo6=true;controllo7=true;controllo4=true;controllo11=false;controllo2=false;controllo3=true;controllo13=false;controllo14=true;identificatore=3;controllore(identificatore)}}else {controllo18=true;help(15);colore_bottoni(15)}}
        button5.setOnClickListener {if(!controllo17) {if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){posizione=0;counter=0;colore_bottoni(16);controllo15=false;textView2.textSize=24.toFloat();if((controllo11)||(controllo12))primo=textView2.text.toString()else primo=generale;controllo12=false; textView2.text=primo+":";generale=primo;controllo10=true;controllo9=true;controllo6=true;controllo7=true;controllo4=true;controllo11=false;controllo2=false;controllo3=true;controllo13=false;controllo14=true;identificatore=4;controllore(identificatore)}}else {controllo18=true;help(16);colore_bottoni(16)}}
        button30.setOnClickListener {if(!controllo17) {if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(17);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text="arcsen("+generale+")";val risposta=arcoseno(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(17);colore_bottoni(17)}}
        button31.setOnClickListener {if(!controllo17) {if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(18);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="arccos("+generale+")";val risposta=arcocoseno(generale.toDouble());risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else {controllo18=true;help(18);colore_bottoni(18)}}
        button32.setOnClickListener {if(!controllo17) {if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(19);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="arctg("+generale+")";val risposta=arcotangente(generale.toDouble());risultato=risposta.toString();controllo9=false;controllo10=false ;controllo11=false}}else {controllo18=true;help(19);colore_bottoni(19)}}
        button33.setOnClickListener {if(!controllo17) {if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(20);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString(); controllo12=false;textView2.text="arcctg("+generale+")";val risposta=arcocotangente(generale.toDouble());risultato=risposta.toString();controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(20);colore_bottoni(20)}}
        button34.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false; if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(21);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString(); controllo12=false;textView2.text="1/"+generale;val risposta=reciproco(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else {controllo18=true; help(21);colore_bottoni(21)}}
        button35.setOnClickListener { controllo16=false;if((controllo)&&(controllo13)&&(counter<11)&&(!controllo17)){controllo19=false;counter++;posizione2=counter;controllo15=true;val s1=textView2.text.toString();val s2="E";val s3=s1+s2;textView2.text=s3;controllo13=false;controllo3=false;controllo2=false;controllo4=true;controllo6=true;controllo7=true;controllo8=false;controllo14=false} }
        button36.setOnClickListener {if(!controllo17){ controllo16=false;if((controllo15)&&(counter>0)){cancella();counter--;controllore(identificatore)}}}
        button7.setOnClickListener {if(!controllo17){ if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)};controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){controllo21=false;colore_bottoni(22);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text=generale;val risposta=percentuale(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true;  help(22);colore_bottoni(22)}}
        button40.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(26);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text="ctgh("+generale+")";val risposta=cotangente_iperbolica(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(26);colore_bottoni(26)}}
        button39.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(25);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="tgh("+generale+")";val risposta=tangente_iperbolica(generale.toDouble());risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else{controllo18=true; help(25);colore_bottoni(25)}}
        button38.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(24);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="cosh("+generale+")";val risposta=coseno_iperbolico(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false ;controllo11=false}}else {controllo18=true;help(24);colore_bottoni(24)}}
        button37.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(23);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="senh("+generale+")";val risposta=seno_iperbolico(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(23);colore_bottoni(23)}}
        button41.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){controllo21=false;colore_bottoni(27);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text=generale;val risposta=radianti(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(27);colore_bottoni(27)}}
        button42.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){controllo21=false;colore_bottoni(28);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString(); controllo12=false;textView2.text=generale;val risposta=convertitore(generale.toDouble(),2);risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else {controllo18=true;help(28);colore_bottoni(28)}}
        button43.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){controllo21=false;colore_bottoni(29);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString(); controllo12=false;textView2.text=generale;val risposta=convertitore(generale.toDouble(),8);risultato=risposta;controllo9=false;controllo10=false ;controllo11=false}}else {controllo18=true;help(29);colore_bottoni(29)}}
        button44.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){controllo21=false;colore_bottoni(30);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString(); controllo12=false;textView2.text=generale;val risposta=convertitore(generale.toDouble(),16);risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(30);colore_bottoni(30)}}
        button16.setOnClickListener {if(controllo19){ textView2.text="Press a fun_button to have help!";textView2.textSize=24.toFloat();colore_bottoni(31);controllo17=true;}}
        button45.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(32);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false;textView2.text="settsenh("+generale+")";val risposta=settoreseno_iperbolico(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(27);colore_bottoni(27)}}
        button46.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(33);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="settcosh("+generale+")";val risposta=settorecoseno_iperbolico(generale.toDouble());risultato=risposta;controllo9=false ;controllo10=false;controllo11=false}}else {controllo18=true;help(28);colore_bottoni(28)}}
        button47.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(34);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString();controllo12=false; textView2.text="setttgh("+generale+")";val risposta=settoretangente_iperbolica(generale.toDouble());risultato=risposta;controllo9=false;controllo10=false ;controllo11=false}}else {controllo18=true;help(29);colore_bottoni(29)}}
        button48.setOnClickListener {if(!controllo17){if(textView2.text==""){textView2.text="Warning: missing operand!";textView2.setTextColor(Color.RED)}; controllo16=false;if((controllo)&&(controllo5)&&(controllo8)&&(controllo21)){colore_bottoni(35);controllo15=false;if((controllo11)||(controllo12))generale=textView2.text.toString(); controllo12=false;textView2.text="settctgh("+generale+")";val risposta=settorecotangente_iperbolica(generale.toDouble());risultato=risposta ;controllo9=false;controllo10=false;controllo11=false}}else{controllo18=true; help(30);colore_bottoni(30)}}
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
            Toast.makeText(this,"Hi, I'm your own\ncalculator!", Toast.LENGTH_LONG).show()
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
        if (id == R.id.note_testuali) {
            val next = Intent(this, Function8::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_immagini) {
            val next = Intent(this, Function9::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_audio) {
            val next = Intent(this, Function10::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.Profile) {
            val next = Intent(this, Profilo_Utente::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.calculator) {
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
            mediaplayer?.start()
            return true
        }
        return true
    }
    private fun controllore2()
    {
        if((risultato=="Error: Overflow!")||(risultato=="Operation not defined!")||(risultato=="Warning: bad operand!")||(risultato=="Operation Indeterminate!")||(risultato=="Warning: bad syntax!"))
            controllo=false
    }
    private fun help(id:Int)
    {
        when (id)
        {
            1->textView2.text="Absolute Value of x!"
            2->textView2.text="Fase of x!"
            3->textView2.text="X squared up!"
            4->textView2.text="Square root of x!"
            5->textView2.text="Cotangent of x!"
            6->textView2.text="Tangent of x!"
            7->textView2.text="Cosine of x!"
            8->textView2.text="Sine of x!"
            9->textView2.text="Logarithm in base 10 of x!"
            10->textView2.text="10 raised to x!"
            11->textView2.text="Neperian logarithm of x!"
            12->textView2.text="Neperian number raised to x!"
            13->textView2.text="Addition!"
            14->textView2.text="Subtraction!"
            15->textView2.text="Multiplication!"
            16->textView2.text="Division!"
            17->textView2.text="Arc sine of x!"
            18->textView2.text="Arc cosine of x!"
            19->textView2.text="Arc tangent of x!"
            20->textView2.text="Arc cotangent of x!"
            21->textView2.text="Reciprocal of x!"
            22->textView2.text="Percentage!"
            23->textView2.text="Hyperbolic sine of x!"
            24->textView2.text="Hyperbolic cosine of x!"
            25->textView2.text="Hyperbolic tangent of x!"
            26->textView2.text="Hyperbolic cotangent of x!"
            27->textView2.text="Conversion to radians!"
            28->textView2.text="Binary conversion!"
            29->textView2.text="Conversion into octal!"
            30->textView2.text="Conversion into hexadecimal!"
            32->textView2.text="Arc Hyperbolic sine of x!"
            33->textView2.text="Arc Hyperbolic cosine of x!"
            34->textView2.text="Arc Hyperbolic tangent of x!"
            35->textView2.text="Arc Hyperbolic cotangent of x!"
        }
    }
    private fun controllore(id:Int)
    {
        if(id==0)
        {
            if ((counter == 1) && (textView2.text == "0")) {
                controllo6 = false
                controllo2 = true
                controllo13 = false
            }
            if ((counter <= posizione - 1) && (textView2.text != "")) {
                controllo3 = true
                controllo2 = true
            }
            if (textView2.text == "") {
                controllo5=false
                controllo4 = true
                controllo3 = true
                controllo2 = false
                controllo6 = true
                controllo13=false;
            }
            if((counter<=posizione2-1)&&(textView2.text!="")&&(textView2.text!="0"))
                controllo13=true
        }
        else
        {
            if ((counter == 1) && (textView2.text == primo+operazioni[id-1]+"0"))
            {
                controllo6 = false
                controllo2 = true
                controllo13 = false
            }
            if ((counter <= posizione - 1) && (textView2.text != primo+operazioni[id-1]))
            {
                controllo3 = true
                controllo2 = true
            }
            if (textView2.text == primo+operazioni[id-1])
            {
                controllo4 = true
                controllo3 = true
                controllo2 = false
                controllo6 = true
                controllo13=false;
            }
            if((counter<=posizione2-1)&&(textView2.text!=primo+operazioni[id-1])&&(textView2.text!=primo+operazioni[id-1]+"0"))
                controllo13=true
        }
    }
    private fun cancella()
    {
        var new=""
        var cont=0
        while(cont<textView2.length()-1)
        {
            new = new + textView2.text[cont]
            cont++
        }
        textView2.text=new
    }
    private fun estrai(i:Int)
    {
        var cont=0
        while(textView2.text[cont]!=operazioni[i-1])
            cont++
        val lunghezza=textView2.length()
        var cont3=cont+1
        while(cont3<lunghezza)
        {
            secondo = secondo+textView2.text[cont3]
            cont3++
        }
    }
    private fun elementare(id:Int):String {
        var ris: String
        estrai(id)
        if((primo.toDouble()==0.toDouble())&&(secondo.toDouble()!=0.toDouble())&&(id==4))
            ris="0.0"
        else
            if ((secondo == "") || (secondo == "")) {
                ris = "Warning: bad syntax!"
                controllo16=true;
            } else
                if ((secondo.toDouble() == 0.toDouble()) && (primo.toDouble() == 0.toDouble()) && (id == 4)){
                    ris = "Operation Indeterminate!"
                    controllo16=true;
                }
                else
                    if((secondo.toDouble()==0.toDouble())&&(primo.toDouble()!=0.toDouble())&&(id==4)){
                        controllo16=true;
                        ris="Operation not defined!"}
                    else
                    {
                        when (id)
                        {
                            1 -> ris = somma(primo.toDouble(), secondo.toDouble()).toString()
                            2 -> ris = differenza(primo.toDouble(), secondo.toDouble()).toString()
                            3 -> ris = moltiplicazione(primo.toDouble(), secondo.toDouble()).toString()
                            4 -> ris = divisione(primo.toDouble(), secondo.toDouble()).toString()
                            else -> ris = ""
                        }
                    }
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun colore_bottoni(id:Int)
    {
        val colorValue1=rgb(81,153,167)
        val colorValue2=rgb(255,215,0)
        val colorValue3=rgb(40,114,51)
        val colorValue4=rgb(155,17,30)
        button18.setTextColor(colorValue1)
        button19.setTextColor(colorValue1)
        button6.setTextColor(colorValue1)
        button.setTextColor(colorValue1)
        button11.setTextColor(colorValue1)
        button10.setTextColor(colorValue1)
        button9.setTextColor(colorValue1)
        button8.setTextColor(colorValue1)
        button15.setTextColor(colorValue1)
        button12.setTextColor(colorValue1)
        button14.setTextColor(colorValue1)
        button13.setTextColor(colorValue1)
        button2.setTextColor(colorValue1)
        button3.setTextColor(colorValue1)
        button4.setTextColor(colorValue1)
        button5.setTextColor(colorValue1)
        button30.setTextColor(colorValue1)
        button31.setTextColor(colorValue1)
        button32.setTextColor(colorValue1)
        button33.setTextColor(colorValue1)
        button34.setTextColor(colorValue1)
        button7.setTextColor(colorValue1)
        button37.setTextColor(colorValue1)
        button38.setTextColor(colorValue1)
        button39.setTextColor(colorValue1)
        button40.setTextColor(colorValue1)
        button41.setTextColor(colorValue1)
        button42.setTextColor(colorValue1)
        button43.setTextColor(colorValue1)
        button44.setTextColor(colorValue1)
        button45.setTextColor(colorValue1)
        button46.setTextColor(colorValue1)
        button47.setTextColor(colorValue1)
        button48.setTextColor(colorValue1)
        if(controllo18)
            button16.setTextColor(colorValue3)
        else
            button16.setTextColor(colorValue4)
        when (id)
        {
            1->button18.setTextColor(colorValue2)
            2->button19.setTextColor(colorValue2)
            3->button6.setTextColor(colorValue2)
            4->button.setTextColor(colorValue2)
            5->button11.setTextColor(colorValue2)
            6->button10.setTextColor(colorValue2)
            7->button9.setTextColor(colorValue2)
            8->button8.setTextColor(colorValue2)
            9->button15.setTextColor(colorValue2)
            10->button12.setTextColor(colorValue2)
            11->button14.setTextColor(colorValue2)
            12->button13.setTextColor(colorValue2)
            13->button2.setTextColor(colorValue2)
            14->button3.setTextColor(colorValue2)
            15->button4.setTextColor(colorValue2)
            16->button5.setTextColor(colorValue2)
            17->button30.setTextColor(colorValue2)
            18->button31.setTextColor(colorValue2)
            19->button32.setTextColor(colorValue2)
            20->button33.setTextColor(colorValue2)
            21->button34.setTextColor(colorValue2)
            22->button7.setTextColor(colorValue2)
            23->button37.setTextColor(colorValue2)
            24->button38.setTextColor(colorValue2)
            25->button39.setTextColor(colorValue2)
            26->button40.setTextColor(colorValue2)
            27->button41.setTextColor(colorValue2)
            28->button42.setTextColor(colorValue2)
            29->button43.setTextColor(colorValue2)
            30->button44.setTextColor(colorValue2)
            31->button16.setTextColor(colorValue3)
            32->button45.setTextColor(colorValue2)
            33->button46.setTextColor(colorValue2)
            34->button47.setTextColor(colorValue2)
            35->button48.setTextColor(colorValue2)
        }
    }
    private fun convertitore(operando:Double,base:Int):String {
        val simboli = "ABCDEF"
        var ris = ""
        var convertito = ""
        var resto: Int
        var cont=0
        var lunghezza: Int
        if ((operando == ceil(operando))&&(operando>=0))
        {
            var numero = operando.toLong()
            if ((((operando <= 8388607)&&(base==2))||((operando <= 9223372036854775807)&&(base!=2)))&&(operando>0))
            {
                while (numero > 0)
                {
                    resto = (numero % base).toInt()
                    if (resto > 9)
                        convertito = convertito + simboli[resto - 10]
                    else
                        convertito = convertito + resto.toString()
                    numero = numero / base
                }
                lunghezza = convertito.length
                while (cont < lunghezza)
                {
                    ris = ris + convertito[lunghezza - 1 - cont]
                    cont++
                }
            }
            else
                if(numero==0.toLong())
                    ris="0"
                else
                {
                    controllo16=true
                    ris = "Error: Overflow!"
                }
        }
        else
        {
            controllo16=true
            ris = "Warning: bad operand!"
        }
        return ris
    }
    private fun radianti(operando:Double):String
    {
        var ris:String
        ris=(operando* PI /180.toDouble()).toString()
        if(ris=="Infinity") {
            controllo16 = true;
            ris = "Error: Overflow!"
        }
        return ris
    }
    private fun percentuale(operando:Double):String
    {
        var ris:String
        ris=(operando*100.toDouble()).toString()
        if(ris=="Infinity") {
            controllo16 = true;
            ris = "Error: Overflow!"
        }
        else
            ris=ris+"%"
        return ris
    }
    private fun reciproco(operando:Double):String
    {
        var ris:String
        if(operando!=0.toDouble())
            ris=(1/operando).toString()
        else{
            controllo16=true;
            ris="Operation not defined!"}
        return ris
    }
    private fun somma(operando1:Double,operando2:Double):Double
    {
        var ris:Double
        ris=operando1+operando2
        return ris
    }
    private fun differenza(operando1:Double,operando2:Double):Double
    {
        val ris:Double
        ris=operando1-operando2
        return ris
    }
    private fun moltiplicazione(operando1:Double,operando2:Double):Double
    {
        val ris:Double
        ris=operando1*operando2
        return ris
    }
    private fun divisione(operando1:Double,operando2:Double):Double
    {
        val ris:Double
        ris=operando1/operando2
        return ris
    }
    private fun potenza(operando:Double):String
    {
        var ris:String
        ris=(operando*operando).toString()
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun radice(operando:Double):String
    {
        var ris:String
        if(operando>=0)
        {
            ris = sqrt(operando).toString()
            if (ris == "Infinity"){
                controllo16=true;
                ris = "Error: Overflow!"}
        }
        else{
            controllo16=true;
            ris="Operation not defined!"}
        return ris
    }
    private fun modulo(operando:Double):String
    {
        var ris:String
        if(operando>=0)
            ris=operando.toString()
        else
            ris=(-1*operando).toString()
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun fase(operando:Double):String
    {
        val ris:String
        if(operando>0)
            ris=(0.toDouble()).toString()
        else
            if(operando<0)
                ris=(180.toDouble()).toString()
            else
            {
                controllo16=true
                ris = "Operation Indeterminate!"
            }
        return ris
    }
    private fun seno(operando:Double):String
    {
        var ris:String
        var argomento=(operando* PI)/180.toDouble()
        ris=(sin(argomento)).toString()
        if(ris=="NaN"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun coseno(operando:Double):String
    {
        var ris:String
        var argomento=(operando* PI)/180.toDouble()
        ris=(cos(argomento)).toString()
        if(ris=="NaN"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun tangente(operando:Double):String
    {
        var ris:String
        if((operando/180- ceil(operando/180) !=-0.5)&&(operando/180- ceil(operando/180) !=0.0))
        {
            var argomento = (operando * PI) / 180.toDouble()
            ris = tan(argomento).toString()
        }
        else
            if(operando/180- ceil(operando/180) ==-0.5){
                controllo16=true;
                ris="Operation not defined!"}
            else
                ris="0.0"
        if(ris=="NaN"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun cotangente(operando:Double):String
    {
        var ris:String
        if((operando/180- ceil(operando/180) !=0.0)&&(operando/180- ceil(operando/180) !=-0.5))
        {
            var argomento = (operando * PI) / 180.toDouble()
            ris =(1.toDouble()/ tan(argomento)).toString()
        }
        else
            if(operando/180- ceil(operando/180) ==0.0){
                controllo16=true;
                ris="Operation not defined!"}
            else
                ris="0.0"
        if(ris=="NaN"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun esponenziale_neperiano(operando:Double):String
    {
        var ris:String
        ris=(exp(operando)).toString()
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun logaritmo_neperiano(operando:Double):String
    {
        var ris:String
        if(operando>0)
            ris= ln(operando).toString()
        else{
            controllo16=true;
            ris="Operation not defined!"}
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun esponenziale_base_10(operando:Double):String
    {
        var ris:String
        ris=(10.toDouble().pow(operando)).toString()
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun logaritmo_base_10(operando:Double):String
    {
        var ris:String
        if(operando>0)
            ris= log10(operando).toString()
        else{
            controllo16=true;
            ris="Operation not defined!"}
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun arcotangente(operando:Double):Double
    {
        val ris:Double
        ris= atan(operando)
        val risdec=(ris* 180.toDouble())/ PI
        return risdec
    }
    private fun arcocotangente(operando:Double):Double
    {
        val ris:Double
        ris= PI /2- atan(operando)
        val risdec=(ris* 180.toDouble())/ PI
        return risdec
    }
    private fun arcocoseno(operando:Double):String
    {
        val ris:Double
        var risdec:String
        if((operando<-1)||(operando>1)){
            controllo16=true;
            risdec="Operation not defined!"}
        else
        {
            ris = acos(operando)
            risdec = ((ris * 180.toDouble()) / PI).toString()
        }
        return risdec
    }
    private fun arcoseno(operando:Double):String
    {
        val ris:Double
        var risdec:String
        if((operando<-1)||(operando>1)){
            controllo16=true;
            risdec="Operation not defined!"}
        else
        {
            ris = asin(operando)
            risdec = ((ris * 180.toDouble()) / PI).toString()
        }
        return risdec
    }
    private fun seno_iperbolico(operando:Double):String
    {
        var ris:String
        var argomento=(operando* PI)/180.toDouble()
        ris=(sinh(argomento)).toString()
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun coseno_iperbolico(operando:Double):String
    {
        var ris:String
        var argomento=(operando* PI)/180.toDouble()
        ris=(cosh(argomento)).toString()
        if(ris=="Infinity"){
            controllo16=true;
            ris="Error: Overflow!"}
        return ris
    }
    private fun tangente_iperbolica(operando:Double):String
    {
        var ris:String
        var argomento = (operando * PI) / 180.toDouble()
        ris = tanh(argomento).toString()
        return ris
    }
    private fun cotangente_iperbolica(operando:Double):String
    {
        var ris:String
        if(operando!=0.toDouble())
        {
            var argomento = (operando * PI) / 180.toDouble()
            ris =(1.toDouble()/ tanh(argomento)).toString()
        }
        else
        {
            controllo16=true;
            ris="Operation not defined!"
        }
        return ris
    }
    private fun settorecotangente_iperbolica(operando:Double):String
    {
        val ris:Double
        var risdec:String
        if((operando>=-1)&&(operando<=1))
        {
            controllo16=true;
            risdec="Operation not defined!"
        }
        else
        {
            ris = 0.5 * ln((operando + 1) / (operando - 1))
            if(ris.toString()=="NaN")
            {
                controllo16=true;
                risdec="Error: Overflow!"
            }
            else
                risdec = ((ris * 180.toDouble()) / PI).toString()
        }
        return risdec
    }
    private fun settoretangente_iperbolica(operando:Double):String
    {
        val ris:Double
        var risdec:String
        if((operando>=1)||(operando<=-1))
        {
            controllo16=true;
            risdec="Operation not defined!"
        }
        else
        {
            ris = atanh(operando)
            risdec = ((ris * 180.toDouble()) / PI).toString()
        }
        return risdec
    }
    private fun settorecoseno_iperbolico(operando:Double):String
    {
        val ris:Double
        var risdec:String
        if(operando<1)
        {
            controllo16=true;
            risdec="Operation not defined!"}
        else
        {
            ris = acosh(operando)
            if(ris.toString()=="Infinity")
            {
                controllo16=true
                risdec="Error: Overflow!"
            }
            else
                risdec = ((ris * 180.toDouble()) / PI).toString()
        }
        return risdec
    }
    private fun settoreseno_iperbolico(operando:Double):String
    {
        val ris:Double
        var risdec:String
        ris = asinh(operando)
        if((ris.toString()=="Infinity")||(ris.toString()=="-Infinity"))
        {
            controllo16=true
            risdec="Error: Overflow!"
        }
        else
            risdec = ((ris * 180.toDouble()) / PI).toString()
        return risdec
    }
}
private operator fun String.set(length: Int, value: Char) {

}