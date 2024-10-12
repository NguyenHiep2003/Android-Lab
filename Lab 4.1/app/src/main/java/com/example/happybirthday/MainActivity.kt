package com.example.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textReuslt: TextView
    lateinit var textTemp: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0
    var currentShowResult = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textReuslt = findViewById<TextView>(R.id.text_result);
        textTemp = findViewById<TextView>(R.id.text_temp);
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnNeg).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.btn0) {
            addDigit(0)
        } else if (id == R.id.btn1) {
            addDigit(1)
        } else if (id == R.id.btn2) {
            addDigit(2)
        } else if (id == R.id.btn3) {
            addDigit(3)
        } else if (id == R.id.btn4) {
           addDigit(4)
        }else if (id == R.id.btn5) {
            addDigit(5)
        }else if (id == R.id.btn6) {
            addDigit(6)
        }else if (id == R.id.btn7) {
            addDigit(7)
        }else if (id == R.id.btn8) {
            addDigit(8)
        }else if (id == R.id.btn9) {
            addDigit(9)
        }else if (id == R.id.btnAdd) {
            if(state == 2) return;
            textReuslt.text = "$op1";
            textTemp.text = "$op1 + ";
            op = 1
            state = 2
        } else if (id == R.id.btnCE) {
            if(state == 1) op1 = 0;
            else if (state == 2) op2 = 0;
            textReuslt.text= "0";
            if(currentShowResult) textTemp.text="";
        }else if (id == R.id.btnSub) {
            if(state == 2) return;
            textReuslt.text = "$op1";
            textTemp.text = "$op1 - ";
            op = 2
            state = 2
        }else if (id == R.id.btnMul) {
            if(state == 2) return;
            textReuslt.text = "$op1";
            textTemp.text = "$op1 x ";
            op = 3
            state = 2
        }else if (id == R.id.btnDiv) {
            if(state == 2) return;
            textReuslt.text = "$op1";
            textTemp.text = "$op1 / ";
            op = 4
            state = 2
        } else if(id == R.id.btnC) {
            state =1 ;
            op = 0;
            op1 = 0;
            op2 = 0;
            textTemp.text="";
            textReuslt.text="0";
        } else if(id == R.id.btnBS) {
            if(!currentShowResult) deleteLastDigit()
        }
        else if(id == R.id.btnNeg) {
            if(!currentShowResult) changeSign()
        }
        else if (id == R.id.btnEqual) {
            if(currentShowResult) return;
            currentShowResult = true;
            var result = 0
            var floatResult: Double = 0.0
            if(state == 1) {result = op1; textTemp.text = "$op1 = "}
            else if (op == 1) {
                result = op1 + op2
            }else if (op == 2) {
                result = op1 - op2
            }else if (op == 3) {
                result = op1 * op2
            }else if (op == 4) {
                if(op2 == 0) {
                    textReuslt.text = "Failed";
                }
                else floatResult  = String.format("%.2f",(op1.toDouble() / op2.toDouble())).toDouble();
            }
            if(state == 2) textTemp.text = "${textTemp.text}${op2} = "
            if (op !=4) textReuslt.text = "$result"
            else if(op2 != 0) textReuslt.text = "$floatResult"
            if(state ==2 ) {
                state = 1
                op1 = 0
                op2 = 0
                op = 0
            }

        }
        if(id != R.id.btnEqual && id != R.id.btnNeg && id != R.id.btnBS) currentShowResult = false;
    }

    private fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            textTemp.text="";
            textReuslt.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            textReuslt.text = "$op2"
        }
    }

    private fun deleteLastDigit(){
        if (state == 1) {
            op1 /= 10
            textReuslt.text = "$op1"
        } else {
            op2 /= 10
            textReuslt.text = "$op2"
        }
    }

    private fun changeSign(){
        if (state == 1) {
            op1 = -op1
            textReuslt.text = "$op1"
        } else {
            op2 = -op2
            textReuslt.text = "$op2"
        }
    }
}