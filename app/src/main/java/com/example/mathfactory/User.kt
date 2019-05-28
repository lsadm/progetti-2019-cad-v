package com.example.mathfactory
import android.graphics.Bitmap
import android.widget.Button
import android.widget.SeekBar

data class User(val testo:String,val orario_data:String)
data class User2(val immagine:Bitmap?,val orario_data2:String)
data class User3(val path:String?, var scorri:Int,val orario_data3:String)