package com.example.mathfactory

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start_.*
import java.util.*

class Start_Activity : AppCompatActivity()
{
    private var mediaplayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_)
        button24.setOnClickListener {settaggio(1)}
        button25.setOnClickListener {settaggio(2)}
        button27.setOnClickListener {settaggio(0)}
        button28.setOnClickListener { val next = Intent(this, MainActivity::class.java);startActivity(next);mediaplayer = MediaPlayer.create(this, R.raw.move_sound);mediaplayer?.start() }
    }
    override fun onCreateOptionsMenu(menu: Menu):Boolean
    {
        menuInflater.inflate(R.menu.menu_application4, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Have fun with MathView!", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_help) {
            Toast.makeText(this, "Please login or\nregister at MathView", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun settaggio(id:Int)
    {
        if (id != 0)
        {
            button24.visibility = View.INVISIBLE
            button25.visibility = View.INVISIBLE
            textView39.visibility = View.VISIBLE
            textView37.visibility = View.VISIBLE
            editText15.visibility = View.VISIBLE
            editText14.visibility = View.VISIBLE
            textView48.visibility = View.VISIBLE
            button28.visibility = View.VISIBLE
            button27.visibility = View.VISIBLE
            if (id == 1)
            {
                editText15.hint="Username"
                editText14.hint="Password"
                textView48.text = "Access"
                button28.text = "Login"
            } else
                if (id == 2)
                {
                    editText15.hint="Choose username"
                    editText14.hint="Choose password"
                    textView47.visibility = View.VISIBLE
                    textView49.visibility = View.VISIBLE
                    editText16.visibility = View.VISIBLE
                    spinner.visibility = View.VISIBLE
                    textView48.text = "Registration"
                    button28.text = "Confirm\nRegistration"
                }
        }
        else
        {
            button24.visibility = View.VISIBLE
            button25.visibility = View.VISIBLE
            textView39.visibility = View.INVISIBLE
            textView37.visibility = View.INVISIBLE
            editText15.visibility = View.INVISIBLE
            editText14.visibility = View.INVISIBLE
            textView48.visibility = View.INVISIBLE
            button28.visibility = View.INVISIBLE
            button27.visibility = View.INVISIBLE
            textView47.visibility = View.INVISIBLE
            textView49.visibility = View.INVISIBLE
            editText16.visibility = View.INVISIBLE
            spinner.visibility = View.INVISIBLE
        }
    }
}
