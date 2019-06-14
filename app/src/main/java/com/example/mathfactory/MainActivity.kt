package com.example.mathfactory
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import com.example.mathfactory.com.example.mathfactory.Fragment_Clock
import com.example.mathfactory.com.example.mathfactory.contesto_Fragment_Clock
import com.example.mathfactory.com.example.mathfactory.regolatore_clock
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_clock.*
import java.io.File
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*
var controllo_generale:Int=0
class MainActivity : AppCompatActivity()
{
    var controllo_clock=true
    var controllo_barra=false
    var Id_Utente:String=""
    val PERMISSION_REQUEST_CODE=0
    private var mediaplayer:MediaPlayer?=null
    val prefisso:String=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato
    val suffissi=arrayOf<String>("","/MathView_Parameters","/MathView_Text","/MathView_Date_Text_Note","/MathView_Image","/MathView_Date_Image_Note","/MathView_Audio","/MathView_Duration_Audio_Note","/MathView_Date_Audio_Note","/MathView_Text_Note_Title","/MathView_Image_Note_Title","/MathView_Audio_Note_Title")
    var paths=arrayOf<String?>(null,null,null,null,null,null,null,null,null,null,null,null)
    var directories=arrayOf<File?>(null,null,null,null,null,null,null,null,null,null,null,null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contesto_Fragment_Clock=this
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        if(controllo_archivio==true)
        {
            for (i in 0..11)
            {
                paths[i] = prefisso + suffissi[i]
                directories[i] = File(paths[i])
                if (directories[i]?.exists() == false)
                    directories[i]?.mkdir()
            }
            controllo_archivio=false
        }
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val formato1=SimpleDateFormat("HH:mm:ss")
                val formato2=SimpleDateFormat("dd/MM/yyyy")
                var ora:String=""
                var data:String=""
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                if(valore=="visible")
                    progress_access.visibility= View.VISIBLE
                else
                    if(valore=="invisible")
                        progress_access.visibility=View.INVISIBLE
                    else
                        if(valore=="setTime")
                        {
                            ora=formato1.format(Date()).toString()
                            data=formato2.format(Date()).toString()
                            timerView.text=ora
                            dateView.text=data
                        }
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
        val progressBarThread=ProgressBarThread(myHandler)
        progressBarThread.start()
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle=ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        storia.setOnClickListener {if(checkPermission()){controllo_barra=true;val next = Intent(this, Function8::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start();controllo_barra=false}else requestPermission()}
        storia_per_immagini.setOnClickListener{if(checkPermission()) {controllo_barra=true; val next = Intent(this, Function9::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start();controllo_barra=false}else requestPermission()}
        storia_vocale.setOnClickListener {if(checkPermission()){controllo_barra=true; val next = Intent(this, Function10::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start();controllo_barra=false}else requestPermission()}
        calcolatrice.setOnClickListener {  val next = Intent(this, Function0::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        numeri_casuali.setOnClickListener {  val next = Intent(this, Function5::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        equazioni_lineari.setOnClickListener {  val next = Intent(this, Function2::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        equanzioni_differenziali.setOnClickListener {  val next = Intent(this, Function4::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        numeri_complessi.setOnClickListener {  val next = Intent(this, Function6::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        plot1.setOnClickListener {  val next = Intent(this, Function1::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start() }
        plot2.setOnClickListener {  val next = Intent(this, Function3::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()  }
        plot3.setOnClickListener {  val next = Intent(this, Function7::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()   }
        profilo.setOnClickListener {if(checkPermission()){controllo_generale8=null;controllo_generale9=true;val next = Intent(this, Profilo_Utente::class.java);next.putExtra("Id_Utente",Id_Utente);startActivity(next);mediaplayer=MediaPlayer.create(this,R.raw.move_sound);mediaplayer?.start()}else requestPermission()}
    }
    override fun onBackPressed() {}
    override fun onCreateOptionsMenu(menu: Menu):Boolean
    {
        menuInflater.inflate(R.menu.menu_application, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Enjoy with Math!", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_clock) {
            Access_Timer()
            return true
        }
        return super.onOptionsItemSelected(item)
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
    private fun Access_Timer()
    {
        val currentOrientation=getResources().getConfiguration().orientation
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = Fragment_Clock()
        transaction.replace(R.id.fragment_holder, fragment)
        if(controllo_clock)
        {
            regolatore_clock=true
            lock_unlock_Device_Rotation(true)
            profilo.setEnabled(false)
            calcolatrice.setEnabled(false)
            numeri_casuali.setEnabled(false)
            equazioni_lineari.setEnabled(false)
            equanzioni_differenziali.setEnabled(false)
            numeri_complessi.setEnabled(false)
            plot1.setEnabled(false)
            plot2.setEnabled(false)
            plot3.setEnabled(false)
            storia.setEnabled(false)
            storia_per_immagini.setEnabled(false)
            storia_vocale.setEnabled(false)
            if(currentOrientation== Configuration.ORIENTATION_PORTRAIT)
            {
                equazioni_lineari.visibility=View.INVISIBLE
                equanzioni_differenziali.visibility=View.INVISIBLE
                numeri_complessi.visibility=View.INVISIBLE
                plot1.visibility=View.INVISIBLE
                plot2.visibility=View.INVISIBLE
                plot3.visibility=View.INVISIBLE
            }
            else
            {
                numeri_casuali.visibility=View.INVISIBLE
                equazioni_lineari.visibility=View.INVISIBLE
                plot3.visibility=View.INVISIBLE
                storia.visibility=View.INVISIBLE
            }
            transaction.addToBackStack(null)
            transaction.commit()
            Toast.makeText(this,"Tap again the clock to\nhide the Clock_Window!",Toast.LENGTH_LONG).show()
        }
        else
        {
            lock_unlock_Device_Rotation(false)
            profilo.setEnabled(true)
            calcolatrice.setEnabled(true)
            numeri_casuali.setEnabled(true)
            equazioni_lineari.setEnabled(true)
            equanzioni_differenziali.setEnabled(true)
            numeri_complessi.setEnabled(true)
            plot1.setEnabled(true)
            plot2.setEnabled(true)
            plot3.setEnabled(true)
            storia.setEnabled(true)
            storia_per_immagini.setEnabled(true)
            storia_vocale.setEnabled(true)
            if(currentOrientation== Configuration.ORIENTATION_PORTRAIT)
            {
                equazioni_lineari.visibility=View.VISIBLE
                equanzioni_differenziali.visibility=View.VISIBLE
                numeri_complessi.visibility=View.VISIBLE
                plot1.visibility=View.VISIBLE
                plot2.visibility=View.VISIBLE
                plot3.visibility=View.VISIBLE
            }
            else
            {
                numeri_casuali.visibility=View.VISIBLE
                equazioni_lineari.visibility=View.VISIBLE
                plot3.visibility=View.VISIBLE
                storia.visibility=View.VISIBLE
            }
            regolatore_clock=false
            transaction.remove(fragment)
            transaction.commit()
        }
        controllo_clock=!controllo_clock
    }
    private fun lock_unlock_Device_Rotation(lock:Boolean)
    {
        if(lock)
        {
            val currentOrientation=getResources().getConfiguration().orientation
            if(currentOrientation== Configuration.ORIENTATION_LANDSCAPE)
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
            else
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT)
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR2)
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER)
            else
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR)
        }
    }
}
