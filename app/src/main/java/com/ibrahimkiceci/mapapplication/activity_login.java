package com.ibrahimkiceci.mapapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {
    EditText mailText;
    EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mailText = findViewById(R.id.mailTextLogin);
        passwordText = findViewById(R.id.passwordTextLogin);
    }

    public void login(View view){

        if (mailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent =  new Intent(activity_login.this, MainActivity.class);
            startActivity(intent);

        }

    }

    public void textClick(View view){

        Intent intent =  new Intent(activity_login.this, activity_register.class);
        startActivity(intent);

    }


}