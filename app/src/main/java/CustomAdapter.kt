package com.example.mathfactory
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.*
var controllo_generale10:Boolean?=null
var controllo_generale11:Boolean?=null
val SHARING_REQUEST_CODE=2
var mediaplayer_CustomerAdapter3: MediaPlayer? = null
class CustomAdapter(val userList:ArrayList<User>):RecyclerView.Adapter<CustomAdapter.ViewHolder>()
{
    var mediaplayer:MediaPlayer?=null
    val path_title1=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Text_Note_Title/Text_Note_Title_"
    var file_title1:File?=null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder
    {
      val v=LayoutInflater.from(p0.context).inflate(R.layout.list_layout,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int
    {
      return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int)
    {
        val output= Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato+"/MathView_Text/"
        val output_data= Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato+"/MathView_Date_Text_Note/"
        var file_text: File?=null
        var file_data_text:File?=null
        val user:User=userList[p1]
        class MyHandler:Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                if(valore=="change")
                    p0.share1.setBackgroundResource(R.mipmap.imm48_foreground)
                else
                    if(valore=="change2")
                        p0.share1.setBackgroundResource(R.mipmap.imm49_foreground)
                    else
                        if(valore=="error_change")
                            p0.share1.setBackgroundResource(R.mipmap.imm50_foreground)
            }
        }
        val myHandler=MyHandler()
        val changeColorThread=ChangeColorThread(myHandler)
        p0.textViewTesto.text=user.testo
        p0.textViewOrario_Data.text=user.orario_data
        p0.titolo1.setText(user.title1)
        p0.titolo1.setSelection(p0.titolo1.text.lastIndex+1)
        val position_title=p0.getAdapterPosition()+1
        file_title1=File(path_title1+position_title.toString()+".txt")
        file_title1?.writeText(user.title1,Charsets.UTF_8)
        p0.cancella1.setOnClickListener {
            val path1=output+user.titolo1+".txt"
            val path_data1=output_data+"Date_"+user.titolo1+".txt"
            file_text=File(path1)
            file_data_text=File(path_data1)
            file_text?.delete()
            file_data_text?.delete()
            file_title1?.delete()
            Toast.makeText(user.contesto, user.titolo1+" has been\nsuccessfully deleted!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(user.contesto, R.raw.return_graph_sound)
            mediaplayer?.start()
            val position= p0.getAdapterPosition()
            userList.removeAt(position)
            notifyItemRemoved(position)
        }
        p0.share1.setOnClickListener {
            val path1=output+user.titolo1+".txt"
            file_text=File(path1)
            share(file_title1?.readText(Charsets.UTF_8),file_text,1,user.contesto,null,changeColorThread)
        }
        p0.modifica1.setOnClickListener {
            if(p0.titolo1.text.toString()!="")
            {
                file_title1?.writeText(p0.titolo1.text.toString(),Charsets.UTF_8)
                Toast.makeText(user.contesto,"The title: '"+p0.titolo1.text.toString()+"'\nhas been successfully setted!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(user.contesto, R.raw.return_graph_sound)
                mediaplayer?.start()
            }
            else
            {
                Toast.makeText(user.contesto,"Warning: You can't set an empty title!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(user.contesto, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
  {
      val textViewTesto=itemView.findViewById(R.id.textViewTesto)as TextView
      val textViewOrario_Data=itemView.findViewById(R.id.textViewOrario_Data)as TextView
      val titolo1=itemView.findViewById(R.id.titolo1)as EditText
      val cancella1=itemView.findViewById(R.id.cancella1)as Button
      val share1=itemView.findViewById(R.id.share1)as Button
      val modifica1=itemView.findViewById(R.id.modifica1)as Button
  }
}
class CustomAdapter2(val userList:ArrayList<User2>):RecyclerView.Adapter<CustomAdapter2.ViewHolder>()
{
    var mediaplayer:MediaPlayer?=null
    val path_title2=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Image_Note_Title/Image_Note_Title_"
    var file_title2:File?=null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder
    {
        val v=LayoutInflater.from(p0.context).inflate(R.layout.list_layout2,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int
    {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int)
    {
        val output= Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato+"/MathView_Image/"
        val output_data= Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato+"/MathView_Date_Image_Note/"
        var file_image: File?=null
        var file_data_image:File?=null
        val user2:User2=userList[p1]
        val opzioni:Bundle?=null
        var controllo_barra=false
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                if(valore=="visible")
                    p0.progress_immage.visibility=View.VISIBLE
                else
                    if(valore=="invisible")
                        p0.progress_immage.visibility=View.INVISIBLE
                    else
                        if(valore=="change")
                            p0.share2.setBackgroundResource(R.mipmap.imm48_foreground)
                        else
                            if(valore=="change2")
                                p0.share2.setBackgroundResource(R.mipmap.imm49_foreground)
                            else
                                if(valore=="error_change")
                                    p0.share2.setBackgroundResource(R.mipmap.imm50_foreground)
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
                        while (p0.progress_immage.progress < 100)
                        {
                            p0.progress_immage.incrementProgressBy(1)
                            sleep(10)
                        }
                        p0.progress_immage.progress = 0
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
        val changeColorThread=ChangeColorThread(myHandler)
        progressBarThread.start()
        p0.imageViewData.setImageBitmap(user2.immagine)
        p0.imageViewData.setOnClickListener{
            controllo_barra=true
            val next=Intent(user2.contesto,call::class.java)
            val stream=ByteArrayOutputStream()
            user2.immagine?.compress(Bitmap.CompressFormat.JPEG,30,stream)
            val image=stream.toByteArray()
            next.putExtra("Id_Utente",user2.Id_Utente)
            next.putExtra("immagine",image)
            next.putExtra("titolo_immagine",user2.titolo2)
            next.putExtra("controllo",true)
            startActivity(user2.contesto,next,opzioni)
            mediaplayer= MediaPlayer.create(user2.contesto,R.raw.move_graph_sound)
            mediaplayer?.start()
            controllo_barra=false}
        p0.textViewOrario_Data2.text=user2.orario_data2
        p0.titolo2.setText(user2.title2)
        p0.titolo2.setSelection(p0.titolo2.text.lastIndex+1)
        val position_title=p0.getAdapterPosition()+1
        file_title2=File(path_title2+position_title.toString()+".txt")
        file_title2?.writeText(user2.title2,Charsets.UTF_8)
        p0.titolo2.setSelection(p0.titolo2.text.lastIndex+1)
        p0.cancella2.setOnClickListener {
            val path2=output+user2.titolo2+".jpg"
            val path_data2=output_data+"Date_"+user2.titolo2+".txt"
            file_image=File(path2)
            file_data_image=File(path_data2)
            file_image?.delete()
            file_data_image?.delete()
            file_title2?.delete()
            Toast.makeText(user2.contesto, user2.titolo2+" has been\nsuccessfully deleted!", Toast.LENGTH_LONG).show()
            mediaplayer = MediaPlayer.create(user2.contesto, R.raw.return_graph_sound)
            mediaplayer?.start()
            val position= p0.getAdapterPosition()
            userList.removeAt(position)
            notifyItemRemoved(position)
        }
        p0.share2.setOnClickListener {
            val path2=output+user2.titolo2+".jpg"
            file_image=File(path2)
            share(file_title2?.readText(Charsets.UTF_8),file_image,2,user2.contesto,null,changeColorThread)
        }
        p0.modifica2.setOnClickListener {
            if(p0.titolo2.text.toString()!="")
            {
                file_title2?.writeText(p0.titolo2.text.toString(),Charsets.UTF_8)
                Toast.makeText(user2.contesto,"The title: '"+p0.titolo2.text.toString()+"'\nhas been successfully setted!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(user2.contesto, R.raw.return_graph_sound)
                mediaplayer?.start()
            }
            else
            {
                Toast.makeText(user2.contesto,"Warning: You can't set an empty title!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(user2.contesto, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val imageViewData=itemView.findViewById(R.id.imageViewData)as ImageView
        val textViewOrario_Data2=itemView.findViewById(R.id.textViewOrario_Data2)as TextView
        val titolo2=itemView.findViewById(R.id.titolo2)as EditText
        val cancella2=itemView.findViewById(R.id.cancella2)as Button
        val progress_immage=itemView.findViewById(R.id.progress_image)as ProgressBar
        val share2=itemView.findViewById(R.id.share2)as Button
        val modifica2=itemView.findViewById(R.id.modifica2)as Button
    }
}
class CustomAdapter3(val userList:ArrayList<User3>):RecyclerView.Adapter<CustomAdapter3.ViewHolder>()
{
    var mediaplayer:MediaPlayer?=null
    var controllo_riproduzione=true
    var posizione:Int?=null
    val path_title3=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+utente_loggato+"/MathView_Audio_Note_Title/Audio_Note_Title_"
    var file_title3:File?=null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder
    {
        val v=LayoutInflater.from(p0.context).inflate(R.layout.list_layout3,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int
    {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int)
    {
        val output= Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato+"/MathView_Audio/"
        val output_data= Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato+"/MathView_Date_Audio_Note/"
        val output_durata=Environment.getExternalStorageDirectory().absolutePath+"/.MathView/"+ utente_loggato+"/MathView_Duration_Audio_Note/"
        var file_audio: File?=null
        var file_data_audio:File?=null
        var file_durata_audio:File?=null
        var riproduci_pausa=true
        val user3:User3=userList[p1]
        var controllo=true
        class MyHandler:Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                if(valore=="change")
                    p0.share3.setBackgroundResource(R.mipmap.imm48_foreground)
                else
                    if(valore=="change2")
                        p0.share3.setBackgroundResource(R.mipmap.imm49_foreground)
                    else
                        if(valore=="error_change")
                            p0.share3.setBackgroundResource(R.mipmap.imm50_foreground)
                        else
                            if(valore!=R.mipmap.imm34_foreground.toString())
                            {
                                if(valore=="The selected audio is not available!")
                                    p0.timer.setTextColor(rgb(155,17,30))
                                p0.timer.text = valore
                            }
                            else
                                p0.riproduci_ferma.setBackgroundResource(valore.toInt())
            }
        }
        class TimerThread constructor(val handler:Handler):Thread()
        {
            var contatore:Int=0
            override fun run()
            {
                contatore=0
                while((p0.progress_audio.progress<100)&&(!riproduci_pausa))
                {
                    if(controllo)
                    {
                        notify_message("Remaining: "+(user3.scorri.toString().toInt()-contatore).toString()+" s")
                        contatore++
                    }
                    sleep(1000)
                    p0.progress_audio.incrementProgressBy(100 / user3.scorri.toString().toInt())
                }
                if ((p0.progress_audio.progress >= 100)||(riproduci_pausa))
                {
                    if(!riproduci_pausa)
                    {
                        riproduci_pausa = true
                        if(controllo)
                         notify_message("Remaining: 0 s")
                        sleep(500)
                        notify_message(R.mipmap.imm34_foreground.toString())
                        controllo_riproduzione=true
                    }
                    if(controllo)
                     notify_message("Duration: "+user3.scorri.toString()+" s")
                    else
                     notify_message("The selected audio is not available!")
                    p0.progress_audio.progress = 0
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
        val handler=MyHandler()
        val timer=TimerThread(handler)
        val changeColorThread=ChangeColorThread(handler)
        p0.riproduci_ferma.setOnClickListener{
            if(riproduci_pausa&&controllo_riproduzione)
            {
                controllo_riproduzione=false
                posizione=p0.getAdapterPosition()
                p0.riproduci_ferma.setBackgroundResource(R.mipmap.imm35_foreground)
                riproduci_pausa=false
                mediaplayer_CustomerAdapter3=MediaPlayer().apply{
                    try
                    {
                        setDataSource(user3.path)
                        prepare()
                        start()
                    }
                    catch (e: IOException)
                    {
                        controllo=false
                        user3.scorri=1
                    }
                }
                timer.start()
            }
            else
            {
                if (p0.getAdapterPosition() == posizione)
                {
                    p0.riproduci_ferma.setBackgroundResource(R.mipmap.imm34_foreground)
                    riproduci_pausa = true
                    mediaplayer_CustomerAdapter3?.release()
                    mediaplayer_CustomerAdapter3 = null
                    controllo_riproduzione = true
                }
            }
        }
        p0.progress_audio.progress=0
        p0.progress_audio.setMax(100)
        p0.progress_audio.setOnTouchListener { v, event ->true  }
        p0.textViewOrario_Data3.text=user3.orario_data3
        p0.timer.text="Duration: "+user3.scorri.toString()+" s"
        p0.titolo3.setText(user3.title3)
        p0.titolo3.setSelection(p0.titolo3.text.lastIndex+1)
        val position_title=p0.getAdapterPosition()+1
        file_title3=File(path_title3+position_title.toString()+".txt")
        file_title3?.writeText(user3.title3,Charsets.UTF_8)
        p0.titolo3.setSelection(p0.titolo3.text.lastIndex+1)
        p0.cancella3.setOnClickListener {
            if(!controllo_riproduzione)
            {
                riproduci_pausa = true
                mediaplayer_CustomerAdapter3?.release()
                mediaplayer_CustomerAdapter3= null
                controllo_riproduzione = true
            }
            val path3=output+user3.titolo3+".mp3"
            val path_data3=output_data+"Date_"+user3.titolo3+".txt"
            val path_durata=output_durata+"Duration_"+user3.titolo3+".txt"
            file_audio=File(path3)
            file_data_audio=File(path_data3)
            file_durata_audio=File(path_durata)
            file_audio?.delete()
            file_data_audio?.delete()
            file_durata_audio?.delete()
            file_title3?.delete()
            Toast.makeText(user3.contesto, user3.titolo3+" has been\nsuccessfully deleted!", Toast.LENGTH_LONG).show()
            mediaplayer_CustomerAdapter3 = MediaPlayer.create(user3.contesto, R.raw.return_graph_sound)
            mediaplayer_CustomerAdapter3?.start()
            val position= p0.getAdapterPosition()
            userList.removeAt(position)
            notifyItemRemoved(position)
        }
        p0.share3.setOnClickListener {
            val path3=output+user3.titolo3+".mp3"
            file_audio=File(path3)
            share(file_title3?.readText(Charsets.UTF_8),file_audio,3,user3.contesto,null,changeColorThread)
        }
        p0.modifica3.setOnClickListener {
            if(p0.titolo3.text.toString()!="")
            {
                file_title3?.writeText(p0.titolo3.text.toString(),Charsets.UTF_8)
                Toast.makeText(user3.contesto,"The title: '"+p0.titolo3.text.toString()+"'\nhas been successfully setted!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(user3.contesto, R.raw.return_graph_sound)
                mediaplayer?.start()
            }
            else
            {
                Toast.makeText(user3.contesto,"Warning: You can't set an empty title!", Toast.LENGTH_LONG).show()
                mediaplayer = MediaPlayer.create(user3.contesto, R.raw.error_sound)
                mediaplayer?.start()
            }
        }
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        var riproduci_ferma=itemView.findViewById(R.id.riproduci_ferma)as Button
        var progress_audio=itemView.findViewById(R.id.progress_audio)as SeekBar
        val textViewOrario_Data3=itemView.findViewById(R.id.textViewOrario_Data3)as TextView
        val timer=itemView.findViewById(R.id.timer)as TextView
        val titolo3=itemView.findViewById(R.id.titolo3)as EditText
        val cancella3=itemView.findViewById(R.id.cancella3)as Button
        val share3=itemView.findViewById(R.id.share3)as Button
        val modifica3=itemView.findViewById(R.id.modifica3)as Button
    }
}
private fun share(title:String?,file:File?,tipo:Int,contesto:Context,opzioni:Bundle?,changeColorThread:ChangeColorThread)
{
    changeColorThread.start()
    var mediaplayer:MediaPlayer?=null
    var titolo:String?=null
    val shareIntent=Intent(Intent.ACTION_SEND)
    when(tipo)
    {
        1->
        {
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,file?.readText(Charsets.UTF_8))
            titolo="Share the Text_Note with..."
        }
        2->
        {
            shareIntent.type = "image/jpeg"
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
            titolo="Share the Image_Note with..."
        }
        3->
        {
            shareIntent.type = "audio/mp3"
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
            titolo="Share the Audio_Note with..."
        }
    }
    shareIntent.putExtra(Intent.EXTRA_SUBJECT,title)
    startActivityForResult(contesto as Activity,Intent.createChooser(shareIntent,titolo), SHARING_REQUEST_CODE,null)
    Toast.makeText(contesto,"The sharing box has been\nsuccessfully activated!", Toast.LENGTH_LONG).show()
    mediaplayer = MediaPlayer.create(contesto, R.raw.move_graph_sound)
    mediaplayer?.start()
}
class ChangeColorThread constructor(val handler: Handler):Thread()
{
    override fun run()
    {
        while((controllo_generale10==null)||(controllo_generale11==null))
            sleep(500)
        if(controllo_generale11==true)
        {
            if (controllo_generale10 == true)
                notify_message("change")
            else
                if (controllo_generale10 == false)
                    notify_message("change2")
        }
        else
            if(controllo_generale11==false)
                notify_message("error_change")
        controllo_generale10=null
        controllo_generale11=null
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