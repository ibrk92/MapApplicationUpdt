package com.ibrahimkiceci.mapapplication;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.shape.ShapePath;
import com.ibrahimkiceci.mapapplication.adapter.MapAdapter;
import com.ibrahimkiceci.mapapplication.model.Place;
import com.ibrahimkiceci.mapapplication.roomdb.PlaceDAO;
import com.ibrahimkiceci.mapapplication.roomdb.PlaceDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class activity_register extends AppCompatActivity {

    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editText1 = findViewById(R.id.mailTextSignUp);
        editText2 = findViewById(R.id.passTextSignUp);


    }


    public void register(View view) {


        if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill all fields to register the app", Toast.LENGTH_SHORT).show();

        }else{

            Intent intent = new Intent(activity_register.this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Welcome !", Toast.LENGTH_SHORT).show();

        }

        
    }

    public void textClick(View view){

        Intent intent = new Intent(activity_register.this, activity_login.class);
        startActivity(intent);


    }

    //




}