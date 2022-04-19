package com.example.mohammed_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    DatabaseHelper db;
    int count =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button delete=(Button)findViewById(R.id.deleteButton);
        Button view=(Button)findViewById(R.id.viewData);
        EditText id_val=(EditText)findViewById(R.id.editTextID);
        Button bttnsecond=(Button)findViewById(R.id.Secondact);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id =id_val.getText().toString();
                db.delete(user_id);

                Toast.makeText(ThirdActivity.this,"Deleted",Toast.LENGTH_LONG).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    db.getListContents();
                    Toast.makeText(ThirdActivity.this,"Successful View",Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    count=1;
                    Toast.makeText(ThirdActivity.this,"Unsuccessful",Toast.LENGTH_LONG).show();
                }
            }

        });

        bttnsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this,SecondActivity.class));
            }
        });


    }
}