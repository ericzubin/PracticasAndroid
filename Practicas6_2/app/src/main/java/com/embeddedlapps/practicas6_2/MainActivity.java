package com.embeddedlapps.practicas6_2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity  extends Activity {
    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    TextView dateAndTimeLabel;
    Calendar dateAndTime = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        }
        };
        TimePickerDialog.OnTimeSetListener t = new
                TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateAndTime.set(Calendar.HOUR, hourOfDay);
                        dateAndTime.set(Calendar.MINUTE, minute);
                        updateLabel();
                    }


                };

        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.activity_main);
            Button btn1 = (Button) findViewById(R.id.dateBtn);
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("Boton ","DATE");
                    new DatePickerDialog(MainActivity.this, d,
                            dateAndTime.get(Calendar.YEAR),
                            dateAndTime.get(Calendar.MONTH),
                            dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            Button btn = (Button) findViewById(R.id.timeBtn);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("Boton ","TIME");
                    new TimePickerDialog(MainActivity.this,
                            t, dateAndTime.get(Calendar.HOUR),
                            dateAndTime.get(Calendar.MINUTE), true).show();
                }
            });
            dateAndTimeLabel = (TextView) findViewById(R.id.dateAndTime);
            updateLabel();
        }

        private void updateLabel() {
            //Error
            dateAndTimeLabel.setText(fmtDateAndTime.format(dateAndTime.getTime()));
        }

}