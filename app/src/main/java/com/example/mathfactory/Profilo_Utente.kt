package com.example.mathfactory
import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.rgb
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.mathfactory.com.example.mathfactory.Check_Network
import com.example.mathfactory.com.example.mathfactory.Utente
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profilo__utente.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Profilo_Utente : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var referenza_database:DatabaseReference
    lateinit var outputStream:ByteArrayOutputStream
    var array_di_bytes:ByteArray?=null
    var utente:Utente?=null
    var Id_Utente:String=""
    val PERMISSION_REQUEST_CODE_2=0
    var controllo=true
    val PERMISSION_REQUEST_CODE= 101
    val CAMERA_REQUEST_CODE=0
    var gestione_uscita_cancellazione:Boolean=true
    var uscita_cancellazione:Boolean?=null
    private var mediaplayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilo__utente)
        nav_view_PU.setNavigationItemSelectedListener(this)
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        leggi_utente()
        editText18.setEnabled(false)
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        editText26.inputType= InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        if(checkPermission())
            button26.setBackgroundResource(R.mipmap.imm13)
        else
            button26.setBackgroundResource(R.mipmap.imm15)
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                button26.setBackgroundResource(R.mipmap.imm13)
            }
        }
        class CheckThread constructor(val handler: Handler):Thread()
        {
            override fun run()
            {
                while(!checkPermission())
                    sleep(100)
                notify_message("")
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
        button26.setOnClickListener{if(checkPermission())go_to_camera()else requestPermission()}
        button51.setOnClickListener {if(controllo){editText26.inputType=InputType.TYPE_CLASS_TEXT;editText26.setSelection(editText26.text.lastIndex+1);button51.setBackgroundResource(R.mipmap.imm18);controllo=false}else{editText26.inputType= InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD;editText26.setSelection(editText26.text.lastIndex+1);button51.setBackgroundResource(R.mipmap.imm17);controllo=true}}
        button29.setOnClickListener {if(gestione_uscita_cancellazione){setta_uscita(true);uscita_cancellazione=false;button29.setBackgroundResource(R.mipmap.imm39)}}
        button50.setOnClickListener {setta_uscita(false)}
        button23.setOnClickListener {modifica_utente()}
        button49.setOnClickListener {if(((uscita_cancellazione==true)&&(delete_account()))||(uscita_cancellazione==false)){val next = Intent(this, Start_Activity::class.java);startActivity(next);mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound);mediaplayer?.start()}}
        button54.setOnClickListener {if(editText18.text.toString()=="Male")editText18.setText("Female")else if(editText18.text.toString()=="Female")editText18.setText("Male")}
        button55.setOnClickListener {if(gestione_uscita_cancellazione){setta_uscita(true);uscita_cancellazione=true;button55.setTextColor(rgb(40,114,51))}}
        imageView.setOnClickListener { val next=Intent(this,call::class.java);next.putExtra("Id_Utente",Id_Utente);next.putExtra("immagine",array_di_bytes);next.putExtra("titolo_immagine",utente?.username+"'s\nprofile photo");next.putExtra("controllo",false);startActivity(next);mediaplayer= MediaPlayer.create(this,R.raw.move_graph_sound);mediaplayer?.start() }
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
            next.putExtra("Id_Utente",Id_Utente)
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
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_five) {
            val next = Intent(this, Function2::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_six) {
            val next = Intent(this, Function3::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_seven) {
            val next = Intent(this, Function4::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_eight) {
            val next = Intent(this, Function5::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_nine) {
            val next = Intent(this, Function6::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_ten) {
            val next = Intent(this, Function7::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_testuali) {
            if(checkPermission2())
            {
                val next = Intent(this, Function8::class.java)
                next.putExtra("Id_Utente",Id_Utente)
                startActivity(next)
                mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                mediaplayer?.start()
            }
            requestPermission2()
            return true
        }
        if (id == R.id.note_immagini) {
            if(checkPermission2())
            {
                val next = Intent(this, Function9::class.java)
                next.putExtra("Id_Utente",Id_Utente)
                startActivity(next)
                mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                mediaplayer?.start()
            }
            else requestPermission2()
            return true
        }
        if (id == R.id.note_audio) {
            if(checkPermission2())
            {
                val next = Intent(this, Function10::class.java)
                next.putExtra("Id_Utente",Id_Utente)
                startActivity(next)
                mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                mediaplayer?.start()
            }
            else
                requestPermission2()
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
            next.putExtra("Id_Utente",Id_Utente)
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
                   outputStream= ByteArrayOutputStream()
                   val imm=data.extras.get("data")as Bitmap
                   imm.compress(Bitmap.CompressFormat.JPEG,45,outputStream)
                   array_di_bytes=outputStream.toByteArray()
                   imageView.setBackgroundResource(0)
                   imageView.setImageBitmap(imm)
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
            gestione_uscita_cancellazione=false
        }
        else
        {
            textView52.visibility=View.INVISIBLE
            button50.visibility=View.INVISIBLE
            button49.visibility=View.INVISIBLE
            gestione_uscita_cancellazione=true
            if(uscita_cancellazione==true)
                button55.setTextColor(rgb(155,17,30))
            else
                if(uscita_cancellazione==false)
                    button29.setBackgroundResource(R.mipmap.imm16)
            uscita_cancellazione=null
        }
    }
    private fun checkPermission2():Boolean
    {
        val permesso= ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED&& ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
        return permesso
    }
    private fun requestPermission2()
    {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),PERMISSION_REQUEST_CODE_2)
    }
    private fun leggi_utente()
    {
        var controllo=true
        referenza_database = FirebaseDatabase.getInstance().getReference("Users")
        referenza_database.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError){}
            override fun onDataChange(p0: DataSnapshot) {
              if(p0.exists())
              {
               for(record in p0.children)
               {
                   if(controllo)
                   {
                       utente = record.getValue(Utente::class.java)
                       if(utente?.chiave==Id_Utente)
                       {
                           setta_parametri(utente)
                           controllo=false
                       }
                   }
               }
              }
            }
        })
    }
    private fun setta_parametri(utente:Utente?)
    {
        editText20.setText(utente?.username)
        editText20.setSelection(editText20.text.lastIndex+1)
        editText26.setText(utente?.password)
        editText26.setSelection(editText26.text.lastIndex+1)
        editText25.setText(utente?.name)
        editText25.setSelection(editText25.text.lastIndex+1)
        editText24.setText(utente?.surname)
        editText24.setSelection(editText24.text.lastIndex+1)
        editText18.setText(utente?.gender)
        editText22.setText(utente?.birthday)
        editText22.setSelection(editText22.text.lastIndex+1)
        editText21.setText(utente?.profession)
        editText21.setSelection(editText21.text.lastIndex+1)
        if(utente?.gender=="Male")
            imageView.setBackgroundResource(R.mipmap.imm12)
        else
            if(utente?.gender=="Female")
               imageView.setBackgroundResource(R.mipmap.imm36)
    }
    private fun modifica_utente()
    {
        if((editText20.text.toString()=="")||(editText26.text.toString()==""))
        {
            Toast.makeText(this, "Warning: Username or password\nmissing!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
            mediaplayer?.start()
            return
        }
        else
            if((editText20.text.toString().length<4)||(editText26.text.toString().length<8))
            {
                Toast.makeText(this, "Warning: Username or password\ntoo short!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
                return
            }
            else
                {
                    val controllo_connessione= Check_Network()
                    if(controllo_connessione.Network_Disponibile(this))
                    {
                        val dati = arrayOf(editText20.text.toString(), editText26.text.toString(),editText25.text.toString(),editText24.text.toString(),editText18.text.toString(),editText22.text.toString(),editText21.text.toString())
                        referenza_database = FirebaseDatabase.getInstance().getReference("Users")
                        val utente =Utente(Id_Utente, dati[0], dati[1], dati[2], dati[3], dati[4], dati[5], dati[6])
                        referenza_database.child(Id_Utente).setValue(utente).addOnCompleteListener {
                            if(editText18.text.toString()=="Male")
                                imageView.setBackgroundResource(R.mipmap.imm12)
                            else
                                if(editText18.text.toString()=="Female")
                                    imageView.setBackgroundResource(R.mipmap.imm36)
                            Toast.makeText(this, "The profile has been\nsuccessfully modified!", Toast.LENGTH_LONG).show()
                            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                            mediaplayer?.start()
                        }
                        return
                    }
                    else
                    {
                        Toast.makeText(this, "Warning: The server is not reachable!", Toast.LENGTH_LONG).show()
                        mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                        mediaplayer?.start()
                        return
                    }
                }
    }
    private fun delete_account():Boolean
    {
        val controllo_connessione= Check_Network()
        if(controllo_connessione.Network_Disponibile(this))
        {
            referenza_database = FirebaseDatabase.getInstance().getReference("Users")
            referenza_database.child(Id_Utente).removeValue()
            Toast.makeText(this, utente?.username + "\nhas been successfully deleted!", Toast.LENGTH_LONG).show()
            return true
        }
        else
        {
            Toast.makeText(this, "Warning: The server is not reachable!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
            mediaplayer?.start()
            return false
        }
    }
}
