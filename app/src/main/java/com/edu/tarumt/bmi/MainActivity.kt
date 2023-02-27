package com.edu.tarumt.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Linking UI to code
        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)

        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus : TextView = findViewById(R.id.textViewStatus)

        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)

        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)


        buttonCalculate.setOnClickListener {
            if (editTextWeight.text.isEmpty()){
                editTextWeight.setError(getString(R.string.value_required))
                Toast.makeText(applicationContext, getString(R.string.value_required), Toast.LENGTH_LONG).show()
                return@setOnClickListener //terminate the program
            }
            else if (editTextHeight.text.isEmpty()){
                editTextHeight.setError(getString(R.string.value_required))
                Toast.makeText(applicationContext, getString(R.string.value_required), Toast.LENGTH_LONG).show()
                return@setOnClickListener //terminate the program
            }

            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()

            var bmi = weight / (height/100).pow(2)

            if(weight <= 0) {
                editTextWeight.setError(getString(R.string.value_low))
                Toast.makeText(applicationContext, getString(R.string.value_low), Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener //terminate the program
            }
            else if(height <= 0){
                editTextHeight.setError(getString(R.string.value_low))
                Toast.makeText(applicationContext, getString(R.string.value_low), Toast.LENGTH_LONG).show()
                return@setOnClickListener //terminate the program
            }

            if(bmi < 18.5){
                //UnderWeight
                imageViewBMI.setImageResource(R.drawable.under)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text =
                    String.format("%s : %s", getString(R.string.status), getString(R.string.under))
            }
            else if(bmi in 18.5..24.9){
                //Normal
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text =
                    String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
            }
            else if(bmi > 25){
                //OverWeight
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text =
                    String.format("%s : %s", getString(R.string.status), getString(R.string.over))
            }
        }

        buttonReset.setOnClickListener {
            editTextHeight.text.clear()
            editTextWeight.text.clear()
            textViewBMI.text = getString(R.string.bmi)
            textViewStatus.text = getString(R.string.status)
            imageViewBMI.setImageResource(R.drawable.empty)

        }
    }
}