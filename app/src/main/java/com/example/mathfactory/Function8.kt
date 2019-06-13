package com.example.mathfactory

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
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
import java.io.File
import java.nio.charset.Charset
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
    var output_parametro1:String?=null
    var output_data:String?=null
    val prefisso1:String="Text_Note_"
    val prefisso_data:String="Date_Text_Note_"
    var titolo1:String=""
    var nome_text:String?=null
    var nome_data_text:String?=null
    var textFile:File?=null
    var file_parametro1:File?=null
    var file_data:File?=null
    var text:String?=null
    var counter:Int?=null
    var indice:Int?=1
    var Id_Utente:String?=null
    val path_title=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Text_Note_Title/Text_Note_Title_"
    var file_title:File?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function8)
        nav_view8.setNavigationItemSelectedListener(this)
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        val recyclerView=findViewById(R.id.recyclerView)as RecyclerView
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val users=ArrayList<User>()
        output_parametro1=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Parameters/Parameter1.txt"
        output= Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Text/"
        output_data=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Date_Text_Note/"
        file_parametro1=File(output_parametro1)
            if (file_parametro1?.exists() == true)
                counter = file_parametro1?.readText(Charsets.UTF_8)?.toInt()?.plus(1)
            else
                counter = 1
            if (counter != 1)
            {
                while (indice != counter)
                {
                    nome_text = output + prefisso1 + indice?.toString() + ".txt"
                    nome_data_text = output_data + prefisso_data + indice?.toString() + ".txt"
                    textFile = File(nome_text)
                    file_data = File(nome_data_text)
                    if((textFile?.exists()==true)&&(file_data?.exists()==true))
                    {
                        var title:String?=null
                        file_title=File(path_title+indice.toString()+".txt")
                        if(file_title?.exists()==true)
                            title=file_title?.readText(Charsets.UTF_8)
                        else
                            title=prefisso1 + indice?.toString()
                        users.add(User(textFile?.readText(Charsets.UTF_8), "Upload time and date---> " + file_data?.readText(Charsets.UTF_8),prefisso1 + indice?.toString(),this,title.toString(),indice!!))
                    }
                    indice = indice?.plus(1)
                }
                adapter = CustomAdapter(users)
                recyclerView.adapter = adapter
            }
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        button21.setOnClickListener{
            if(editText17.text.toString()!="")
            {
                if(users.size==0)
                    counter=1
                data=formato.format(Date()).toString()
                nome_text=output+prefisso1+counter.toString()+".txt"
                nome_data_text=output_data+prefisso_data+counter.toString()+".txt"
                textFile=File(nome_text)
                file_data=File(nome_data_text)
                textFile?.writeText(editText17.text.toString(),Charsets.UTF_8)
                file_data?.writeText(data,Charsets.UTF_8)
                file_parametro1?.writeText(counter.toString(),Charsets.UTF_8)
                text=textFile?.readText(Charsets.UTF_8)
                titolo1=prefisso1+counter.toString()
                users.add(User(text, "Upload time and date---> "+data,titolo1,this,titolo1,counter!!))
                counter=counter?.plus(1)
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
                cancellazione_text()
                counter=1
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
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_immagini) {
            val next = Intent(this, Function9::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_audio) {
            val next = Intent(this, Function10::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            SHARING_REQUEST_CODE->
            {
                if (resultCode == Activity.RESULT_OK)
                {
                    Toast.makeText(this, "The text has been\nsuccessfully shared!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound)
                    mediaplayer?.start()
                    controllo_generale10=true
                    controllo_generale11=true
                }
                else
                {
                    Toast.makeText(this,"The text has not been shared!",Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                    controllo_generale10=false
                    controllo_generale11=true
                }
            }
            else->
            {
                Toast.makeText(this,"Ops... Something is gone wrong!",Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
                if(requestCode== SHARING_REQUEST_CODE)
                {
                    controllo_generale10 = true
                    controllo_generale11 = false
                }
            }
        }
    }
    private fun cancellazione_text()
    {
        indice=1
        while (indice != counter)
        {
            nome_text = output + prefisso1 + indice?.toString() + ".txt"
            nome_data_text=output_data+prefisso_data+indice?.toString()+".txt"
            textFile = File(nome_text)
            file_data=File(nome_data_text)
            file_title=File(path_title+indice.toString()+".txt")
            if((textFile?.exists()==true)&&(file_data?.exists()==true)&&(file_title?.exists()==true))
            {
                textFile?.delete()
                file_data?.delete()
                file_title?.delete()
            }
            indice=indice?.plus(1)
        }
        file_parametro1=File(output_parametro1)
        file_parametro1?.delete()
    }
}

