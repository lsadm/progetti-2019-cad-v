package com.example.mathfactory
import android.content.Context
import android.graphics.Bitmap
import android.widget.Button
import android.widget.SeekBar

data class User(val testo:String?,val orario_data:String,val titolo1:String,val contesto:Context,val title1:String,val index:Int)
data class User2(val immagine:Bitmap?,val orario_data2:String,val contesto:Context,val titolo2:String,val Id_Utente:String?,val title2:String,val index:Int)
data class User3(val path:String?, var scorri:Int?,val orario_data3:String,val titolo3:String,val contesto:Context,val title3:String,val index:Int)