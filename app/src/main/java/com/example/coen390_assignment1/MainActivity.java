package com.example.coen390_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button  Counter1, Counter2, Counter3, Counts, Settings;
    private TextView Total;
    public int totalcount =0;
    public int maxcount;
    private SharedPreferences sharedPreferences;

    private View.OnClickListener TotalCountOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(totalcount < maxcount) {
                totalcount = totalcount + 1;
                Total.setText("Total Count: " + totalcount);
            }
            else
            {
                Toast errorToast = Toast.makeText(MainActivity.this, "Max Count reached", Toast.LENGTH_LONG);
                errorToast.show();
            }
        }
    };

    private  View.OnClickListener GoToSettings = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setGoToSettings();
        }
    };

    private void setGoToSettings()
    {
        Intent  settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void getPref()
    {
        sharedPreferences =getSharedPreferences("settingsActivity", Context.MODE_PRIVATE);
        Counter1.setText( sharedPreferences.getString("Counter1key", null));
        Counter2.setText(sharedPreferences.getString("Counter2key", null));
        Counter3.setText(sharedPreferences.getString("Counter3key", null));
        maxcount = Integer.parseInt(sharedPreferences.getString("MaxCountkey", "200"));

    }

    private View.OnClickListener GoToCounts = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setGoToCounts();
        }
    };
    private void setGoToCounts() {
        Intent  dataIntent = new Intent(this, DataActivity.class);
        startActivity(dataIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maxcount =200;
        Settings = findViewById(R.id.Settings);
        Counter1 = findViewById(R.id.Counter1);
        Counter2 = findViewById(R.id.Counter2);
        Counter3 = findViewById(R.id.Counter3);
        Counts = findViewById(R.id.Counts);
        Total = findViewById(R.id.Total);

        Counter1.setOnClickListener(TotalCountOnClickListener);
        Counter2.setOnClickListener(TotalCountOnClickListener);
        Counter3.setOnClickListener(TotalCountOnClickListener);
        Settings.setOnClickListener(GoToSettings);
        Counts.setOnClickListener(GoToCounts);
        getPref();

    }
}