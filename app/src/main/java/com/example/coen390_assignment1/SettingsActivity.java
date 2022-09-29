package com.example.coen390_assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class SettingsActivity extends AppCompatActivity {

    private Button SaveButton;
    private ImageButton Backbutton, MoreButton;
    private TextView Counter1Name, Counter2Name, Counter3Name, MaxCounterName;
    private TextInputEditText Counter1Input, Counter2Input, Counter3Input, MaxCounterInput;
    private Toolbar SettingsToolbar;

    private View.OnClickListener SaveAll = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int check = Integer.parseInt(MaxCounterInput.getText().toString());
            if(check < 5 || check > 200 )
            {
                Toast errorToast = Toast.makeText(SettingsActivity.this, "Max Count must be between 5 and 200", Toast.LENGTH_LONG);
                errorToast.show();
            }
            else
            {
                gotoSaveAll();
            }
        }
    };
    private void gotoSaveAll(){
        Intent  mainIntent = new Intent(this, MainActivity.class);
        String counter1n = Counter1Input.getText().toString();
        String counter2n = Counter2Input.getText().toString();
        String counter3n = Counter3Input.getText().toString();
        String maxcount = MaxCounterInput.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("settingsActivity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("Counter1key", counter1n);
        editor.putString("Counter2key", counter2n);
        editor.putString("Counter3key", counter3n);
        editor.putString("MaxCountkey", maxcount);
        editor.putInt("Count1key", 0);
        editor.putInt("Count2key", 0);
        editor.putInt("Count3key", 0);
        for(int i=0;i<200;i++)
        {
            editor.putString("Countdata"+i,null);
        }
        editor.apply();
        Counter1Input.setEnabled(false);
        Counter2Input.setEnabled(false);
        Counter3Input.setEnabled(false);
        MaxCounterInput.setEnabled(false);
        SaveButton.setVisibility(View.INVISIBLE);
        startActivity(mainIntent);


    }
    private View.OnClickListener Back = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gotoBack();
        }
    };

    private void gotoBack(){
        Intent  mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
    private View.OnClickListener More = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PopupMenu popupMenu=new PopupMenu(SettingsActivity.this, MoreButton);
            popupMenu.getMenuInflater().inflate(R.menu.settingpopup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId())
                    {
                        case R.id.EditSettings:
                            Counter1Input.setEnabled(true);
                            Counter2Input.setEnabled(true);
                            Counter3Input.setEnabled(true);
                            MaxCounterInput.setEnabled(true);
                            SaveButton.setVisibility(View.VISIBLE);

                    }


                    return false;
                }

            });
            popupMenu.show();
        }
    };
    private TextWatcher FillAll = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String counter1n = Counter1Input.getText().toString();
            String counter2n = Counter2Input.getText().toString();
            String counter3n = Counter3Input.getText().toString();
            String maxcount = MaxCounterInput.getText().toString();
            SaveButton.setEnabled(!counter1n.isEmpty() && !counter2n.isEmpty()&& !counter3n.isEmpty()&& !maxcount.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SaveButton = findViewById(R.id.SaveButton);
        Counter1Input = findViewById(R.id.Counter1Input);
        Counter2Input = findViewById(R.id.Counter2Input);
        Counter3Input = findViewById(R.id.Counter3Input);
        MaxCounterInput = findViewById(R.id.MaxCountInput);
        SettingsToolbar = findViewById(R.id.SettingsToolbar);
        Counter1Input.addTextChangedListener(FillAll);
        Counter2Input.addTextChangedListener(FillAll);
        Counter3Input.addTextChangedListener(FillAll);
        MaxCounterInput.addTextChangedListener(FillAll);
        Backbutton = findViewById(R.id.Backbutton);
        MoreButton = findViewById(R.id.Morebutton);
        Backbutton.setOnClickListener(Back);
        MoreButton.setOnClickListener(More);
        setSupportActionBar(SettingsToolbar);
        SaveButton.setOnClickListener(SaveAll);
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences("settingsActivity", Context.MODE_PRIVATE);
        Counter1Input.setText( sharedPreferences.getString("Counter1key", null));
        Counter2Input.setText(sharedPreferences.getString("Counter2key", null));
        Counter3Input.setText(sharedPreferences.getString("Counter3key", null));
        MaxCounterInput.setText(sharedPreferences.getString("MaxCountkey", null));



    }

}