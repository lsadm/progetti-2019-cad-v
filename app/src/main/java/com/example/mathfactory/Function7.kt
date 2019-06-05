package com.example.mathfactory
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function7.*
import kotlin.math.*
class Function7 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val PERMISSION_REQUEST_CODE=0
    private var mediaplayer: MediaPlayer?=null
    var passo:Double=0.1
    var inf:Double=0.0
    var sup:Double=0.1
    var A:Double=0.0
    var T:Double=0.1
    var rit:Double=0.0
    var o:Double=0.0
    var D:Double=0.1
    var f1:Double=100.0
    var f2:Double=100.0
    var tau1:Double=0.1
    var tau2:Double=0.1
    var omega:Double=100.0
    var real:Double=-1.0
    var imm:Double=0.001
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
    var real2:Double=-1.0
    var imm2:Double=0.0
    var titolo2=""
    var identifier:Int=0
    var turno=false
    var interpolat=false
    var interpolat2=false
    var identifier2:Int=0
    var controllo=true
    var controllo2=true
    var fv=0.0
    var fv2=0.0
    var Tv=0.0
    var ritv=0.0
    var tauv=0.0
    var tauv2=0.0
    var realv=0.0
    var immv=0.0
    var Id_Utente:String?=null
    var controllo_barra=false
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function7)
        nav_view7.setNavigationItemSelectedListener(this)
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                if(valore=="visible")
                    progress_access.visibility= View.VISIBLE
                else
                    if(valore=="invisible")
                        progress_access.visibility= View.INVISIBLE
            }
        }
        class ProgressBarThread constructor(val handler: Handler):Thread()
        {
            override fun run()
            {
                while(true)
                {
                    while (!controllo_barra)
                    {
                        sleep(500)
                    }
                    notify_message("visible")
                    while (controllo_barra)
                    {
                        while (progress_access.progress < 100)
                        {
                            progress_access.incrementProgressBy(1)
                            sleep(10)
                        }
                        progress_access.progress = 0
                    }
                    notify_message("invisible")
                }
            }
            fun notify_message(valore:String)
            {
                val messaggio=handler.obtainMessage()
                val bundle=Bundle()
                bundle.putString("reflesh",""+valore)
                messaggio.setData(bundle)
                handler.sendMessage(messaggio)
            }
        }
        val myHandler=MyHandler()
        val progressBarThread=ProgressBarThread(myHandler)
        progressBarThread.start()
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        controllo_generale=2
        button8.setOnClickListener {set_color(18);if(!interpolat){interpolat=true;interpolat2=true;button8.text="Interpolat\nOn"}else{interpolat=false;interpolat2=false;button8.text="Interpolat\nOff"}}
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
        button20.setOnClickListener {if(!turno){set_color(28);if(input_output())textView30.setTextColor(Color.RED)else textView30.setTextColor(Color.BLUE);if(identifier!=0)turno=true}}
        button22.setOnClickListener {if(turno){set_color(29);if(input_output_bis())textView23.setTextColor(Color.RED)else textView23.setTextColor(Color.BLUE);if(identifier2!=0)turno=false} }
        button17.setOnClickListener {controllo_barra=true;parametri_fondamentali();val next= Intent(this,Grafici::class.java);next.putExtra("Id_Utente",Id_Utente);next.putExtra("passo", passo);next.putExtra("inf", inf);next.putExtra("sup", sup);next.putExtra("identifier", identifier);next.putExtra("A", A);next.putExtra("T", T);next.putExtra("rit", rit);next.putExtra("o", o);next.putExtra("real", real);next.putExtra("f1", f1);next.putExtra("f2", f2);next.putExtra("tau1", tau1);next.putExtra("tau2", tau2);next.putExtra("imm", imm);next.putExtra("titolo",titolo);next.putExtra("interpolat",interpolat);next.putExtra("controllo",controllo);next.putExtra("identifier2", identifier2);next.putExtra("A2", A2);next.putExtra("T2", T2);next.putExtra("rit2", rit2);next.putExtra("o2", o2);next.putExtra("real2", real2);next.putExtra("f1_2", f1_2);next.putExtra("f2_2", f2_2);next.putExtra("tau1_2", tau1_2);next.putExtra("tau2_2", tau2_2);next.putExtra("imm2", imm2);next.putExtra("titolo2",titolo2);next.putExtra("interpolat2",interpolat2);next.putExtra("controllo2",controllo2);reset();startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_graph_sound);mediaplayer?.start();controllo_barra=false}
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
            Toast.makeText(this,"Hi, do you want to see any\nsignal functions?", Toast.LENGTH_LONG).show()
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
            next.putExtra("Id_Utente",Id_Utente)
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
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_five) {
            val next = Intent(this, Function2::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_six) {
            val next = Intent(this, Function3::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_seven) {
            val next = Intent(this, Function4::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_eight) {
            val next = Intent(this, Function5::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_nine) {
            val next = Intent(this, Function6::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_ten) {
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_testuali) {
            if(checkPermission())
            {
                val next = Intent(this, Function8::class.java)
                next.putExtra("Id_Utente",Id_Utente)
                startActivity(next)
                mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                mediaplayer?.start()
            }
            else
                requestPermission()
            return true
        }
        if (id == R.id.note_immagini) {
            if(checkPermission())
            {
                val next = Intent(this, Function9::class.java)
                next.putExtra("Id_Utente",Id_Utente)
                startActivity(next)
                mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                mediaplayer?.start()
            }
            else
                requestPermission()
            return true
        }
        if (id == R.id.note_audio) {
            if(checkPermission())
            {
                val next = Intent(this, Function10::class.java)
                next.putExtra("Id_Utente",Id_Utente)
                startActivity(next)
                mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                mediaplayer?.start()
            }
            else
                requestPermission()
            return true
        }
        if (id == R.id.Profile) {
            val next = Intent(this, Profilo_Utente::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            return true
        }
        return true
    }
    private fun set_color(id:Int) {
        val not_selected = rgb(81, 153, 167)
        val selected = rgb(255, 215, 0)
        val selected2 = rgb(40, 114, 51)
        val selected3 = (rgb(155, 17, 30))
        if(id!=18)
        {
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
        when (id) {
            9 -> button9.setTextColor(selected)
            10 -> button10.setTextColor(selected)
            11 -> button11.setTextColor(selected)
            12 -> button30.setTextColor(selected)
            21 -> button31.setTextColor(selected)
            22 -> button32.setTextColor(selected)
            23 -> button33.setTextColor(selected)
            24 -> button37.setTextColor(selected)
            25 -> button38.setTextColor(selected)
            26 -> button39.setTextColor(selected)
            27 -> button40.setTextColor(selected)
            28 -> if (identifier != 0) button20.setTextColor(selected2)
            29 -> if (identifier != 0) button22.setTextColor(selected2)
        }
    }
        else
        if (!interpolat)
            button8.setTextColor(selected2)
        else
            button8.setTextColor(selected3)
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
        valutazione()
        if((identifier==9)||(identifier==25))
        {
            if(identifier==9)
                passo=0.1
            else
                passo=1/(1000*fv)
            inf=-1*2*Tv+ritv
            sup=2*Tv+ritv
        }
        else
        if((identifier==10)||(identifier==26))
        {
           if(identifier==10)
               passo=0.1
            else
               passo=1/(1000*fv)
            inf=-2*Tv+ritv
            sup=2*Tv+ritv
        }
        else
        if((identifier==11)||(identifier==12))
        {
            passo=1/(1000*fv)
            inf=-5/fv+ritv
            sup=5/fv+ritv
        }
        else
        if(identifier==27)
        {
            if(fv<fv2)
            {
                passo=1/(1000*fv2)
                inf = -5 / fv + ritv
                sup = 5 / fv + ritv
            }
            else
            {
                passo=1/(4*fv)
                inf = -5 / fv2 + ritv
                sup = 5 / fv2 + ritv
            }
        }
        else
        {
            inf=ritv
            if(identifier==22)
            {
                passo=0.1
                if(tauv>tauv2)
                    sup=4.5*tauv+ritv
                else
                    sup=4.5*tauv+ritv
            }
            else
                if(identifier==24)
                {
                    passo=(2*PI)/(1000*immv)
                    sup = -4.5 / realv + ritv
                }
                else
                {
                    passo=0.1
                    sup = 4.5 * tauv + ritv
                }
        }
    }
    private fun valutazione()
    {
        if(f1>f1_2)
            fv=f1
        else
            fv=f1_2
        if(T>T2)
            Tv=T
        else
            Tv=T2
        if(rit>rit2)
            ritv=rit
        else
            ritv=rit2
        if(f2>f2_2)
            fv2=f2
        else
            fv2=f2_2
        if(tau1>tau1_2)
            tauv=tau1
        else
            tauv=tau1_2
        if(tau2>tau2_2)
            tauv2=tau2
        else
            tauv2=tau2_2
        if(real>real2)
            realv=real
        else
            realv=real2
        if(imm>imm2)
            immv=imm
        else
            immv=imm2
    }
    private fun checkPermission():Boolean
    {
        val permesso= ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED&& ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
        return permesso
    }
    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),PERMISSION_REQUEST_CODE)
    }
}

