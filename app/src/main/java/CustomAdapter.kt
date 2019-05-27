package com.example.mathfactory
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.IOException

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
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
  {
      val textViewTesto=itemView.findViewById(R.id.textViewTesto)as TextView
      val textViewOrario_Data=itemView.findViewById(R.id.textViewOrario_Data)as TextView
  }
}
class CustomAdapter2(val userList:ArrayList<User2>):RecyclerView.Adapter<CustomAdapter2.ViewHolder>()
{
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
        p0.imageViewData.setImageBitmap(user2.immagine)
        p0.textViewOrario_Data2.text=user2.orario_data2
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val imageViewData=itemView.findViewById(R.id.imageViewData)as ImageView
        val textViewOrario_Data2=itemView.findViewById(R.id.textViewOrario_Data2)as TextView
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
                        p0.errore.text="The selected audio is not available!"
                    }
                }
            }
            else
            {
                p0.riproduci_ferma.setBackgroundResource(R.mipmap.imm34_foreground)
                riproduci_pausa=true
                mediaplayer?.release()
                mediaplayer=null
            }
        }
        p0.progress_audio.incrementProgressBy(user3.scorri)
        p0.textViewOrario_Data3.text=user3.orario_data3
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        var riproduci_ferma=itemView.findViewById(R.id.riproduci_ferma)as Button
        var progress_audio=itemView.findViewById(R.id.progress_audio)as SeekBar
        val textViewOrario_Data3=itemView.findViewById(R.id.textViewOrario_Data3)as TextView
        val errore=itemView.findViewById(R.id.errore)as TextView
    }
}