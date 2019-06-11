package com.example.mathfactory.com.example.mathfactory
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat.finishAffinity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mathfactory.R
import com.example.mathfactory.Start_Activity
import com.example.mathfactory.controllo_generale8
import com.example.mathfactory.controllo_generale9
import kotlinx.android.synthetic.main.fragment_quit_application.*
import kotlinx.android.synthetic.main.fragment_quit_application.view.*
import kotlin.system.exitProcess

class Fragment_Quit_Application:Fragment()
{
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
        val next= Intent(getActivity(),Start_Activity::class.java)
        controllo_generale9=false
        view.ritorno.setOnClickListener{controllo_generale8=false;controllo_generale9=true;startActivity(next)}
        view.esci.setOnClickListener{controllo_generale8=true;startActivity(next)}
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
