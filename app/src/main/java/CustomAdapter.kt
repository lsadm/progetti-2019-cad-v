package com.example.mathfactory
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
class CustomAdapter(val userList:ArrayList<User>):RecyclerView.Adapter<CustomAdapter.ViewHolder>()
{
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
      val user:User=userList[p1]
        p0.textViewTesto.text=user.testo
        p0.textViewOrario_Data.text=user.orario_data
        p0.titolo1.text=user.titolo1
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
  {
      val textViewTesto=itemView.findViewById(R.id.textViewTesto)as TextView
      val textViewOrario_Data=itemView.findViewById(R.id.textViewOrario_Data)as TextView
      val titolo1=itemView.findViewById(R.id.titolo1)as TextView
  }
}
class CustomAdapter2(val userList:ArrayList<User2>):RecyclerView.Adapter<CustomAdapter2.ViewHolder>()
{
    var mediaplayer:MediaPlayer?=null
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
        val user2:User2=userList[p1]
        val opzioni:Bundle?=null
        p0.imageViewData.setImageBitmap(user2.immagine)
        p0.imageViewData.setOnClickListener{
            val next=Intent(user2.contesto,call::class.java)
            val stream=ByteArrayOutputStream()
            user2.immagine?.compress(Bitmap.CompressFormat.JPEG,45,stream)
            val image=stream.toByteArray()
            next.putExtra("immagine",image)
            next.putExtra("titolo_immagine",user2.titolo2)
            startActivity(user2.contesto,next,opzioni)
            mediaplayer= MediaPlayer.create(user2.contesto,R.raw.move_graph_sound)
            mediaplayer?.start()}
        p0.textViewOrario_Data2.text=user2.orario_data2
        p0.titolo2.text=user2.titolo2
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val imageViewData=itemView.findViewById(R.id.imageViewData)as ImageView
        val textViewOrario_Data2=itemView.findViewById(R.id.textViewOrario_Data2)as TextView
        val titolo2=itemView.findViewById(R.id.titolo2)as TextView
    }
}
class CustomAdapter3(val userList:ArrayList<User3>):RecyclerView.Adapter<CustomAdapter3.ViewHolder>()
{
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
        var mediaplayer: MediaPlayer? = null
        var riproduci_pausa=true
        val user3:User3=userList[p1]
        var controllo=true
        class MyHandler:Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
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
        p0.riproduci_ferma.setOnClickListener{
            if(riproduci_pausa)
            {
                p0.riproduci_ferma.setBackgroundResource(R.mipmap.imm35_foreground)
                riproduci_pausa=false
                mediaplayer=MediaPlayer().apply{
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
                p0.riproduci_ferma.setBackgroundResource(R.mipmap.imm34_foreground)
                riproduci_pausa=true
                mediaplayer?.release()
                mediaplayer=null
            }
        }
        p0.progress_audio.progress=0
        p0.progress_audio.setMax(100)
        p0.progress_audio.setOnTouchListener { v, event ->true  }
        p0.textViewOrario_Data3.text=user3.orario_data3
        p0.timer.text="Duration: "+user3.scorri.toString()+" s"
        p0.titolo3.text=user3.titolo3
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        var riproduci_ferma=itemView.findViewById(R.id.riproduci_ferma)as Button
        var progress_audio=itemView.findViewById(R.id.progress_audio)as SeekBar
        val textViewOrario_Data3=itemView.findViewById(R.id.textViewOrario_Data3)as TextView
        val timer=itemView.findViewById(R.id.timer)as TextView
        val titolo3=itemView.findViewById(R.id.titolo3)as TextView
    }
}