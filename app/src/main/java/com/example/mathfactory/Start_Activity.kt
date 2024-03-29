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
import com.example.mathfactory.com.example.mathfactory.Fragment_Quit_Application
import com.example.mathfactory.com.example.mathfactory.Utente
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_start_.*
import kotlinx.android.synthetic.main.fragment_quit_application.*
import java.io.File
import kotlin.system.exitProcess
var controllo_generale2:Boolean?=null
var controllo_generale3:Boolean?=null
var controllo_generale4:Boolean?=null
var controllo_generale5:Boolean?=null
var controllo_generale6:Boolean?=null
var utente_loggato:String?=null
var controllo_archivio:Boolean?=null
var controllo_generale8:Boolean?=null
var controllo_generale9=true
val file_controllo_numero_iscritti=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/Subscribers_number.txt")
class Start_Activity : AppCompatActivity()
{
    var controllo_interno=true
    val main_directory=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView")
    val reflesh_directory=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/MathView_Reflesh_Parameters")
    val ricorda_file_username=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/MathView_Reflesh_Parameters/Reflesh_Parameter1.txt")
    val ricorda_file_password=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/MathView_Reflesh_Parameters/Reflesh_Parameter2.txt")
    val ricorda_stato_switcher=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/MathView_Reflesh_Parameters/Reflesh_Parameter3.txt")
    var stato_switcher=false
    var controllo_barra=false
    var utente:Utente?=null
    lateinit var referenza_database:DatabaseReference
    var Id_Utente:String=""
    lateinit var item_spinner:Array<String>
    lateinit var adapter_spinner:ArrayAdapter<Any>
    val PERMISSION_REQUEST_CODE=0
    var spinner: Spinner?=null
    var selezione_spinner:String=""
    var controllo:Boolean?=null
    var controllo_Check_Thread=false
    var controllo_switcher=false
    var controllo_registrazione=false
    private var mediaplayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_)
        if(controllo_generale8==true)
        {
            moveTaskToBack(true)
            exitProcess(-1)
        }
        else
            if(controllo_generale8==false)
            {
                button24.setEnabled(true)
                button25.setEnabled(true)
                button27.setEnabled(true)
                button28.setEnabled(true)
                editText15.setEnabled(true)
                editText14.setEnabled(true)
                editText16.setEnabled(true)
                spinner?.setEnabled(true)
                switcher.setEnabled(true)
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
            }
        controllo_generale5=true
        controllo_generale6=true
        controllo_generale7=false
        controllo_archivio=true
        if(checkPermission()&&ricorda_stato_switcher.exists()&&(ricorda_stato_switcher.readText(Charsets.UTF_8)=="1")&&ricorda_file_username.exists()&&ricorda_file_password.exists())
        {
            stato_switcher=true
            editText15.setText(ricorda_file_username.readText(Charsets.UTF_8))
            editText15.setSelection(editText15.text.lastIndex+1)
            editText14.setText(ricorda_file_password.readText(Charsets.UTF_8))
            editText14.setSelection(editText14.text.lastIndex+1)
            switcher.setChecked(true)
        }
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                when(valore)
                {
                    "visible"->progress_access.visibility=View.VISIBLE
                    "invisible"->progress_access.visibility=View.INVISIBLE
                    "switch"->{controllo_switcher=false;mode_switcher_on()}
                    "registration"->button25.performClick()
                    "check"->button28.performClick()
                }
            }
        }
        class RegistrationThread constructor(val handler:Handler):Thread()
        {
            override fun run()
            {
                if(!checkPermission())
                {
                    while ((!checkPermission()))
                        sleep(1000)
                    if(controllo_registrazione)
                    {
                        sleep(500)
                        notify_message("registration")
                    }
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
        class SwitchThread constructor(val handler:Handler):Thread()
        {
            override fun run()
            {
                if(!checkPermission())
                {
                    while ((!checkPermission()))
                        sleep(1000)
                    if(controllo_switcher)
                    {
                        sleep(500)
                        notify_message("switch")
                    }
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
        class CheckThread constructor(val handler: Handler):Thread()
        {
            override fun run()
            {
                if(!checkPermission())
                {
                    while ((!checkPermission()))
                        sleep(1000)
                    if(controllo_Check_Thread)
                    {
                        sleep(500)
                        notify_message("check")
                    }
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
               while(true)
               {
                   while (!controllo_barra)
                   {
                       sleep(500)
                   }
                   notify_message("visible")
                   while (controllo_barra)
                   {
                       while (progress_access.progress < 100)
                       {
                           progress_access.incrementProgressBy(1)
                           sleep(10)
                       }
                       progress_access.progress = 0
                   }
                   notify_message("invisible")
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
        val switchThread=SwitchThread(myHandler)
        val registrationThread=RegistrationThread(myHandler)
        progressThread.start()
        checkThread.start()
        registrationThread.start()
        switchThread.start()
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        item_spinner=arrayOf("None","Male","Female")
        adapter_spinner=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,item_spinner)
        spinner=findViewById(R.id.spinner)as Spinner
        spinner?.adapter=adapter_spinner
        switcher.setOnCheckedChangeListener { buttonView, isChecked ->
            controllo_registrazione=false
            controllo_Check_Thread=false
            controllo_switcher=true
            if(checkPermission())
            {
                if (isChecked)
                {
                    stato_switcher=true
                    if(controllo_interno)
                    {
                        Toast.makeText(this, "I remember you c:", Toast.LENGTH_LONG).show()
                        mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                        mediaplayer?.start()
                    }
                }
                else
                {
                    stato_switcher=false
                    if(controllo_interno)
                    {
                        Toast.makeText(this, "I don't remember you :c", Toast.LENGTH_LONG).show()
                        mediaplayer = MediaPlayer.create(this, R.raw.move_graph_sound)
                        mediaplayer?.start()
                    }
                }
            }
            else
            {
                switcher.setChecked(false)
                requestPermission()
            }
        }
        button24.setOnClickListener {settaggio(1);controllo=false; mediaplayer = MediaPlayer.create(this, R.raw.move_sound);mediaplayer?.start()}
        button25.setOnClickListener {controllo_switcher=false;controllo_Check_Thread=false;controllo_registrazione=true;if(checkPermission()){settaggio(2);controllo=true;avvertimento()}else requestPermission()}
        button27.setOnClickListener {settaggio(0);mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound);mediaplayer?.start()}
        button28.setOnClickListener {controllo_switcher=false;controllo_registrazione=false;controllo_Check_Thread=true;if(checkPermission()){if(!main_directory.exists())main_directory.mkdir();if(!reflesh_directory.exists())reflesh_directory.mkdir();if((controllo==true)&&(controllo_numero_iscritti())){controllo_generale3=true;aggiungi_utente()}else if(controllo==false){controllo_generale2=true;controllo_generale4=true;verifica_utente(this)}}else ;requestPermission()}
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
    override fun onBackPressed()
    {
        if(controllo_generale9)
            Show_Exit_Message()
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
            spinner?.adapter=adapter_spinner
            if (id == 1)
            {
                editText15.hint="Username"
                editText14.hint="Password"
                textView48.text = "Access"
                button28.text = "Login"
                ricordami.visibility=View.VISIBLE
                switcher.visibility=View.VISIBLE
                if(stato_switcher)
                {
                    editText15.setText(ricorda_file_username.readText(Charsets.UTF_8))
                    editText15.setSelection(editText15.text.lastIndex+1)
                    editText14.setText(ricorda_file_password.readText(Charsets.UTF_8))
                    editText14.setSelection(editText14.text.lastIndex+1)
                }
            }
            else
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
            ricordami.visibility=View.INVISIBLE
            switcher.visibility=View.INVISIBLE
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
          if((editText14.text.toString().length<8)||(editText15.text.toString().length<4))
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
                  verifica_username(this)
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
                switcher.setEnabled(false)
                controllo_barra=true
                var controllo=true
                referenza_database = FirebaseDatabase.getInstance().getReference("Users")
                referenza_database.addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {}
                    override fun onDataChange(p0: DataSnapshot) {
                        if(p0.exists())
                        {
                            for(record in p0.children)
                            {
                                if((controllo)&&(controllo_generale2==true)&&(controllo_generale4==true))
                                {
                                    utente = record.getValue(Utente::class.java)
                                        if (utente?.username==editText15.text.toString())
                                        {
                                            controllo = false
                                            if (utente?.password == editText14.text.toString())
                                            {
                                                Id_Utente = utente?.chiave.toString()
                                                ricorda_file_username.writeText(utente?.username.toString(),Charsets.UTF_8)
                                                ricorda_file_password.writeText(utente?.password.toString(),Charsets.UTF_8)
                                                if(stato_switcher)
                                                    ricorda_stato_switcher.writeText("1",Charsets.UTF_8)
                                                else
                                                    ricorda_stato_switcher.writeText("0",Charsets.UTF_8)
                                                utente_loggato=utente?.username
                                            }
                                        }
                                }
                            }
                        }
                        if(esito(controllo)&&(controllo_generale2==true)&&(controllo_generale4==true)&&(controllo_generale6==true))
                        {
                            controllo_interno=false
                            val next = Intent(contesto, MainActivity::class.java)
                            next.putExtra("Id_Utente",Id_Utente)
                            settaggio(0)
                            startActivity(next)
                            mediaplayer = MediaPlayer.create(contesto, R.raw.move_sound)
                            mediaplayer?.start()
                            controllo_barra=false
                            switcher.setEnabled(true)
                            controllo_interno=true
                        }
                        else
                            switcher.setEnabled(true)
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
            if((controllo_generale2==true)&&(controllo_generale4==true)&&(controllo_generale6==true))
            {
                Toast.makeText(this, "Warning: Username '" + editText15.text.toString() + "'\nnot found!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
                controllo_barra = false
            }
            return false
        }
        else
            if(Id_Utente=="")
            {
                if((controllo_generale2==true)&&(controllo_generale4==true)&&(controllo_generale6==true))
                {
                    Toast.makeText(this, "Warning: Incorrect password!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                    controllo_barra = false
                }
                return false
            }
            else
            {
               if((controllo_generale2==true)&&(controllo_generale4==true)&&(controllo_generale6==true))
                  Toast.makeText(this, "Welcome back\n"+editText15.text.toString()+"!", Toast.LENGTH_LONG).show()
                return true
            }
    }
    private fun verifica_username(contesto:Context)
    {
            controllo_barra=true
            var controllo=true
            referenza_database = FirebaseDatabase.getInstance().getReference("Users")
            referenza_database.addValueEventListener(object:ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {}
                override fun onDataChange(p0: DataSnapshot)
                {
                    if(p0.exists())
                    {
                        for(record in p0.children)
                        {
                            if((controllo)&&(controllo_generale3==true))
                            {
                                utente = record.getValue(Utente::class.java)
                                if ((utente?.username==editText15.text.toString())&&(controllo_generale5==true))
                                {
                                    controllo = false
                                    Toast.makeText(contesto, "Warning: The username '"+editText15.text.toString()+"'\n is already used!", Toast.LENGTH_LONG).show()
                                    mediaplayer = MediaPlayer.create(contesto, R.raw.error_sound)
                                    mediaplayer?.start()
                                    controllo_barra=false
                                }
                            }
                        }
                    }
                    if((controllo)&&(controllo_generale3==true)&&(controllo_generale5==true))
                    {
                        val dati = arrayOf(editText15.text.toString(), editText14.text.toString(), "", "", selezione_spinner, "", "")
                        referenza_database = FirebaseDatabase.getInstance().getReference("Users")
                        Id_Utente = referenza_database.push().key.toString()
                        utente = Utente(Id_Utente, dati[0], dati[1], dati[2], dati[3], dati[4], dati[5], dati[6])
                        controllo_generale5=false
                        controllo_generale6=false
                        referenza_database.child(Id_Utente).setValue(utente).addOnCompleteListener {
                            ricorda_file_username.writeText(utente?.username.toString(),Charsets.UTF_8)
                            ricorda_file_password.writeText(utente?.password.toString(),Charsets.UTF_8)
                            if(stato_switcher)
                                ricorda_stato_switcher.writeText("1",Charsets.UTF_8)
                            else
                                ricorda_stato_switcher.writeText("0",Charsets.UTF_8)
                            file_controllo_numero_iscritti.writeText((file_controllo_numero_iscritti.readText(Charsets.UTF_8).toInt()+1).toString(),Charsets.UTF_8)
                            utente_loggato=utente?.username
                            val next = Intent(contesto, MainActivity::class.java)
                            next.putExtra("Id_Utente", Id_Utente)
                            settaggio(0)
                            startActivity(next)
                            Toast.makeText(contesto, editText15.text.toString()+"\nhas been successfully added!", Toast.LENGTH_LONG).show()
                            mediaplayer = MediaPlayer.create(contesto, R.raw.move_sound)
                            mediaplayer?.start()
                            controllo_barra = false
                        }
                    }
                }
            })
        }
    private fun controllo_numero_iscritti():Boolean
    {
        if(file_controllo_numero_iscritti.exists())
        {
            if(file_controllo_numero_iscritti.readText(Charsets.UTF_8).toInt() < 10)
                return true
            else
            {
                Toast.makeText(this, "Warning: It is forbidden\nadding other new users!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
                return false
            }
        }
        else
        {
            file_controllo_numero_iscritti.writeText("0",Charsets.UTF_8)
            return true
        }
    }
    private fun avvertimento()
    {
        var messaggio:String?=null
        var suono:Int?=null
        var numero:Int?=null
        if(!main_directory.exists())
            main_directory.mkdir()
        if(!file_controllo_numero_iscritti.exists())
        {
            numero=0
            file_controllo_numero_iscritti.writeText(numero.toString(), Charsets.UTF_8)
        }
        else
            numero = file_controllo_numero_iscritti.readText(Charsets.UTF_8).toInt()
        if(numero==0)
        {
            messaggio="It is possible adding\nnot more than 10 new users!"
            suono=R.raw.move_sound
        }
        else
        {
            if(numero<7)
            {
                messaggio="It is possible adding\nother "+(10-numero).toString()+" new users!"
                suono=R.raw.move_home_sound
            }
            else
                if(numero<10)
                {
                    messaggio="Attention: It is possible adding only\nother "+(10-numero).toString()+" new users!"
                    suono=R.raw.return_graph_sound
                }
                 else
                {
                    messaggio="Warning: It is not possible\nadding other new users!"
                    suono=R.raw.error_sound
                }
        }
        Toast.makeText(this,messaggio,Toast.LENGTH_LONG).show()
        mediaplayer = MediaPlayer.create(this, suono)
        mediaplayer?.start()
    }
    private fun mode_switcher_on()
    {
        if(ricorda_file_username.exists()&&ricorda_file_password.exists())
        {
            editText15.setText(ricorda_file_username.readText(Charsets.UTF_8))
            editText15.setSelection(editText15.text.lastIndex + 1)
            editText14.setText(ricorda_file_password.readText(Charsets.UTF_8))
            editText14.setSelection(editText14.text.lastIndex + 1)
            if(controllo_interno)
            {
                Toast.makeText(this, "I remember you c:", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
            }
            stato_switcher=true
            switcher.setChecked(true)
        }
    }
    private fun Show_Exit_Message()
    {
        button24.setEnabled(false)
        button25.setEnabled(false)
        button27.setEnabled(false)
        button28.setEnabled(false)
        editText15.setEnabled(false)
        editText14.setEnabled(false)
        editText16.setEnabled(false)
        spinner?.setEnabled(false)
        switcher.setEnabled(false)
        val bundle=Bundle()
        bundle.putInt("tipo",1)
        val manager=supportFragmentManager
        val transaction=manager.beginTransaction()
        val fragment=Fragment_Quit_Application()
        fragment.setArguments(bundle)
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        mediaplayer = MediaPlayer.create(this, R.raw.move_graph_sound)
        mediaplayer?.start()
    }
}
