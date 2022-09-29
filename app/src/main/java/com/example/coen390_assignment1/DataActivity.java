package com.example.coen390_assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    private TextView TotalCounterData,Counter1Data,Counter2Data,Counter3Data;
    private ListView AllCounter;
    private SharedPreferences sharedPreferences;
    private int Counter1Total, maxcount;
    private ImageButton Backbutton, MoreButton;
    private boolean check = true;
    public ArrayList<String> list =new ArrayList<>();
    public ArrayList<String> def =new ArrayList<>();
    int totalcount;


    private void getPref()
    {
        sharedPreferences =getSharedPreferences("settingsActivity", Context.MODE_PRIVATE);
        Counter1Data.setText(sharedPreferences.getString("Counter1key", null)+ ": " + sharedPreferences.getInt("Count1key", 0));
        Counter2Data.setText(sharedPreferences.getString("Counter2key", null)+ ": " + sharedPreferences.getInt("Count2key", 0));
        Counter3Data.setText(sharedPreferences.getString("Counter3key", null)+ ": " + sharedPreferences.getInt("Count3key", 0));
        totalcount =(sharedPreferences.getInt("Count1key", 0)+sharedPreferences.getInt("Count2key", 0)+sharedPreferences.getInt("Count3key", 0));
        TotalCounterData.setText("Total Count: " + totalcount);
        list =new ArrayList<>();
        def =new ArrayList<>();
        for(int i=0;i<totalcount;i++)
        {
            list.add(sharedPreferences.getString("Countdata"+i,null));
            def.add(sharedPreferences.getString("Countdatadef"+i,null));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(DataActivity.this, android.R.layout.simple_list_item_1, list);
        AllCounter.setAdapter(null);
        AllCounter.setAdapter(adapter);
    }
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gotoBack();
        }
    };

    private void gotoBack(){
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
    private View.OnClickListener More = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PopupMenu popupMenu=new PopupMenu(DataActivity.this, MoreButton);
            popupMenu.getMenuInflater().inflate(R.menu.datapopup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId())
                    {
                        case R.id.Toggle:
                        if(check == true)
                        {
                            check = false;
                            Counter1Data.setText("Counter 1: " + sharedPreferences.getInt("Count1key", 0));
                            Counter2Data.setText("Counter 2: " + sharedPreferences.getInt("Count2key", 0));
                            Counter3Data.setText("Counter 3: " + sharedPreferences.getInt("Count3key", 0));
                            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(DataActivity.this, android.R.layout.simple_list_item_1, def);
                            AllCounter.setAdapter(null);
                            AllCounter.setAdapter(adapter2);
                        }
                        else
                        {
                            check = true;
                            getPref();
                        }


                    }


                    return false;
                }

            });
            popupMenu.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Counter1Data = findViewById(R.id.Counter1Data);
        Counter2Data = findViewById(R.id.Counter2Data);
        Counter3Data = findViewById(R.id.Counter3Data);
        TotalCounterData = findViewById(R.id.TotalCounterData);
        AllCounter = findViewById(R.id.AllCounter);
        Backbutton = findViewById(R.id.Backbutton2);
        MoreButton = findViewById(R.id.Morebutton2);
        getPref();
        Backbutton.setOnClickListener(Back);
        MoreButton.setOnClickListener(More);

    }
}