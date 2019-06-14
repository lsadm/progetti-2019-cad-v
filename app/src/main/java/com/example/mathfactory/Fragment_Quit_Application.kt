package com.example.mathfactory.com.example.mathfactory
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.ActivityCompat.finishAffinity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mathfactory.*
import kotlinx.android.synthetic.main.fragment_quit_application.*
import kotlinx.android.synthetic.main.fragment_quit_application.view.*
import kotlin.system.exitProcess

class Fragment_Quit_Application:Fragment()
{
    var mediaplayer:MediaPlayer?=null
    override fun onAttach(context: Context?)
    {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view=inflater.inflate(R.layout.fragment_quit_application,container,false)
        val tipo=getArguments()?.getInt("tipo")
        val Id_Utente=getArguments()?.getString("Id_Utente")
        val next= Intent(getActivity(),Start_Activity::class.java)
        val next2=Intent(getActivity(),Profilo_Utente::class.java)
        controllo_generale9=false
        when(tipo)
        {
            1->
            {
                view.messaggio.text="Are you sure you\nwant to exit\nMathView?"
                view.ritorno.setOnClickListener{controllo_generale8=false;controllo_generale9=true;startActivity(next)}
                view.esci.setOnClickListener{controllo_generale8=true;startActivity(next)}
            }
            2->
            {
                view.messaggio.text="Are you sure you\nwant to Logout?"
                view.ritorno.setOnClickListener{controllo_generale8=false;controllo_generale9=true;next2.putExtra("Id_Utente",Id_Utente);startActivity(next2)}
                view.esci.setOnClickListener{controllo_generale8=null;controllo_generale9=true;controllo_generale2=false;controllo_generale3=false;startActivity(next);mediaplayer = MediaPlayer.create(context_for_fragment, R.raw.move_home_sound);mediaplayer?.start()}
            }
            3->
            {
                view.messaggio.text="Are you sure you\nwant to delete\nyour Account?"
                view.ritorno.setOnClickListener{controllo_generale8=false;controllo_generale9=true;next2.putExtra("Id_Utente",Id_Utente);startActivity(next2)}
                view.esci.setOnClickListener{controllo_generale8=true;controllo_generale9=true;controllo_generale2=false;controllo_generale3=false;next2.putExtra("Id_Utente",Id_Utente);startActivity(next2)}
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart()
    {
        super.onStart()
    }

    override fun onResume()
    {
        super.onResume()
    }

    override fun onPause()
    {
        super.onPause()
    }

    override fun onStop()
    {
        super.onStop()
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
    }

    override fun onDestroy()
    {
        super.onDestroy()
    }

    override fun onDetach()
    {
        super.onDetach()
    }
}
