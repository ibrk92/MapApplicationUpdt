package com.ibrahimkiceci.mapapplication.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ibrahimkiceci.mapapplication.model.Place;

// Source: https://developer.android.com/training/data-storage/room#java
//the database class must define an abstract method that has zero arguments and returns an instance of the DAO class.

    @Database(entities = {Place.class}, version = 1)
    public abstract class PlaceDatabase extends RoomDatabase {
        public abstract PlaceDAO placeDao();
    }


