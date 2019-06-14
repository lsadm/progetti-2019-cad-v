package com.example.mathfactory.com.example.mathfactory
import android.content.Context
import android.media.MediaPlayer
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
var regolatore_clock:Boolean?=null
var contesto_Fragment_Clock:Context?=null
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
                val formato_ore=SimpleDateFormat("HH")
                val formato_minuti=SimpleDateFormat("mm")
                val formato_secondi=SimpleDateFormat("ss")
                var ora:String?=null
                var data:String?=null
                val bundle:Bundle=msg.getData()
                val valore:String=bundle.getString("reflesh")
                if(valore=="synchronize")
                {
                    progressTimer.progress = formato_ore.format(Date()).toInt()
                    progressTimer2.progress = formato_minuti.format(Date()).toInt()
                    progressTimer3.progress = formato_secondi.format(Date()).toInt()
                }
                else
                    if(valore=="setTime")
                    {
                        ora = formato1.format(Date()).toString()
                        data = formato2.format(Date()).toString()
                        view.timerView.text = ora
                        view.dateView.text = data
                    }
            }
        }
        class ClockThread constructor(val handler: Handler):Thread()
        {
            var mediaplayer:MediaPlayer?=null
            override fun run()
            {
                mediaplayer= MediaPlayer.create(contesto_Fragment_Clock,R.raw.ticchettio_sound)
                notify_message("synchronize")
                while(regolatore_clock==true)
                {
                    notify_message("setTime")
                    sleep(1000)
                    if(regolatore_clock==true)
                    {
                        progressTimer3.incrementProgressBy(1)
                        if (progressTimer3.progress == progressTimer3.max)
                        {
                            progressTimer2.incrementProgressBy(1)
                            progressTimer3.progress = 0
                        }
                        if (progressTimer2.progress == progressTimer2.max)
                        {
                            progressTimer.incrementProgressBy(1)
                            progressTimer2.progress = 0
                        }
                        mediaplayer?.start()
                    }
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
