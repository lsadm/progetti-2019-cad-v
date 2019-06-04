package com.example.mathfactory
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Color.rgb
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
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_start_.*
var controllo_generale2:Boolean?=null
class Start_Activity : AppCompatActivity()
{
    var controllo_barra=false
    var utente:Utente?=null
    lateinit var referenza_database:DatabaseReference
    var Id_Utente:String=""
    lateinit var item_spinner:Array<String>
    lateinit var adapter_spinner:SpinnerAdapter
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
                if(valore=="visible")
                    progress_access.visibility=View.VISIBLE
                    else
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
        class ProgressBarThread constructor(val handler:Handler):Thread()
        {
            override fun run()
            {
                while(!controllo_barra)
                {
                    sleep(500)
                }
                notify_message("visible")
                while(true)
                {
                    while(progress_access.progress<100)
                    {
                       progress_access.incrementProgressBy(1)
                       sleep(10)
                    }
                    progress_access.progress=0
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
        val progressThread=ProgressBarThread(myHandler)
        progressThread.start()
        checkThread.start()
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        item_spinner=arrayOf("None","Male","Female")
        adapter_spinner=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,item_spinner)
        spinner=findViewById(R.id.spinner)as Spinner
        spinner?.adapter=adapter_spinner
        button24.setOnClickListener {settaggio(1);controllo=false}
        button25.setOnClickListener {settaggio(2);controllo=true}
        button27.setOnClickListener {settaggio(0)}
        button28.setOnClickListener {controllo_generale2=true;if(checkPermission()){if(controllo==true)aggiungi_utente()else if(controllo==false)verifica_utente(this)}else requestPermission()}
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
                    editText15.hint="Choose username (Min 4 chars)"
                    editText14.hint="Choose password (Min 8 chars)"
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
    private fun aggiungi_utente()
    {
      if((editText14.text.toString()=="")||(editText15.text.toString()=="")||(editText16.text.toString()=="")||(selezione_spinner=="None"))
      {
          Toast.makeText(this, "Warning: Data missing!", Toast.LENGTH_LONG).show()
          mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
          mediaplayer?.start()
      }
        else
          if((editText14.text.toString().length<8)||(editText14.text.toString()==""))
          {
              Toast.makeText(this, "Warning: Username or password\ntoo short!", Toast.LENGTH_LONG).show()
              mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
              mediaplayer?.start()
          }
        else
          if(editText14.text.toString()!=editText16.text.toString())
          {
              Toast.makeText(this, "Warning: Unmatched passwords!", Toast.LENGTH_LONG).show()
              mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
              mediaplayer?.start()
          }
        else
          {
              val controllo_connessione=Check_Network()
              if(controllo_connessione.Network_Disponibile(this))
              {
                  val dati = arrayOf<String>(editText15.text.toString(), editText14.text.toString(), "", "", selezione_spinner, "", "")
                  referenza_database = FirebaseDatabase.getInstance().getReference("Users")
                  controllo_barra=true
                      Id_Utente = referenza_database.push().key.toString()
                      utente = Utente(Id_Utente, dati[0], dati[1], dati[2], dati[3], dati[4], dati[5], dati[6])
                      referenza_database.child(Id_Utente).setValue(utente).addOnCompleteListener {
                          Toast.makeText(this, editText15.text.toString() + "\nhas been successfully added!", Toast.LENGTH_LONG).show()
                          val next = Intent(this, MainActivity::class.java)
                          next.putExtra("Id_Utente", Id_Utente)
                          settaggio(0)
                          startActivity(next)
                          mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                          mediaplayer?.start()
                      }
              }
              else
              {
                  Toast.makeText(this, "Warning: The server is not reachable!", Toast.LENGTH_LONG).show()
                  mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                  mediaplayer?.start()
              }
          }
    }
    private fun verifica_utente(contesto: Context)
    {
        if((editText15.text.toString()=="")||(editText14.text.toString()==""))
        {
            Toast.makeText(this, "Warning: Data missing!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
            mediaplayer?.start()
        }
        else
        if((editText15.text.toString().length<4)||(editText14.text.toString().length<8))
        {
            Toast.makeText(this, "Warning: Username or password\ntoo short!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
            mediaplayer?.start()
        }
        else
        {
            val controllo_connessione = Check_Network()
            if (controllo_connessione.Network_Disponibile(this))
            {
                var controllo=true
                referenza_database = FirebaseDatabase.getInstance().getReference("Users")
                controllo_barra=true
                referenza_database.addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {}
                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.exists())
                        {
                            for(record in p0.children)
                            {
                                if(controllo)
                                {
                                    utente = record.getValue(Utente::class.java)
                                        if (utente?.username==editText15.text.toString())
                                        {
                                            controllo = false
                                            if (utente?.password == editText14.text.toString())
                                                Id_Utente = utente?.chiave.toString()
                                        }
                                }
                            }
                        }
                        if(esito(controllo)&&(controllo_generale2==true))
                        {
                            val next = Intent(contesto, MainActivity::class.java)
                            next.putExtra("Id_Utente",Id_Utente)
                            settaggio(0)
                            startActivity(next)
                            mediaplayer = MediaPlayer.create(contesto, R.raw.move_sound)
                            mediaplayer?.start()
                        }
                    }
                })
            }
            else
            {
                Toast.makeText(this, "Warning: The server is not reachable!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
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
    private fun esito(controllo:Boolean):Boolean
    {
        if(controllo)
        {
            Toast.makeText(this, "Warning: Username '"+editText15.text.toString()+"'\nnot found!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
            mediaplayer?.start()
            return false
        }
        else
            if(Id_Utente=="")
            {
                Toast.makeText(this, "Warning: Unmatched passwords!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
                return false
            }
            else
            {
               if(controllo_generale2==true)
                  Toast.makeText(this, "Welcome back\n"+editText15.text.toString()+"!", Toast.LENGTH_LONG).show()
                return true
            }
    }
}
