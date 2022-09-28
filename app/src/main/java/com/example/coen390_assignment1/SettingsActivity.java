package com.example.coen390_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SettingsActivity extends AppCompatActivity {

    private Button SaveButton;
    private TextView Counter1Name, Counter2Name, Counter3Name, MaxCounterName;
    private TextInputEditText Counter1Input, Counter2Input, Counter3Input, MaxCounterInput;

    private View.OnClickListener SaveAll = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int check = Integer.parseInt(MaxCounterInput.getText().toString());
            if(check < 5 || check > 200 )
            {
                Toast errorToast = Toast.makeText(SettingsActivity.this, "Max Count must be between 5 and 200", Toast.LENGTH_LONG);
                errorToast.show();
            }
            else if (MaxCounterInput.getText().toString().trim().isEmpty()) {

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
        Intent  settingsIntent = new Intent(this, MainActivity.class);
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
        editor.apply();
        startActivity(settingsIntent);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SaveButton = findViewById(R.id.SaveButton);
        Counter1Input = findViewById(R.id.Counter1Input);
        Counter2Input = findViewById(R.id.Counter2Input);
        Counter3Input = findViewById(R.id.Counter3Input);
        MaxCounterInput = findViewById(R.id.MaxCountInput);
        SaveButton.setOnClickListener(SaveAll);
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences("settingsActivity", Context.MODE_PRIVATE);
        Counter1Input.setText( sharedPreferences.getString("Counter1key", null));
        Counter2Input.setText(sharedPreferences.getString("Counter2key", null));
        Counter3Input.setText(sharedPreferences.getString("Counter3key", null));
        MaxCounterInput.setText(sharedPreferences.getString("MaxCountkey", null));



    }
}