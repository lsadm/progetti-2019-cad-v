package com.example.mathfactory
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_call.*
import java.io.ByteArrayInputStream

class call : AppCompatActivity()
{
    var mediaplayer:MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val immagine=getIntent().getExtras().getByteArray("immagine")
        val stream= ByteArrayInputStream(immagine)
        val imm_bitmap= BitmapFactory.decodeStream(stream)
        foto.setImageBitmap(imm_bitmap)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_application3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Hi, I'm your own\nPhoto_Viewer!", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_home) {
            val next: Intent
            next = Intent(this, Function9::class.java)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.return_graph_sound)
            mediaplayer?.start()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
