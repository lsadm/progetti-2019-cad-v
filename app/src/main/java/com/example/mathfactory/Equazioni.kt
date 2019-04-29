package com.example.mathfactory
import kotlin.math.*
class Equazioni
{
    var controllo_algebrico=false
    var controllo_differenziale=false
    var a=""
    var b=""
    var c=""
    var rad1=""
    var rad2=""
    var soluzione_algebrica=""
    var soluzione_differenziale=""
    var delta=0.toFloat()
    var real=""
    var imm=""
    public fun risolvi()
    {
        if((a!=".")&&(b!=".")&&(c!=".")&&(a!="-")&&(b!="-")&&(c!="-"))
            {
               val op1:Float
                val op2:Float
                val op3:Float
                if(a!="")
                 op1=a.toFloat()
                else
                    op1=0.toFloat()
                if(b!="")
                    op2=b.toFloat()
                else
                    op2=0.toFloat()
                if(c!="")
                    op3=c.toFloat()
                else
                    op3=0.toFloat()
                if (op1 != 0.toFloat())
                {
                    delta = op2.pow(2) - 4 * op1 * op3
                    if (delta > 0)
                    {
                        rad1 = ((-1*op2 + sqrt(delta)) / (2 * op1)).toString()
                        rad2 = ((-1*op2 - sqrt(delta)) / (2 * op1)).toString()
                        if((rad1.toFloat()!=0.toFloat())&&(rad2.toFloat()!=0.toFloat()))
                        soluzione_differenziale="y(x)=A*exp("+rad1+"*x)+B*exp("+rad2+"*x)"
                        else
                        if(rad1.toFloat()==0.toFloat())
                            soluzione_differenziale="y(x)=A+B*exp("+rad2+"*x)"
                            else
                            soluzione_differenziale="y(x)=A*exp("+rad1+"*x)+B"
                    }
                    else
                        if(delta<0)
                       {
                        real=(-1*op2/(2*op1)).toString()
                        imm=(sqrt(-delta)/(2*op1)).toString()
                        if(op2!=0.toFloat())
                        {
                            rad1 = real + "+i" + imm
                            rad2 = real + "-i" + imm
                            soluzione_differenziale="y(x)=exp("+real+"*x)*(A*cos("+imm+"*x)+B*sen("+imm+"*x))"
                        }
                        else
                        {
                            rad1 ="+i" + imm
                            rad2 ="-i" + imm
                            soluzione_differenziale="y(x)=A*cos("+imm+"*x)+B*sen("+imm+"*x)"
                        }
                       }
                        else
                            if(op2!=0.toFloat())
                           {
                            rad1=(-1*op2/(2*op1)).toString()
                            soluzione_algebrica="x="+rad1+"\n(Double Root)"
                               soluzione_differenziale="y(x)=A*x*exp("+rad1+"*x)"

                           }
                            else
                            {
                                soluzione_algebrica="x=0.0\n(Double Root)"
                                soluzione_differenziale="y(x)=A*x"
                            }
                    if(delta!=0.toFloat())
                     soluzione_algebrica="x1="+rad1+"\nx2="+rad2
                }
                else
                    if (op2 != 0.toFloat())
                    {
                        if(op3!=0.toFloat())
                        {
                            rad1 = (-op3 / op2).toString()
                            soluzione_algebrica = "x=" + rad1
                            soluzione_differenziale="y(x)=A*exp("+rad1+"*x)"
                        }
                        else
                        {
                            soluzione_algebrica = "x=0.0"
                            soluzione_differenziale="y(x)=A"
                        }
                    }
                    else
                        if(op3==0.toFloat())
                        {
                        soluzione_algebrica = "Indeterminate!"
                        soluzione_differenziale=soluzione_algebrica
                            controllo_algebrico=true
                            controllo_differenziale=true
                        }
                       else
                        {
                            soluzione_algebrica="Impossible!"
                            soluzione_differenziale="y(x)=0"
                            controllo_algebrico=true
                        }
            }
            else
        {
            soluzione_algebrica="Invalid Operands!"
            soluzione_differenziale=soluzione_algebrica
            controllo_algebrico=true
            controllo_differenziale=true
        }
        }
}


