package com.example.mathfactory
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_function9.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Function9 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val PERMISSION_REQUEST_CODE= 101
    val CAMERA_REQUEST_CODE=0
    private var mediaplayer: MediaPlayer?=null
    var adapter2:CustomAdapter2?=null
    val formato= SimpleDateFormat("HH:mm:ss_dd/MM/yyyy")
    var data:String=""
    var imm:Bitmap?=null
    var controllo=true
    val prefisso2:String="Image_Note_"
    var nome_image:String=""
    var photoFile:File?=null
    var file_parametro2:File?=null
    var titolo2:String=""
    var counter:Int?=null
    var indice:Int?=1
    var output:String?=null
    var output_parametro2:String?=null
    var output_data:String?=null
    var file_data:File?=null
    val prefisso_data:String="Date_Image_Note_"
    var nome_data_image:String?=null
    var Id_Utente:String?=null
    lateinit var users2:ArrayList<User2>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function9)
        nav_view9.setNavigationItemSelectedListener(this)
        Id_Utente=getIntent().getExtras().getString("Id_Utente")
        val recyclerView2=findViewById(R.id.recyclerView2)as RecyclerView
        recyclerView2.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        users2= ArrayList<User2>()
        output_parametro2=Environment.getExternalStorageDirectory().absolutePath+"/MathView/"+ utente_loggato+"/MathView_Parameters/Parameter2.txt"
        output=Environment.getExternalStorageDirectory().absolutePath+"/MathView/"+ utente_loggato+"/MathView_Image/"
        output_data=Environment.getExternalStorageDirectory().absolutePath+"/MathView/"+ utente_loggato+"/MathView_Date_Image_Note/"
        file_parametro2=File(output_parametro2)
        if(file_parametro2?.exists()==true)
            counter=file_parametro2?.readText(Charsets.UTF_8)?.toInt()?.plus(1)
        else
            counter=1
        if(counter!=1)
        {
            while (indice != counter)
            {
                nome_image =prefisso2+ indice?.toString() + ".jpg"
                nome_data_image=output_data+prefisso_data+indice?.toString()+".txt"
                photoFile = File(output,nome_image)
                file_data=File(nome_data_image)
                if((photoFile?.exists()==true)&&(file_data?.exists()==true))
                {
                    users2.add(User2(BitmapFactory.decodeFile(photoFile?.getAbsolutePath()), "Upload time and date:\n" + file_data?.readText(Charsets.UTF_8), this, prefisso2 + indice?.toString(), Id_Utente))
                }
                indice=indice?.plus(1)
            }
            adapter2=CustomAdapter2(users2)
            recyclerView2.adapter=adapter2
        }
        val toolbar=findViewById(R.id.toolbar)as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
        val drawer=findViewById(R.id.drawer_layout)as DrawerLayout
        val toogle= ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        if(checkPermission())
            button53.setBackgroundResource(R.mipmap.imm30_foreground)
        textView55.text="No photo is ready to\nupload!"
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                button53.setBackgroundResource(R.mipmap.imm30_foreground)
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
        button53.setOnClickListener { if(checkPermission())photoFile=go_to_camera()else requestPermission()}
        button21.setOnClickListener{
            if(imm!=null)
            {
                data=formato.format(Date()).toString()
                titolo2=prefisso2+counter.toString()
                nome_data_image=output_data+prefisso_data+counter?.toString()+".txt"
                file_data=File(nome_data_image)
                users2.add(User2(imm,"Upload time and date:\n"+data,this,titolo2,Id_Utente))
                file_data?.writeText(data,Charsets.UTF_8)
                file_parametro2?.writeText(counter.toString(),Charsets.UTF_8)
                counter=counter?.plus(1)
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                imm=null
                textView55.setTextColor(rgb(155,17,30))
                textView55.text="No photo is ready to\nupload!"
                Toast.makeText(this,"The image has been\nsuccessfully added!", Toast.LENGTH_LONG).show()
                adapter2=CustomAdapter2(users2)
                recyclerView2.adapter=adapter2
                controllo=false
            }
            else
            {
                Toast.makeText(this, "You can't add an empty image!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
        button52.setOnClickListener {
            if(users2.size!=0)
            {
                users2.clear()
                cancellazione_image()
                counter=1
                Toast.makeText(this, "Image Note has been\nsuccessfully cleaned up!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.return_graph_sound)
                mediaplayer?.start()
                adapter2 = CustomAdapter2(users2)
                recyclerView2.adapter = adapter2
            }
            else
                if(controllo)
                {
                    Toast.makeText(this, "Image Note is still empty!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                }
                else
                {
                    Toast.makeText(this,"Image Note has already\nbeen cleaned up!", Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                    mediaplayer?.start()
                }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu):Boolean
    {
        menuInflater.inflate(R.menu.menu_application2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        val id=item.getItemId()
        if(id==R.id.action_one)
        {
            Toast.makeText(this,"MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if(id==R.id.action_two)
        {
            Toast.makeText(this,"Hi, I am your own\nImage Note!", Toast.LENGTH_LONG).show()
            return true
        }
        if(id==R.id.action_three)
        {
            Toast.makeText(this,"Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if(id==R.id.action_home)
        {
            val next= Intent(this,MainActivity::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_home_sound)
            mediaplayer?.start()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        val id = item.getItemId()
        if (id == R.id.action_four) {
            val next = Intent(this, Function1::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_immagini) {
            Toast.makeText(this,"You are already here!", Toast.LENGTH_LONG).show()
            mediaplayer= MediaPlayer.create(this,R.raw.error_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_six) {
            val next = Intent(this, Function3::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_seven) {
            val next = Intent(this, Function4::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_eight) {
            val next = Intent(this, Function5::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_nine) {
            val next = Intent(this, Function6::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.action_ten) {
            val next = Intent(this, Function7::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.note_testuali) {
            val next = Intent(this, Function8::class.java)
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
        if (id == R.id.note_audio) {
            val next = Intent(this, Function10::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer = MediaPlayer.create(this, R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.Profile) {
            val next = Intent(this, Profilo_Utente::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
            mediaplayer?.start()
            return true
        }
        if (id == R.id.calculator) {
            val next = Intent(this, Function0::class.java)
            next.putExtra("Id_Utente",Id_Utente)
            startActivity(next)
            mediaplayer= MediaPlayer.create(this,R.raw.move_sound)
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
            CAMERA_REQUEST_CODE->
            {
                if(resultCode== Activity.RESULT_OK)
                {
                    imm = BitmapFactory.decodeFile(photoFile?.getAbsolutePath())
                    textView55.setTextColor(rgb(40,114,51))
                    textView55.text="The photo is ready to\nupload!"
                    Toast.makeText(this,"The image has been acquired!",Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound)
                    mediaplayer?.start()
                }
                else
                {
                    Toast.makeText(this,"The image has not been acquired!",Toast.LENGTH_LONG).show()
                    mediaplayer = MediaPlayer.create(this, R.raw.move_home_sound)
                    mediaplayer?.start()
                }
            }
            else->
            {
                Toast.makeText(this,"Ops... Something is gone wrong!",Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
    }
    private fun checkPermission():Boolean
    {
        val permesso= ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
        return permesso
    }
    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),PERMISSION_REQUEST_CODE)
    }
    private fun go_to_camera():File
    {
        if(users2.size==0)
            counter=1
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        nome_image=prefisso2+counter.toString()+".jpg"
        val photoFile=File(output,nome_image)
        val fileProvider=FileProvider.getUriForFile(this,"com.mydomain.fileprovider",photoFile)
        callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider)
        if (callCameraIntent.resolveActivity(packageManager) != null)
        {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            Toast.makeText(this, "The camera has been\nsuccessfully activated!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(this, R.raw.move_graph_sound)
            mediaplayer?.start()
        }
        return photoFile
    }
    private fun cancellazione_image()
    {
        indice=1
        while (indice != counter)
        {
            nome_image = output + prefisso2 + indice?.toString() + ".jpg"
            photoFile = File(nome_image)
            nome_data_image=output_data+prefisso_data+indice?.toString()+".txt"
            file_data=File(nome_data_image)
            if((photoFile?.exists()==true)&&(file_data?.exists()==true))
            {
                photoFile?.delete()
                file_data?.delete()
            }
            indice=indice?.plus(1)
        }
        file_parametro2=File(output_parametro2)
        file_parametro2?.delete()
    }
}
