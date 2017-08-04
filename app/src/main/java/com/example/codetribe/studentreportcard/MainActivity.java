package com.example.codetribe.studentreportcard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView attempt;
    EditText user,password ;
    Button login,cancell;
    int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password = (EditText) findViewById(R.id.password);
        user = (EditText) findViewById(R.id.user);
        login = (Button) findViewById(R.id.login);
        cancell = (Button)findViewById(R.id.cancel);
        attempt =(TextView)findViewById(R.id.attempt);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent loginn = new Intent(MainActivity.this,Menu.class);
                if (user.getText().toString().equals("admin") && password.getText().
                        toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Loading",Toast.LENGTH_SHORT).show();
                  next();
                    startActivity(loginn);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong creditials entered",Toast.LENGTH_SHORT).show();
                }

               attempt.setVisibility(View.VISIBLE);
                count--;
                attempt.setText(Integer.toString(count));

                if (count==0){
                    login.setEnabled(false);
                }

            }
        });
        cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
public void next(){


}


}