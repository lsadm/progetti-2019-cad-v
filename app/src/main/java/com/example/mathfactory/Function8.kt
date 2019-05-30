package com.example.mathfactory

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function1.*
import kotlinx.android.synthetic.main.activity_function8.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class Function8 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val PERMISSION_REQUEST_CODE=0
    private var mediaplayer: MediaPlayer?=null
    val formato= SimpleDateFormat("HH:mm:ss_dd/MM/yyyy")
    var data:String=""
    var adapter:CustomAdapter?=null
    var controllo=true
    var output:String?=null
    val prefisso1:String="Text_Note_"
    var titolo1:String=""
    var counter=0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function8)
        nav_view8.setNavigationItemSelectedListener(this)
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        if(checkPermission())
            buttoncheck.setBackgroundResource(R.mipmap.imm36_foreground)
        else
            editText17.inputType=InputType.TYPE_NULL
        val recyclerView=findViewById(R.id.recyclerView)as RecyclerView
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val users=ArrayList<User>()
        output= Environment.getExternalStorageDirectory().absolutePath+"/MathView_Text/Text_Note_"
        buttoncheck.setOnClickListener {if(checkPermission()){buttoncheck.setBackgroundResource(R.mipmap.imm36_foreground);editText17.inputType= InputType.TYPE_CLASS_TEXT}else requestPermission()}
        button21.setOnClickListener{
            if(editText17.text.toString()!="")
            {
                data=formato.format(Date()).toString()
                counter++
                titolo1=prefisso1+counter.toString()
                users.add(User(editText17.text.toString(), "Upload time and date---> "+data,titolo1))
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                editText17.setText("")
                Toast.makeText(this,"The note has been\nsuccessfully added!", Toast.LENGTH_LONG).show()
                adapter=CustomAdapter(users)
                recyclerView.adapter=adapter
                controllo=false
            }
            else
            {
                Toast.makeText(this, "You can't add an empty note!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
            }
             }
        button52.setOnClickListener {
            if(users.size!=0)
            {
                users.clear()
                Toast.makeText(this, "Text Note has been\nsuccessfully cleaned up!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                adapter = CustomAdapter(users)
                recyclerView.adapter = adapter
            }
            else
                if(controllo)
                {
                    Toast.makeText(this, "Text Note is still empty!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                }
                else
                {
                    Toast.makeText(this, "Text Note has already\nbeen cleaned up!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                }
        }
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
            Toast.makeText(this,"Hi, I am your own\nText Note!", Toast.LENGTH_LONG).show()
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
        if (id == R.id.Profile) {
            val next = Intent(this, Profilo_Utente::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_testuali) {
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
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
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            startActivity(next)
            mediaplayer=MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        return true
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

