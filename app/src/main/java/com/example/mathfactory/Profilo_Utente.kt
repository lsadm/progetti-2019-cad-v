package com.example.mathfactory
import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
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
import android.view.*
import android.widget.*
import com.example.mathfactory.com.example.mathfactory.Check_Network
import com.example.mathfactory.com.example.mathfactory.Utente
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_profilo__utente.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
var controllo_generale7:Boolean?=null
class Profilo_Utente : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val user_directory=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato)
    val ricorda_file_username=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/MathView_Reflesh_Parameters/Reflesh_Parameter1.txt")
    val ricorda_file_password=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/MathView_Reflesh_Parameters/Reflesh_Parameter2.txt")
    val dimensione_array_di_byte=File(Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Parameters/Parameter4.txt")
    var controllo_barra=false
    lateinit var referenza_database:DatabaseReference
    lateinit var outputStream:ByteArrayOutputStream
    lateinit var inputStream:ByteArrayInputStream
    var imm:Bitmap?=null
    var array_di_bytes:ByteArray?=null
    var utente:Utente?=null
    var Id_Utente:String=""
    val PERMISSION_REQUEST_CODE_2=0
    var controllo=true
    val PERMISSION_REQUEST_CODE= 101
    val CAMERA_REQUEST_CODE=0
    val GALLERY_REQUEST_CODE=1
    var gestione_uscita_cancellazione:Boolean=true
    var uscita_cancellazione:Boolean?=null
    private var mediaplayer: MediaPlayer? = null
    var size:Long?=null
    var controllo_cancellazione=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilo__utente)
        nav_view_PU.setNavigationItemSelectedListener(this)
        if(dimensione_array_di_byte.exists())
            size = dimensione_array_di_byte.readText(Charsets.UTF_8).toLong()
        else
        {
            size = 0.toLong()
            dimensione_array_di_byte.writeText(size.toString(),Charsets.UTF_8)
        }
        controllo_generale7=true
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
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
        class MyHandler constructor(val contesto:Context): Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                if(valore=="check_connection")
                    Toast.makeText(contesto, "                     Warning!\nCheck your Internet's connection.", Toast.LENGTH_LONG).show()
                else
                    if(valore=="visible")
                        progress_access.visibility=View.VISIBLE
                    else
                        if(valore=="invisible")
                            progress_access.visibility=View.INVISIBLE
                        else
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
        class ConnectionThread constructor(val handler:Handler,val contesto:Context):Thread()
        {
            val controllo_connessione = Check_Network()
            override fun run()
            {
                while(controllo_generale7==true)
                {
                    if(!controllo_connessione.Network_Disponibile(contesto)) {
                        notify_message("check_connection")
                        sleep(500)
                        mediaplayer = MediaPlayer.create(contesto, R.raw.error_sound)
                        mediaplayer?.start()
                        sleep(10000)
                    }
                    else
                        sleep(10000)
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
                        sleep(50)
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
        val myHandler=MyHandler(this)
        val checkThread=CheckThread(myHandler)
        val connectionThread=ConnectionThread(myHandler,this)
        val progressBarThread=ProgressBarThread(myHandler)
        checkThread.start()
        connectionThread.start()
        progressBarThread.start()
        button26.setOnClickListener{if(checkPermission()&&checkPermission2())go_to_camera()else{if(!checkPermission())requestPermission();if(!checkPermission2())requestPermission2()}}
        button51.setOnClickListener {if(controllo){editText26.inputType=InputType.TYPE_CLASS_TEXT;editText26.setSelection(editText26.text.lastIndex+1);button51.setBackgroundResource(R.mipmap.imm18);controllo=false}else{editText26.inputType= InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD;editText26.setSelection(editText26.text.lastIndex+1);button51.setBackgroundResource(R.mipmap.imm17);controllo=true}}
        button29.setOnClickListener {if(gestione_uscita_cancellazione){setta_uscita(true);uscita_cancellazione=false;button29.setBackgroundResource(R.mipmap.imm39)}}
        button50.setOnClickListener {setta_uscita(false)}
        button23.setOnClickListener {if(controllo_cancellazione){Cancellazione_Immagini_FireBase_Storage();controllo_cancellazione=false}else{if(array_di_bytes==null)size=0.toLong()else size=array_di_bytes?.size?.toLong();dimensione_array_di_byte.writeText(size.toString(),Charsets.UTF_8);if(size!=0.toLong())Caricamento_Immagini_FireBase_Storage()};modifica_utente()}
        button49.setOnClickListener {if(((uscita_cancellazione==true)&&(delete_account()))||(uscita_cancellazione==false)){val next = Intent(this, Start_Activity::class.java);controllo_generale2=false;controllo_generale3=false;startActivity(next);mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound);mediaplayer?.start()}}
        button54.setOnClickListener {if(editText18.text.toString()=="Male")editText18.setText("Female")else if(editText18.text.toString()=="Female")editText18.setText("Male")}
        button55.setOnClickListener {if(gestione_uscita_cancellazione){setta_uscita(true);uscita_cancellazione=true;button55.setTextColor(rgb(40,114,51))}}
        imageView.setOnClickListener {val next=Intent(this,call::class.java);next.putExtra("Id_Utente",Id_Utente);next.putExtra("immagine",array_di_bytes);next.putExtra("titolo_immagine",utente?.username+"'s\nprofile photo");next.putExtra("controllo",false);startActivity(next);mediaplayer= MediaPlayer.create(this,R.raw.move_graph_sound);mediaplayer?.start()}
        button56.setOnClickListener { controllo_cancellazione=true;imageView.setImageBitmap(null);if(editText18.text.toString()=="Male")imageView.setBackgroundResource(R.mipmap.imm12)else if(editText18.text.toString()=="Female")imageView.setBackgroundResource(R.mipmap.imm36); Toast.makeText(this, "The profile photo has been\nsuccessfully resetted!", Toast.LENGTH_LONG).show();mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound);mediaplayer?.start()}
        button57.setOnClickListener { if(checkPermission2())go_to_gallery()else requestPermission2()}
        if(size!=0.toLong())
            Scaricamento_Immagini_FireBase_Storage()
        leggi_utente()
    }
    override fun onBackPressed() {}
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
            controllo_generale7=false
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
            controllo_generale7=false
            val next = Intent(this, Function1::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_five) {
            controllo_generale7=false
            val next = Intent(this, Function2::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_six) {
            controllo_generale7=false
            val next = Intent(this, Function3::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_seven) {
            controllo_generale7=false
            val next = Intent(this, Function4::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_eight) {
            controllo_generale7=false
            val next = Intent(this, Function5::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_nine) {
            controllo_generale7=false
            val next = Intent(this, Function6::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_ten) {
            controllo_generale7=false
            val next = Intent(this, Function7::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_testuali) {
            controllo_generale7=false
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
            controllo_generale7=false
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
            controllo_generale7=false
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
            controllo_generale7=false
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
           CAMERA_REQUEST_CODE,GALLERY_REQUEST_CODE->{
               if(resultCode==Activity.RESULT_OK&&data!=null)
               {
                   outputStream= ByteArrayOutputStream()
                   if(requestCode==CAMERA_REQUEST_CODE)
                   {
                       imm = data.extras.get("data") as Bitmap
                       imm?.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
                   }
                   else
                   {
                       val imm_uri=data.data
                       imm=MediaStore.Images.Media.getBitmap(contentResolver,imm_uri)
                       imm?.compress(Bitmap.CompressFormat.JPEG,60,outputStream)
                   }
                   array_di_bytes=outputStream.toByteArray()
                   imageView.setBackgroundResource(0)
                   imageView.setImageBitmap(imm)
                   Toast.makeText(this, "The photo has been\nsuccessfully uploaded!", Toast.LENGTH_LONG).show()
                   mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                   mediaplayer?.start()
               }
               else
               {
                   Toast.makeText(this,"The image has not been acquired!",Toast.LENGTH_LONG).show()
                   mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound)
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
        if(size==0.toLong())
            controllo_barra=true
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
                if(size==0.toLong())
                    controllo_barra=false
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
        if(size==0.toLong())
        {
            if (utente?.gender == "Male")
                imageView.setBackgroundResource(R.mipmap.imm12)
            else
                if (utente?.gender == "Female")
                    imageView.setBackgroundResource(R.mipmap.imm36)
        }
    }
    private fun modifica_utente()
    {
        controllo_generale4=false
        if((editText20.text.toString()=="")||(editText26.text.toString()==""))
        {
            Toast.makeText(this, "Warning: Username or password\nmissing!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
            mediaplayer?.start()
            if(size==0.toLong())
                controllo_barra=false
            return
        }
        else
            if((editText20.text.toString().length<4)||(editText26.text.toString().length<8))
            {
                Toast.makeText(this, "Warning: Username or password\ntoo short!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
                if(size==0.toLong())
                    controllo_barra=false
                return
            }
            else
                {
                    val controllo_connessione= Check_Network()
                    if(controllo_connessione.Network_Disponibile(this))
                    {
                        if(size==0.toLong())
                            controllo_barra=true
                        val dati = arrayOf(editText20.text.toString(), editText26.text.toString(),editText25.text.toString(),editText24.text.toString(),editText18.text.toString(),editText22.text.toString(),editText21.text.toString())
                        referenza_database = FirebaseDatabase.getInstance().getReference("Users")
                        val utente =Utente(Id_Utente, dati[0], dati[1], dati[2], dati[3], dati[4], dati[5], dati[6])
                        referenza_database.child(Id_Utente).setValue(utente).addOnCompleteListener {
                            if(size==0.toLong()&&array_di_bytes==null)
                            {
                                if (editText18.text.toString() == "Male")
                                    imageView.setBackgroundResource(R.mipmap.imm12)
                                else
                                    if (editText18.text.toString() == "Female")
                                        imageView.setBackgroundResource(R.mipmap.imm36)
                            }
                            Toast.makeText(this, "The profile has been\nsuccessfully modified!", Toast.LENGTH_LONG).show()
                            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                            mediaplayer?.start()
                            if(size==0.toLong())
                               controllo_barra=false
                        }
                        return
                    }
                    else
                    {
                        Toast.makeText(this, "Warning: The server is not reachable!", Toast.LENGTH_LONG).show()
                        mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                        mediaplayer?.start()
                        if(size==0.toLong())
                            controllo_barra=false
                        return
                    }
                }
    }
    private fun delete_account():Boolean
    {
        controllo_barra=true
        val controllo_connessione= Check_Network()
        if(controllo_connessione.Network_Disponibile(this))
        {
            controllo_barra=true
            Cancellazione_Immagini_FireBase_Storage()
            if(ricorda_file_username.exists()&&ricorda_file_password.exists()&&user_directory.exists())
            {
                ricorda_file_username.delete()
                ricorda_file_password.delete()
                user_directory.deleteRecursively()
            }
            file_controllo_numero_iscritti.writeText((file_controllo_numero_iscritti.readText(Charsets.UTF_8).toInt()-1).toString(),Charsets.UTF_8)
            referenza_database = FirebaseDatabase.getInstance().getReference("Users")
            referenza_database.child(Id_Utente).removeValue()
            Toast.makeText(this, utente?.username + "\nhas been successfully deleted!", Toast.LENGTH_LONG).show()
            controllo_barra=false
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
    private fun Caricamento_Immagini_FireBase_Storage()
    {
        val controllo_connessione=Check_Network()
        if(controllo_connessione.Network_Disponibile(this))
        {
            controllo_barra = true
            val referenza_storage =
                FirebaseStorage.getInstance().getReference("/Profile_Photos/" + utente_loggato + "_Profile_Photo")
            referenza_storage.putBytes(array_di_bytes!!)
                .addOnSuccessListener {
                    imageView.setBackgroundResource(0)
                    Toast.makeText(this, "The profile photo has been\nsuccessfully uploaded!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
                    mediaplayer?.start()
                    controllo_barra = false
                }
                .addOnFailureListener {
                    imageView.setImageBitmap(null)
                    imageView.setBackgroundResource(R.mipmap.imm37)
                    Toast.makeText(this, "Warning: The profile photo has non been\nsuccessfully uploaded!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                    controllo_barra = false
                }
        }
    }
    private fun Scaricamento_Immagini_FireBase_Storage()
    {
        controllo_barra = true
        val referenza_storage = FirebaseStorage.getInstance().getReference("/Profile_Photos/" + utente_loggato + "_Profile_Photo")
        referenza_storage.getBytes(size!!)
                .addOnSuccessListener {
                    array_di_bytes = it
                    inputStream = ByteArrayInputStream(array_di_bytes)
                    imm = BitmapFactory.decodeStream(inputStream)
                    imageView.setBackgroundResource(0)
                    imageView.setImageBitmap(imm)
                    controllo_barra = false
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Warning: The profile photo has non been\nsuccessfully downloaded!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                    controllo_barra = false
                }
    }
    private fun Cancellazione_Immagini_FireBase_Storage()
    {
        val controllo_connessione=Check_Network()
        if(controllo_connessione.Network_Disponibile(this))
        {
            controllo_barra = true
            array_di_bytes = null
            size = 0.toLong()
            dimensione_array_di_byte.writeText(size.toString(), Charsets.UTF_8)
            val referenza_storage =
                FirebaseStorage.getInstance().getReference("/Profile_Photos/" + utente_loggato + "_Profile_Photo")
            referenza_storage.delete()
            controllo_barra = false
        }
    }
    private fun go_to_gallery()
    {
        val intent=Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,GALLERY_REQUEST_CODE)
        Toast.makeText(this, "The access to the archive has\nsuccessfully taken place!", Toast.LENGTH_LONG).show()
        mediaplayer = MediaPlayer.create(this, R.raw.move_graph_sound)
        mediaplayer?.start()
    }
}
