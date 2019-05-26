package com.example.mathfactory
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function9.*
import java.text.SimpleDateFormat
import java.util.*
class Function9 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val PERMISSION_REQUEST_CODE= 101
    val CAMERA_REQUEST_CODE=0
    private var mediaplayer: MediaPlayer?=null
    var adapter2:CustomAdapter2?=null
    val formato= SimpleDateFormat("HH:mm:ss                                                            dd/MM/yyyy")
    var data:String=""
    var imm:Bitmap?=null
    var controllo=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function9)
        nav_view9.setNavigationItemSelectedListener(this)
        if(checkPermission())
            button53.setBackgroundResource(R.mipmap.imm30_foreground)
        else
            button53.setBackgroundResource(R.mipmap.imm29_foreground)
        textView55.text="No photo is ready to\nupload!"
        val recyclerView2=findViewById(R.id.recyclerView2)as RecyclerView
        recyclerView2.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val users2= ArrayList<User2>()
        button53.setOnClickListener { if(checkPermission()){ button53.setBackgroundResource(R.mipmap.imm30_foreground);go_to_camera()}else requestPermission()}
        button21.setOnClickListener{
            if(imm!=null)
            {
                data=formato.format(Date()).toString()
                users2.add(User2(imm,data))
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                imm=null
                textView55.setTextColor(rgb(155,17,30))
                textView55.text="No photo is ready to\nupload!"
                Toast.makeText(this,"The image has been\nsuccessfully added!", Toast.LENGTH_LONG).show()
                adapter2=CustomAdapter2(users2)
                recyclerView2.adapter=adapter2
                controllo=false
            }
            else
            {
                Toast.makeText(this, "You can't add an empty image!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
        button52.setOnClickListener {
            if(users2.size!=0)
            {
                users2.clear()
                Toast.makeText(this, "Image Note has been\nsuccessfully cleaned up!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                adapter2 = CustomAdapter2(users2)
                recyclerView2.adapter = adapter2
            }
            else
                if(controllo)
                {
                    Toast.makeText(this, "Image Note is still empty!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                }
                else
                {
                    Toast.makeText(this,"Image Note has already\nbeen cleaned up!", Toast.LENGTH_LONG).show()
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
            Toast.makeText(this,"Hi, I can resolve all\nyour algebraic equations!", Toast.LENGTH_LONG).show()
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
            mediaplayer= MediaPlayer.create(this,R.raw.move_home_sound)
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
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_immagini) {
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer= MediaPlayer.create(this,R.raw.error_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_six) {
            val next = Intent(this, Function3::class.java)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_seven) {
            val next = Intent(this, Function4::class.java)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_eight) {
            val next = Intent(this, Function5::class.java)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_nine) {
            val next = Intent(this, Function6::class.java)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_ten) {
            val next = Intent(this, Function7::class.java)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
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
        if (id == R.id.action_five) {
            val next = Intent(this, Function2::class.java)
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
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
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
            CAMERA_REQUEST_CODE->{
                if(resultCode== Activity.RESULT_OK&&data!=null)
                {
                    imm = data.extras.get("data") as Bitmap
                    textView55.setTextColor(rgb(40,114,51))
                    textView55.text="The photo is ready to\nupload!"
                }
            }
            else->{
                Toast.makeText(this,"Ops... Something is gone wrong!",Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
    }
    private fun checkPermission():Boolean
    {
        val permesso= ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
        return permesso
    }
    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),PERMISSION_REQUEST_CODE)
    }
    private fun go_to_camera()
    {
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (callCameraIntent.resolveActivity(packageManager) != null)
        {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            Toast.makeText(this, "The camera has been\nsuccessfully activated!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.move_graph_sound)
            mediaplayer?.start()
        }
    }
}
