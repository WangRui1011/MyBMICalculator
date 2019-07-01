package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight,etHeight;
    Button btnCalculate,btnReset;
    TextView tvDate,tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight=findViewById(R.id.editTextWeight);
        etHeight=findViewById(R.id.editTextHeight);
        btnCalculate=findViewById(R.id.buttonCalculate);
        btnReset=findViewById(R.id.buttonReset);
        tvDate=findViewById(R.id.textViewDate);
        tvBMI=findViewById(R.id.textViewBMI);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWeight=etWeight.getText().toString();
                String strHeight=etHeight.getText().toString();

                double weight=Double.parseDouble(strWeight);
                double height=Double.parseDouble(strHeight);
                double bmi;

                bmi=weight/(height*height);
                tvBMI.setText("Last Calculated BMI: "+bmi);

                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" + now.get(Calendar.YEAR) + " " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
                tvDate.setText("Last Calculated Date: "+datetime);

                etWeight.setText(" ");
                etHeight.setText(" ");

                SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit=prefs.edit();

                prefEdit.putFloat("date",0);
                prefEdit.putFloat("bmi",0);


                prefEdit.commit();

            }

        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDate.setText("Last Calculated Date:");
                tvBMI.setText("Last Calculated BMI");
                etWeight.setText(" ");
                etHeight.setText(" ");

                SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit=prefs.edit();

                prefEdit.putFloat("weight",0);
                prefEdit.putFloat("height",0);


                prefEdit.commit();
        }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);

        String date=prefs.getString("date","Default date");
        Float bmi=prefs.getFloat("bmi",0);

        tvDate.setText(date+" ");
        tvBMI.setText(bmi+" ");
    }
}
