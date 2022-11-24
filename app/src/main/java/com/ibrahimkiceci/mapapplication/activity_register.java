package com.ibrahimkiceci.mapapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ibrahimkiceci.mapapplication.model.Place;
import com.ibrahimkiceci.mapapplication.roomdb.PlaceDAO;
import com.ibrahimkiceci.mapapplication.roomdb.PlaceDatabase;

public class activity_register extends AppCompatActivity {

    EditText mailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mailText = findViewById(R.id.mailTextSignUp);
        passwordText = findViewById(R.id.passTextSignUp);
    }

    public void register(View view){

        //Creating User Place

        Place place = new Place("",0.0,0.0);
        place.setMailId(mailText.getText().toString());
        place.setPassword(passwordText.getText().toString());

        if (validateInput(place)){

            PlaceDatabase placeDatabase = PlaceDatabase.getPlaceDatabase(getApplicationContext());
            PlaceDAO placeDAO = placeDatabase.placeDao();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    placeDAO.registerUser(place);
                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_LONG).show();

                }
            }).start();




        }else{
            Toast.makeText(getApplicationContext(), "Email and Passsword can not be empty !", Toast.LENGTH_LONG).show();

        }


    }

    private Boolean validateInput(Place place){


        if (place.getMailId().isEmpty() || place.getPassword().isEmpty()){

            return false;

        }

        return true;

    }

}