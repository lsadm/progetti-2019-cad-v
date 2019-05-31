package com.example.mathfactory
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function10.*
import kotlinx.android.synthetic.main.list_layout3.*
import java.io.File
import java.io.IOException
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
class Function10 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val PERMISSION_REQUEST_CODE=0
    private var mediaplayer: MediaPlayer? = null
    private var output:String?=null
    var output_parametro3:String?=null
    var output_durata:String?=null
    var output_data:String?=null
    private var nome_audio:String?=null
    private var nome_durata:String?=null
    private var mediarecorder:MediaRecorder?=null
    private var registra_ferma:Boolean=true
    var adapter3:CustomAdapter3?=null
    val formato= SimpleDateFormat("HH:mm:ss_dd/MM/yyyy")
    var data:String=""
    var controllo:Boolean=false
    var controllo2:Boolean=true
    var controllo3:Boolean=true
    var counter:Int?=null
    var indice:Int?=1
    var durata_registrazione:Int=0
    var istante_iniziale_registrazione:Long=0
    var istante_finale_registrazione:Long=0
    val prefisso3:String="Audio_Note_"
    val prefisso_durata="Durata_Audio_Note_"
    var titolo3:String=""
    var file_parametro3:File?=null
    var duratafile:File?=null
    var audioFile:File?=null
    var file_data:File?=null
    val prefisso_data:String="Date_Audio_Note_"
    var nome_data_audio:String?=null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function10)
        nav_view10.setNavigationItemSelectedListener(this)
        val recyclerView3=findViewById(R.id.recyclerView3)as RecyclerView
        recyclerView3.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val users3= ArrayList<User3>()
        output_parametro3=Environment.getExternalStorageDirectory().absolutePath+"/MathView/MathView_Parametri/Parametro3.txt"
        output=Environment.getExternalStorageDirectory().absolutePath+"/MathView/MathView_Audio/"
        output_durata=Environment.getExternalStorageDirectory().absolutePath+"/MathView/MathView_Duration_Audio_Note/"
        output_data=Environment.getExternalStorageDirectory().absolutePath+"/MathView/MathView_Date_Audio_Note/"
        file_parametro3=File(output_parametro3)
        if(file_parametro3?.exists()==true)
            counter=file_parametro3?.readText(Charsets.UTF_8)?.toInt()?.plus(1)
        else
            counter=1
        if(counter!=1)
        {
            while (indice != counter)
            {
                nome_data_audio=output_data+prefisso_data+indice?.toString()+".txt"
                nome_audio =output+prefisso3+ indice?.toString() + ".mp3"
                nome_durata=output_durata+prefisso_durata+indice?.toString()+".txt"
                duratafile = File(nome_durata)
                file_data=File(nome_data_audio)
                users3.add(User3(nome_audio, duratafile?.readText(Charsets.UTF_8)?.toInt(),"Upload time and date---> "+file_data?.readText(Charsets.UTF_8),prefisso3 + indice?.toString()))
                indice=indice?.plus(1)
            }
            adapter3=CustomAdapter3(users3)
            recyclerView3.adapter=adapter3
        }
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                textView55.setTextColor(Color.BLUE)
                textView55.text=valore
            }
        }
        class TimerThread constructor(val handler: Handler):Thread()
        {
            var contatore:Int=0
            override fun run()
            {
                contatore=0
                while(!registra_ferma)
                {
                    notify_message("Recordering...\nDuration: "+contatore.toString()+" s")
                    contatore++
                    sleep(1000)
                }
                sleep(500)
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
        if(checkPermission())
            button53.setBackgroundResource(R.mipmap.imm32_foreground)
        textView55.setTextColor(rgb(155,17,30))
        textView55.text="No audio is ready to\nupload!"
        val handler=MyHandler()
        val timer=TimerThread(handler)
        button53.setOnClickListener {
          if(checkPermission())
              if(registra_ferma)
              {
                  nome_audio=output+prefisso3+counter.toString()+".mp3"
                  controllo3=true
                  mediaplayer = MediaPlayer.create(this, R.raw.move_graph_sound)
                  mediaplayer?.start()
                  startRecording()
                  if(controllo3)
                  {
                      button53.setBackgroundResource(R.mipmap.imm33_foreground)
                      registra_ferma = false
                  }
                  timer.start()
              }
            else
              {
                 if(controllo3)
                 {
                     button53.setBackgroundResource(R.mipmap.imm32_foreground)
                     registra_ferma = true
                     stopRecording()
                     textView55.setTextColor(rgb(40, 114, 51))
                     textView55.text = "The audio is ready to\nupload!"
                     controllo = true
                 }
              }
            else
              requestPermission()
        }
        button21.setOnClickListener {
            if(controllo)
            {
                durata_registrazione=(istante_finale_registrazione-istante_iniziale_registrazione).toInt()/1000
                if(durata_registrazione==0)
                    durata_registrazione=1
                nome_durata=output_durata+prefisso_durata+counter.toString()+".txt"
                duratafile=File(nome_durata)
                duratafile?.writeText(durata_registrazione.toString(),Charsets.UTF_8)
                data = formato.format(Date()).toString()
                nome_data_audio=output_data+prefisso_data+counter?.toString()+".txt"
                file_data=File(nome_data_audio)
                file_data?.writeText(data,Charsets.UTF_8)
                titolo3=prefisso3+counter.toString()
                users3.add(User3(nome_audio, durata_registrazione, "Upload time and date---> " + data,titolo3))
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                controllo=false
                file_parametro3=File(output_parametro3)
                file_parametro3?.writeText(counter.toString(),Charsets.UTF_8)
                counter=counter?.plus(1)
                textView55.setTextColor(rgb(155,17,30))
                textView55.text="No audio is ready to\nupload!"
                Toast.makeText(this,"The audio has been\nsuccessfully added!", Toast.LENGTH_LONG).show()
                adapter3 = CustomAdapter3(users3)
                recyclerView3.adapter = adapter3
                controllo2=false
            }
            else
            {
                Toast.makeText(this, "You can't add an empty audio!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
        button52.setOnClickListener {
            if(users3.size!=0)
            {
                users3.clear()
                cancellazione_audio()
                counter=0
                file_parametro3?.writeText(counter.toString(),Charsets.UTF_8)
                Toast.makeText(this, "Audio Note has been\nsuccessfully cleaned up!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                adapter3 = CustomAdapter3(users3)
                recyclerView3.adapter = adapter3
            }
            else
                if(controllo2)
                {
                    Toast.makeText(this, "Audio Note is still empty!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                }
                else
                {
                    Toast.makeText(this,"Audio Note has already\nbeen cleaned up!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                }
        }
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
            Toast.makeText(this, "Hi, I am your own\nAudio Note!", Toast.LENGTH_LONG).show()
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
        if (id == R.id.note_audio) {
            Toast.makeText(this, "You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
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
        if (id == R.id.action_five) {
            val next = Intent(this, Function2::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.Profile) {
            val next = Intent(this, Profilo_Utente::class.java)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
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
    private fun checkPermission():Boolean
    {
        val permesso= ContextCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
        return permesso
    }
    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),PERMISSION_REQUEST_CODE)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun startRecording()
    {
            mediarecorder= MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(nome_audio)
            try
            {
                 prepare()
            }
            catch (e: IOException)
            {
                controllo3=false
            }
                if(controllo3)
                {
                    istante_iniziale_registrazione=System.currentTimeMillis()
                    start()
                }
            }
        if(controllo3)
            Toast.makeText(this, "The recorder has been\nsuccessfully activated!", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "The recorder has some problems!", Toast.LENGTH_LONG).show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun stopRecording()
    {
        mediarecorder?.apply{
            stop()
            istante_finale_registrazione= System.currentTimeMillis()
            release()
        }
        mediarecorder=null
        Toast.makeText(this, "The registration has been successfully acquired!", Toast.LENGTH_LONG).show()
    }
    private fun cancellazione_audio()
    {
        indice=1
        while (indice != counter)
        {
            nome_audio = output + prefisso3 + indice?.toString() + ".mp3"
            nome_durata=output_durata+prefisso_durata+indice?.toString()+".txt"
            nome_data_audio=output_data+prefisso_data+indice?.toString()+".txt"
            file_data=File(nome_data_audio)
            duratafile=File(nome_durata)
            audioFile = File(nome_audio)
            audioFile?.delete()
            file_data?.delete()
            duratafile?.delete()
            indice=indice?.plus(1)
        }
    }
}
