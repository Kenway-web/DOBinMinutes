package eu.kenway.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvselecteddate:TextView? =null

    private  var tvageinmuntes:TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker:Button=findViewById(R.id.btnDatePicker)
        tvselecteddate=findViewById(R.id.tvselectedate)
        tvageinmuntes=findViewById(R.id.tvAgeInMinutes)




        btnDatePicker.setOnClickListener {
            clickDatePicker()
            Toast.makeText(this,"btnDatePickerDisplayed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickDatePicker()
    {

        val  mycalender=Calendar.getInstance()
        val year=mycalender.get(Calendar.YEAR )
        val month=mycalender.get(Calendar.MONTH )
        val day=mycalender.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,selectedyear,selectedmonth,selecteddayofmonth ->

                Toast.makeText(this,"year was $selectedyear, " +
                        "Month was ${selectedmonth+1}," +
                        " Day of Month was ${selecteddayofmonth}",
                    Toast.LENGTH_SHORT).show()

                val selecteddate="$selecteddayofmonth/${selectedmonth+1}/$selectedyear"

                tvselecteddate?.text=selecteddate

                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate=sdf.parse(selecteddate)


                theDate?.let {// will execute code if the dateis not empty this is called null safety
                    val SelectedDateInMinutes=theDate.time/60000

                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes=currentDate.time/60000

                        val differenceInMinutes=currentDateInMinutes-SelectedDateInMinutes

                        tvageinmuntes?.text=differenceInMinutes.toString()
                    }



                }

            },
            year,
            month,
            day

        )

        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()


    }
}