package com.example.mathfactory
import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profilo__utente.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Profilo_Utente : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var controllo=true
    val PERMISSION_REQUEST_CODE= 101
    val CAMERA_REQUEST_CODE=0
    private var mediaplayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilo__utente)
        nav_view_PU.setNavigationItemSelectedListener(this)
        editText26.inputType= InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        if(checkPermission())
            button26.setBackgroundResource(R.mipmap.imm13)
        else
            button26.setBackgroundResource(R.mipmap.imm15)
        button26.setOnClickListener{if(checkPermission()){ button26.setBackgroundResource(R.mipmap.imm13);go_to_camera()}else requestPermission()}
        button51.setOnClickListener {if(controllo){editText26.inputType=InputType.TYPE_CLASS_TEXT;editText26.setSelection(editText26.text.lastIndex+1);button51.setBackgroundResource(R.mipmap.imm18);controllo=false}else{editText26.inputType= InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD;editText26.setSelection(editText26.text.lastIndex+1);button51.setBackgroundResource(R.mipmap.imm17);controllo=true}}
        button29.setOnClickListener {setta_uscita(true)}
        button50.setOnClickListener {setta_uscita(false)}
        button49.setOnClickListener {val next = Intent(this, Start_Activity::class.java);startActivity(next);mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound);mediaplayer?.start()  }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_application2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Hi, here is your profile!", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_home) {
            val next = Intent(this, MainActivity::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound)
            mediaplayer?.start()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_four) {
            val next = Intent(this, Function1::class.java)
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
        if (id == R.id.action_six) {
            val next = Intent(this, Function3::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_seven) {
            val next = Intent(this, Function4::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_eight) {
            val next = Intent(this, Function5::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_nine) {
            val next = Intent(this, Function6::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_ten) {
            val next = Intent(this, Function7::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
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
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer=MediaPlayer.create(this,R.raw.error_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
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
               if(resultCode==Activity.RESULT_OK&&data!=null)
               {
                   imageView.setImageBitmap(data.extras.get("data") as Bitmap)
                   Toast.makeText(this, "The photo has been\nsuccessfully uploaded!", Toast.LENGTH_LONG).show()
                   mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                   mediaplayer?.start()
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
        val permesso=ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED
        return permesso
    }
    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(CAMERA),PERMISSION_REQUEST_CODE)
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
    private fun setta_uscita(controllo:Boolean)
    {
        if(controllo)
        {
            textView52.visibility=View.VISIBLE
            button50.visibility=View.VISIBLE
            button49.visibility=View.VISIBLE
        }
        else
        {
            textView52.visibility=View.INVISIBLE
            button50.visibility=View.INVISIBLE
            button49.visibility=View.INVISIBLE
        }
    }
}
