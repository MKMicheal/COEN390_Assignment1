package com.example.coen390_assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button  Counter1, Counter2, Counter3, Counts, Settings;
    private TextView Total;
    public int totalcount, count1=0, count2=0, count3=0;
    public int maxcount;
    public ArrayList<String> list =new ArrayList<>();
    private SharedPreferences sharedPreferences;


    private View.OnClickListener TotalCountOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(totalcount < maxcount) {
                if(view.getId()==Counter1.getId())
                {
                    count1=count1+1;
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("Countdata"+totalcount, sharedPreferences.getString("Counter1key", null));
                    editor.putString("Countdatadef"+totalcount, "1");
                    editor.putInt("Count1key",count1);
                    editor.apply();
                }
                else if(view.getId()==Counter2.getId())
                {
                    count2=count2+1;
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("Countdata"+totalcount, sharedPreferences.getString("Counter2key", null));
                    editor.putString("Countdatadef"+totalcount, "2");
                    editor.putInt("Count2key",count2);
                    editor.apply();
                }
                else
                {
                    count3=count3+1;
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("Countdata"+totalcount, sharedPreferences.getString("Counter3key", null));
                    editor.putString("Countdatadef"+totalcount, "3");
                    editor.putInt("Count3key",count3);
                    editor.apply();
                }
                totalcount = (sharedPreferences.getInt("Count1key", 0)+sharedPreferences.getInt("Count2key", 0)+sharedPreferences.getInt("Count3key", 0));
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
        count1=(sharedPreferences.getInt("Count1key", 0));
        count2=(sharedPreferences.getInt("Count2key", 0));
        count3=(sharedPreferences.getInt("Count3key", 0));
        totalcount = (sharedPreferences.getInt("Count1key", 0)+sharedPreferences.getInt("Count2key", 0)+sharedPreferences.getInt("Count3key", 0));
        for(int i=0;i<totalcount;i++)
        {
            list.add(sharedPreferences.getString("Countdata"+i,null));
        }
        Total.setText("Total Count: " + totalcount);
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
    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(Counter1.getText().toString()))
        {
            setGoToSettings();
        }


    }

}