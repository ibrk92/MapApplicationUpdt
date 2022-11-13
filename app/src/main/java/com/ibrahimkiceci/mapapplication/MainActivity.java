package com.ibrahimkiceci.mapapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //binding the menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.location_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_place){

            //intent
            // when user click the add new place, it shows the map!
            Intent intent =  new Intent(MainActivity.this,MapsActivity.class);
            startActivity(intent);

        }

        // what is happening when user click the menu item


        return super.onOptionsItemSelected(item);
    }

    // github test
}