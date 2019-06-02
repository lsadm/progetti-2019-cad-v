package com.example.mathfactory
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.mathfactory.com.example.mathfactory.Check_Network
import com.example.mathfactory.com.example.mathfactory.Utente
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_start_.*
class Start_Activity : AppCompatActivity()
{
    lateinit var item_spinner:Array<String>
    lateinit var adapter_spinner:ArrayAdapter<Any>
    val PERMISSION_REQUEST_CODE=0
    var spinner: Spinner?=null
    var selezione_spinner:String=""
    var controllo:Boolean?=null
    private var mediaplayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_)
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                button28.performClick()
            }
        }
        class CheckThread constructor(val handler: Handler):Thread()
        {
            override fun run()
            {
                if(!checkPermission())
                {
                    while (!checkPermission())
                        sleep(1000)
                    sleep(500)
                    notify_message("")
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
        val checkThread=CheckThread(myHandler)
        checkThread.start()
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        item_spinner=arrayOf("Not specified","Male","Female")
        adapter_spinner=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,item_spinner)
        spinner=findViewById(R.id.spinner)as Spinner
        spinner?.adapter=adapter_spinner
        button24.setOnClickListener {settaggio(1);controllo=false}
        button25.setOnClickListener {settaggio(2);controllo=true}
        button27.setOnClickListener {settaggio(0)}
        button28.setOnClickListener {if(checkPermission()){if((controllo==true)&&(aggiungi_utente())){val next = Intent(this, MainActivity::class.java);settaggio(0);startActivity(next);mediaplayer = MediaPlayer.create(this, R.raw.move_sound);mediaplayer?.start()}}else requestPermission()}
        spinner?.onItemSelectedListener=object:AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                selezione_spinner="${parent?.getItemAtPosition(position).toString()}"
            }
            override fun onNothingSelected(parent: AdapterView<*>?)
            {
                selezione_spinner="${parent?.getItemAtPosition(0).toString()}"
            }
        }
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
            editText14.setText("")
            editText15.setText("")
            editText16.setText("")
            spinner?.adapter=adapter_spinner
            if (id == 1)
            {
                editText15.hint="Username"
                editText14.hint="Password"
                textView48.text = "Access"
                button28.text = "Login"
                editText14.setText("")
                editText15.setText("")
            } else
                if (id == 2)
                {
                    editText15.hint="Choose username"
                    editText14.hint="Choose password"
                    textView47.visibility = View.VISIBLE
                    textView49.visibility = View.VISIBLE
                    editText16.visibility = View.VISIBLE
                    spinner?.visibility = View.VISIBLE
                    textView48.text = "Registration"
                    button28.text = "Confirm\nRegistration"
                    editText14.setText("")
                    editText15.setText("")
                    editText16.setText("")
                    spinner?.adapter=adapter_spinner
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
            spinner?.visibility = View.INVISIBLE
        }
    }
    private fun aggiungi_utente():Boolean
    {
      if((editText14.text.toString()=="")||(editText15.text.toString()=="")||(editText16.text.toString()==""))
      {
          Toast.makeText(this, "Warning: Data Missing!", Toast.LENGTH_LONG).show()
          mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
          mediaplayer?.start()
          return false
      }
        else
          if(editText14.text.toString()!=editText16.text.toString())
          {
              Toast.makeText(this, "Warning: Unmatched passwords!", Toast.LENGTH_LONG).show()
              mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
              mediaplayer?.start()
              return false
          }
        else
          {
              val controllo_connessione=Check_Network()
              if(controllo_connessione.Network_Disponibile(this))
              {
                  val dati = arrayOf<String>(editText15.text.toString(), editText14.text.toString(), "Not specified", "Not specified", selezione_spinner, "Not specified", "Not specified")
                  val referenza_database = FirebaseDatabase.getInstance().getReference("Users")
                  val Id_Utente = referenza_database.push().key.toString()
                  val utente =Utente(Id_Utente, dati[0], dati[1], dati[2], dati[3], dati[4], dati[5], dati[6])
                  referenza_database.child(Id_Utente).setValue(utente).addOnCompleteListener {
                      Toast.makeText(this, "A new user has been\nsuccessfully added!", Toast.LENGTH_LONG).show()
                  }
                  return true
              }
              else
              {
                  Toast.makeText(this, "Ops... Connection is not available!", Toast.LENGTH_LONG).show()
                  mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                  mediaplayer?.start()
                  return false
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
