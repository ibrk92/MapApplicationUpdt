package com.ibrahimkiceci.mapapplication.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
// for room db i create a class to get info what i save !
public class Place {
    //In android room documentary, it shows that we should create like that !!
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "latitude")
    public Double latitude;
    @ColumnInfo(name = "longitude")
    public Double longitude;

    // creating constructor
    public Place(String name, Double latitude, Double longitude){
        // we do not need to write id in constructor because it will be created auto !

        this.name = name;
        this.latitude =latitude;
        this.longitude =longitude;

    }


}
