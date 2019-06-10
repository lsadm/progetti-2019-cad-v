package com.example.mathfactory
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*
var controllo_generale:Int=0
class MainActivity : AppCompatActivity()
{
    var controllo_barra=false
    var Id_Utente:String=""
    val PERMISSION_REQUEST_CODE=0
    private var mediaplayer:MediaPlayer?=null
    val formato=SimpleDateFormat("              HH:mm\n         dd/MM/yyyy")
    var data:String=""
    val prefisso:String=Environment.getExternalStorageDirectory().absolutePath+"/MathView/"+utente_loggato
    val suffissi=arrayOf<String>("","/MathView_Parameters","/MathView_Text","/MathView_Date_Text_Note","/MathView_Image","/MathView_Date_Image_Note","/MathView_Audio","/MathView_Duration_Audio_Note","/MathView_Date_Audio_Note")
    var paths=arrayOf<String?>(null,null,null,null,null,null,null,null,null)
    var directories=arrayOf<File?>(null,null,null,null,null,null,null,null,null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        if(controllo_archivio==true)
        {
            for (i in 0..8)
            {
                paths[i] = prefisso + suffissi[i]
                directories[i] = File(paths[i])
                if (directories[i]?.exists() == false)
                    directories[i]?.mkdir()
            }
            controllo_archivio=false
        }
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
                        progress_access.visibility=View.INVISIBLE
            }
        }
        class ProgressBarThread constructor(val handler:Handler):Thread()
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
        val toogle=ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        storia.setOnClickListener {if(checkPermission()){ controllo_barra=true;val next = Intent(this, Function8::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start();controllo_barra=false}else requestPermission()}
        storia_per_immagini.setOnClickListener{if(checkPermission()) {controllo_barra=true; val next = Intent(this, Function9::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start();controllo_barra=false}else requestPermission()}
        storia_vocale.setOnClickListener {if(checkPermission()){controllo_barra=true; val next = Intent(this, Function10::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start();controllo_barra=false}else requestPermission()}
        calcolatrice.setOnClickListener {  val next = Intent(this, Function0::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        numeri_casuali.setOnClickListener {  val next = Intent(this, Function5::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        equazioni_lineari.setOnClickListener {  val next = Intent(this, Function2::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        equanzioni_differenziali.setOnClickListener {  val next = Intent(this, Function4::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        numeri_complessi.setOnClickListener {  val next = Intent(this, Function6::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        plot1.setOnClickListener {  val next = Intent(this, Function1::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start() }
        plot2.setOnClickListener {  val next = Intent(this, Function3::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        plot3.setOnClickListener {  val next = Intent(this, Function7::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()   }
        profilo.setOnClickListener {if(checkPermission()){val next = Intent(this, Profilo_Utente::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()}else requestPermission()}
    }
    override fun onBackPressed() {}
    override fun onCreateOptionsMenu(menu: Menu):Boolean
    {
        menuInflater.inflate(R.menu.menu_application, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Enjoy with Math!", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_clock) {
            data=formato.format(Date()).toString()
            Toast.makeText(this,"Current Time and Day:\n"+data, Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
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
