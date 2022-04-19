package com.example.mohammed_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    int count =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button addbttn=(Button)findViewById(R.id.Add);
        EditText id=(EditText)findViewById(R.id.editTextID);
        EditText name=(EditText)findViewById(R.id.editTextName);
        EditText NationalID=(EditText)findViewById(R.id.editTextNationalID);
        EditText surName=(EditText)findViewById(R.id.editSurName);
        Button Third =(Button)findViewById(R.id.Third);
        Button First =(Button)findViewById(R.id.First);


        final DatabaseHelper MyDB = new DatabaseHelper(this);

        addbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String id_val = id.getText().toString();
                    String name_val = name.getText().toString();
                    String nationalID_val = NationalID.getText().toString();
                    String surName_val = surName.getText().toString();

                    MyDB.addData(id_val, name_val, nationalID_val, surName_val);
                    Log.d("Mohammed","All values were added");

                    Toast.makeText(SecondActivity.this, "Successful", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    count=1;
                    Toast.makeText(SecondActivity.this,"Unsuccessful, please enter all data",Toast.LENGTH_LONG).show();
                }
            }

        });

        First.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });


        Third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
            }
        });
    }
}