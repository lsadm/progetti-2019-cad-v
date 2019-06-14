package com.example.mathfactory
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_call.*
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream

class call : AppCompatActivity()
{
    val SHARING_REQUEST_CODE2=0
    var mediaplayer:MediaPlayer?=null
    var Id_Utente:String?=null
    var controllo:Boolean?=null
    val myHandler= Grafici.MyHandler(this)
    val changeColorThread=com.example.mathfactory.ChangeColorThread(myHandler)
    var imm_bitmap:Bitmap?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        controllo_generale7=false
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        val immagine=getIntent().getExtras().getByteArray("immagine")
        val titolo_immagine=getIntent().getExtras().getString("titolo_immagine")
        controllo=getIntent().getExtras().getBoolean("controllo")
        if(immagine!=null)
        {
            val stream = ByteArrayInputStream(immagine)
            imm_bitmap = BitmapFactory.decodeStream(stream)
            foto.setImageBitmap(imm_bitmap)
            Toast.makeText(this, titolo_immagine + " preview!", Toast.LENGTH_LONG).show()
        }
        else
        {
            foto.setBackgroundResource(R.mipmap.imm40_foreground)
            Toast.makeText(this, titolo_immagine+" not\navailable for preview!", Toast.LENGTH_LONG).show()
        }
    }
    override fun onBackPressed() {}
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_application3, menu)
        share=menu
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Hi, I'm your own\nImage_Viewer!", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if(id==R.id.action_share)
        {
            if(imm_bitmap!=null)
                share_graph(imm_bitmap!!,changeColorThread)
            else
            {
                Toast.makeText(this, "Warning: You can't share\nan empty image!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
            }
            return true
        }
        if (id == R.id.action_home) {
            val next: Intent
            if(controllo==true)
                next = Intent(this, Function9::class.java)
            else
                if(controllo==false)
                    next= Intent(this,Profilo_Utente::class.java)
                else
                    next= Intent(this,MainActivity::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.return_graph_sound)
            mediaplayer?.start()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            SHARING_REQUEST_CODE2->
            {
                if (resultCode == Activity.RESULT_OK)
                {
                    Toast.makeText(this, "The image has been\nsuccessfully shared!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound)
                    mediaplayer?.start()
                    controllo_generale10=true
                    controllo_generale11=true
                }
                else
                {
                    Toast.makeText(this,"The image has not been shared!",Toast.LENGTH_LONG).show()
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
                if(requestCode== SHARING_REQUEST_CODE2)
                {
                    controllo_generale10 = true
                    controllo_generale11 = false
                }
            }
        }
    }
    private fun share_graph(bitmap: Bitmap, changeColorThread:ChangeColorThread)
    {
        val file= File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Parameters/Parameter5.jpg")
        val file_output_stream= FileOutputStream(file)
        bitmap?.compress(Bitmap.CompressFormat.JPEG,50,file_output_stream)
        file_output_stream.flush()
        file_output_stream.close()
        val shareIntent=Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
        shareIntent.type="image/jpeg"
        intent.putExtra(Intent.EXTRA_SUBJECT,"Graph Image")
        startActivityForResult(Intent.createChooser(shareIntent,"Share with..."), SHARING_REQUEST_CODE2)
        Toast.makeText(this,"The sharing box has been\nsuccessfully activated!", Toast.LENGTH_LONG).show()
        mediaplayer = MediaPlayer.create(this, R.raw.move_graph_sound)
        mediaplayer?.start()
        changeColorThread.start()
    }
}
