package com.example.mathfactory
import android.content.Intent
import android.graphics.Color.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlin.math.*
import java.io.File
import kotlinx.android.synthetic.main.activity_grafici.*
var n=0
var t=0.0
var y=0.0
lateinit var series:LineGraphSeries<DataPoint>
lateinit var series2:LineGraphSeries<DataPoint>
class Grafici: AppCompatActivity()
{
    private var min:Double=0.0
    private var max:Double=1.0
    private var min2:Double=0.0
    private var max2:Double=1.0
    var f=0.0
    var p=0.0
    var x=0.0
    var f_2=0.0
    var p2=0.0
    var x2=0.0
    var T=0.0
    var rit=0.0
    var o=0.0
    var real=0.0
    var f1=0.0
    var f2=0.0
    var tau1=0.0
    var tau2=0.0
    var imm=0.0
    var T2=0.0
    var rit2=0.0
    var o2=0.0
    var real2=0.0
    var f1_2=0.0
    var f2_2=0.0
    var tau1_2=0.0
    var tau2_2=0.0
    var imm2=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafici)
        val passo=getIntent().getExtras().getDouble("passo")
        val inf=getIntent().getExtras().getDouble("inf")
        val sup=getIntent().getExtras().getDouble("sup")
        val identifier=getIntent().getExtras().getInt("identifier")
        val A=getIntent().getExtras().getDouble("A")
       if(controllo_generale==1)
       {
            f = getIntent().getExtras().getDouble("f")
            p = getIntent().getExtras().getDouble("p")
            x = getIntent().getExtras().getDouble("x")
       }
        else
           if(controllo_generale==2)
           {
               T=getIntent().getExtras().getDouble("T")
               rit=getIntent().getExtras().getDouble("rit")
               o=getIntent().getExtras().getDouble("o")
               real=getIntent().getExtras().getDouble("real")
               f1=getIntent().getExtras().getDouble("f1")
               f2=getIntent().getExtras().getDouble("f2")
               tau1=getIntent().getExtras().getDouble("tau1")
               tau2=getIntent().getExtras().getDouble("tau2")
               imm=getIntent().getExtras().getDouble("imm")
           }
        val titolo=getIntent().getExtras().getString("titolo")
        val interpolat=getIntent().getExtras().getBoolean("interpolat")
        val controllo=getIntent().getExtras().getBoolean("controllo")
        val identifier2=getIntent().getExtras().getInt("identifier2")
        val A2=getIntent().getExtras().getDouble("A2")
        if(controllo_generale==1)
        {
            f_2 = getIntent().getExtras().getDouble("f2")
            p2 = getIntent().getExtras().getDouble("p2")
            x2 = getIntent().getExtras().getDouble("x2")
        }
        else
            if(controllo_generale==2)
            {
                T2=getIntent().getExtras().getDouble("T2")
                rit2=getIntent().getExtras().getDouble("rit2")
                o2=getIntent().getExtras().getDouble("o2")
                real2=getIntent().getExtras().getDouble("real2")
                f1_2=getIntent().getExtras().getDouble("f1_2")
                f2_2=getIntent().getExtras().getDouble("f2_2")
                tau1_2=getIntent().getExtras().getDouble("tau1_2")
                tau2_2=getIntent().getExtras().getDouble("tau2_2")
                imm2=getIntent().getExtras().getDouble("imm2")
            }
        val titolo2=getIntent().getExtras().getString("titolo2")
        val interpolat2=getIntent().getExtras().getBoolean("interpolat2")
        val controllo2=getIntent().getExtras().getBoolean("controllo2")
        var graphview: GraphView = findViewById(R.id.graph)
        graphview.title="MathGraph"
        graphview.titleColor= rgb(155,17,30)
        graphview.titleTextSize=65.toFloat()
        series = LineGraphSeries<DataPoint>()
        series2 = LineGraphSeries<DataPoint>()
        if((!interpolat)&&(controllo))
        {
            series.setThickness(0)
            series.setDrawDataPoints(true)
            series.setDataPointsRadius(5.toFloat())
        }
        if((!interpolat2)&&(controllo2))
        {
            series2.setThickness(0)
            series2.setDrawDataPoints(true)
            series2.setDataPointsRadius(5.toFloat())
        }
        series.setTitle(titolo)
        series.setColor(rgb(81,153,167))
        series2.setTitle(titolo2)
        series2.setColor(rgb(255,215,0))
        graphview.getLegendRenderer().setVisible(true)
        graphview.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP)
        if((((sup-inf)/passo)>100000)||(((sup-inf)/passo)<-100000))
            n=100000
        else
        {
            if ((sup - inf) > 0)
                n = ceil((sup - inf) / passo).toInt()
            else
                n = ceil((sup - inf) / (-1 * passo)).toInt()
        }
        t = inf - passo
        var vettore=DoubleArray(n)
        var vettore2=DoubleArray(n)
        for (i in 0..n-1) {
            t += passo
            when(identifier)
            {
                1->y=A*sin(2*PI*f*t+p)+x  //
                2->y= A* cos(2 * PI * f * t + p) +x//
                3->y=(A* asin(t + p) +x)*180/PI//
                4->y=(A* acos( t +p ) +x)*180/PI//
                5->y= A* exp(t + p) +x//
                6->y= A* ln(t + p) +x//
                7->y=A*10.toDouble().pow(t+p)+x//
                8->y= A* log10(t + p) +x//
                9->y=A*((1+ sign(t-(rit-T/2)))/2)*((1+ sign(-t+(rit+T/2)))/2)+o
                10->y= A*(-1/T*abs(t-rit)+1)*((1+ sign(t-(rit-T)))/2)*((1+ sign(-t+(rit+T)))/2)+o
                11->y=A*sin(2*PI*(t-rit))/(2*PI*(t-rit))+o
                12->y=(A*sin(2*PI*(t-rit))/(2*PI*(t-rit))).pow(2)+o
                13->y=A/(t+p)+x//
                14->y=A*(t+p).pow(2)+x//
                15->y=A*sqrt(t + p) +x
                16->y=A*(t+p)+x//
                17->y=A*(t+p).pow(3)+x//
                19->y=A*2.toDouble().pow(t+p)+x//
                20->y=A* log2(t+p) +x//
                21->y=A*exp((-1/tau1)*(t-rit))+o
                22->y=(A/2)*(exp((-1/tau1)*(t-rit))+exp((-1/tau2)*(t-rit)))+o
                23->y=A*t*exp((-1/tau1)*(t-rit))+o
                24->y=A*exp(real*(t-rit))*cos(imm*(t-rit))+o
                25->y=A*cos(2*PI*f1*(t-rit))*((1+ sign(t-(rit-T/2)))/2)*((1+ sign(-t+(rit+T/2)))/2)+o
                26->y=A*cos(2*PI*f1*(t-rit))*(-1/T*abs(t-rit)+1)*((1+ sign(t-(rit-T)))/2)*((1+ sign(-t+(rit+T)))/2)+o
                27->y=A*cos(2*PI*f1*(t-rit))*cos(2*PI*f2*(t-rit))+o
            }
            vettore[i]= y
            series.appendData(DataPoint(t, y), true, n)
            when(identifier2)
            {
                1->y=A2*sin(2*PI*f_2*t+p2)+x2  //
                2->y= A2* cos(2 * PI * f_2 * t + p2) +x2//
                3->y=(A2* asin(t + p2) +x2)*180/PI//
                4->y=(A2* acos( t +p2 ) +x2)*180/PI//
                5->y= A2* exp(t + p2) +x2//
                6->y= A2* ln(t + p2) +x2//
                7->y=A2*10.toDouble().pow(t+p2)+x2//
                8->y= A2* log10(t + p2) +x2//
                9->y=A2*((1+ sign(t-(rit2-T2/2)))/2)*((1+ sign(-t+(rit2+T2/2)))/2)+o2
                10->y= A2*(-1/T2*abs(t-rit2)+1)*((1+ sign(t-(rit2-T2)))/2)*((1+ sign(-t+(rit2+T2)))/2)+o2
                11->y=A2*sin(2*PI*(t-rit2))/(2*PI*(t-rit2))+o2
                12->y=(A2*sin(2*PI*(t-rit2))/(2*PI*(t-rit2))).pow(2)+o2
                13->y=A2/(t+p2)+x2//
                14->y=A2*(t+p2).pow(2)+x2//
                15->y=A2*sqrt(t + p2) +x2
                16->y=A2*(t+p2)+x2//
                17->y=A2*(t+p2).pow(3)+x2//
                19->y=A2*2.toDouble().pow(t+p2)+x2//
                20->y=A2* log2(t+p2) +x2//
                21->y=A2*exp((-1/tau1_2)*(t-rit2))+o2
                22->y=(A2/2)*(exp((-1/tau1_2)*(t-rit2))+exp((-1/tau2_2)*(t-rit2)))+o2
                23->y=A2*t*exp((-1/tau1_2)*(t-rit2))+o2
                24->y=A2*exp(real2*(t-rit2))*cos(imm2*(t-rit2))+o2
                25->y=A2*cos(2*PI*f1_2*(t-rit2))*((1+ sign(t-(rit2-T2/2)))/2)*((1+ sign(-t+(rit2+T2/2)))/2)+o2
                26->y=A2*cos(2*PI*f1_2*(t-rit2))*(-1/T2*abs(t-rit2)+1)*((1+ sign(t-(rit2-T2)))/2)*((1+ sign(-t+(rit2+T2)))/2)+o2
                27->y=A2*cos(2*PI*f1_2*(t-rit2))*cos(2*PI*f2_2*(t-rit2))+o2

            }
            vettore2[i]= y
            series2.appendData(DataPoint(t, y), true, n)
        }
        graphview.getViewport().setXAxisBoundsManual(true)
        graphview.getViewport().setYAxisBoundsManual(true)
        graphview.getViewport().setMinX(floor(inf*10)/10)
        graphview.getViewport().setMaxX(ceil(sup*10)/10)
        if(identifier!=0)
         search_min_max(vettore)
        if(identifier2!=0)
            search_min_max_2(vettore2)
        if(min<=min2)
         graphview.getViewport().setMinY(floor(min)*1.5)
        else
            graphview.getViewport().setMinY(floor(min2)*1.5)
        if(max>=max2)
         graphview.getViewport().setMaxY(ceil(max)*1.5)
        else
            graphview.getViewport().setMaxY(ceil(max2)*1.5)
        graphview.addSeries(series)
        if(identifier2!=0)
         graphview.addSeries(series2)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_application3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_one) {
            Toast.makeText(this, "MathView\nVersion 1.0", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_two) {
            Toast.makeText(this, "Enjoy with Math!", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Created by:\nRaffaele Maddaloni\nand\nGiuseppe Barbato ®", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_home) {
            val next:Intent
            if(controllo_generale==1)
             next = Intent(this, Function3::class.java)
            else
            if(controllo_generale==2)
             next = Intent(this, Function7::class.java)
            else
                next = Intent(this, Function1::class.java)
            startActivity(next)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun search_min_max(vettore:DoubleArray)
    {
        min=vettore[0]
        max=vettore[0]
        var i=0
        while(i<n)
        {
            if(min>vettore[i])
                min=vettore[i]
            if(max<vettore[i])
                max=vettore[i]
            i++
        }
    }
    private fun search_min_max_2(vettore:DoubleArray)
    {
        min2=vettore[0]
        max2=vettore[0]
        var i=0
        while(i<n)
        {
            if(min2>vettore[i])
                min2=vettore[i]
            if(max2<vettore[i])
                max2=vettore[i]
            i++
        }
    }
}







