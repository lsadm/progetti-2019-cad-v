package com.example.mathfactory.com.example.mathfactory
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mathfactory.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_clock.*
import kotlinx.android.synthetic.main.fragment_clock.view.*
import java.text.SimpleDateFormat
import java.util.*

class Fragment_Clock:Fragment()
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
        val view=inflater.inflate(R.layout.fragment_clock,container,false)
        class MyHandler: Handler()
        {
            override fun handleMessage(msg: Message)
            {
                val formato1= SimpleDateFormat("HH:mm:ss")
                val formato2= SimpleDateFormat("dd/MM/yyyy")
                var ora:String?=null
                var data:String?=null
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                ora=formato1.format(Date()).toString()
                data=formato2.format(Date()).toString()
                view.timerView.text=ora
                view.dateView.text=data
            }
        }
        class ClockThread constructor(val handler: Handler):Thread()
        {

            override fun run()
            {
                while(true)
                {
                    notify_message("setTime")
                    sleep(1000)
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
        val clockThread=ClockThread(myHandler)
        clockThread.start()
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
