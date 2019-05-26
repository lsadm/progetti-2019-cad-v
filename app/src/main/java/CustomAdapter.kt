package com.example.mathfactory
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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