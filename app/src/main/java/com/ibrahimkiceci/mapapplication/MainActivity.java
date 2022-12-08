package com.ibrahimkiceci.mapapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ibrahimkiceci.mapapplication.adapter.MapAdapter;
import com.ibrahimkiceci.mapapplication.databinding.ActivityMainBinding;
import com.ibrahimkiceci.mapapplication.model.Place;
import com.ibrahimkiceci.mapapplication.roomdb.PlaceDAO;
import com.ibrahimkiceci.mapapplication.roomdb.PlaceDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    PlaceDatabase placeDatabase;
    PlaceDAO placeDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        placeDatabase = Room.databaseBuilder(getApplicationContext(),PlaceDatabase.class,"Place").build();
        // using the methods from the DAO instance to interact with the database:
        placeDAO = placeDatabase.placeDao();

        compositeDisposable.add(placeDAO.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainActivity.this::responseMain)

        );

    }

    private void responseMain(List<Place> placeList){  //it must read List because getAll method is flowable
        // it is listed as one under the other

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MapAdapter mapAdapter = new MapAdapter(placeList);
        binding.recyclerView.setAdapter(mapAdapter);


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
            // i use intent put extra to understand it is clicked the menu item or old item.
            // I also use put extra for saved location
            //in here we understand that the user will create a new location(clicked add location)
            intent.putExtra("locationInfo", "new_data");
            startActivity(intent);

        }else if(item.getItemId() == R.id.log_out){
            MainActivity.this.finish();

        }

        // what is happening when user click the menu item


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}

//Main