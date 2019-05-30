package com.example.mathfactory
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*
import kotlin.math.*
var controllo_generale:Int=0
class MainActivity : AppCompatActivity()
{
    private var mediaplayer:MediaPlayer?=null
    val formato=SimpleDateFormat("              HH:mm\n         dd/MM/yyyy")
    var data:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle=ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        storia.setOnClickListener { val next = Intent(this, Function8::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()   }
        storia_per_immagini.setOnClickListener { val next = Intent(this, Function9::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()   }
        storia_vocale.setOnClickListener { val next = Intent(this, Function10::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()   }
        calcolatrice.setOnClickListener {  val next = Intent(this, Function0::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        numeri_casuali.setOnClickListener {  val next = Intent(this, Function5::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        equazioni_lineari.setOnClickListener {  val next = Intent(this, Function2::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        equanzioni_differenziali.setOnClickListener {  val next = Intent(this, Function4::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        numeri_complessi.setOnClickListener {  val next = Intent(this, Function6::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        plot1.setOnClickListener {  val next = Intent(this, Function1::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start() }
        plot2.setOnClickListener {  val next = Intent(this, Function3::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        plot3.setOnClickListener {  val next = Intent(this, Function7::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()   }
        profilo.setOnClickListener {val next = Intent(this, Profilo_Utente::class.java);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()}
    }
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
}
