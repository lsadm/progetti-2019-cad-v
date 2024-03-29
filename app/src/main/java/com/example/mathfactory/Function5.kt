package com.example.mathfactory

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function5.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
var controllo=false
class Function5 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val PERMISSION_REQUEST_CODE=0
    private var mediaplayer: MediaPlayer?=null
    var Id_Utente:String?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function5)
        nav_view5.setNavigationItemSelectedListener(this)
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        calculate.setOnClickListener { random();editText.setText("");editText2.setText("");editText3.setText("")}
    }
    override fun onBackPressed() {}
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
            Toast.makeText(this,"Hi, I can generate a lot of\nrundom nambers!", Toast.LENGTH_LONG).show()
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
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
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
            val next = Intent(this, Function7::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
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
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        return true
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

