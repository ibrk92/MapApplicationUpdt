package com.ibrahimkiceci.mapapplication.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ibrahimkiceci.mapapplication.model.Place;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlaceDAO {
    @Query("SELECT * FROM Place") // I want to get all of data from place table.
    Flowable<List<Place>> getAll();
    @Insert
    Completable insert(Place place);
    @Delete
    Completable delete(Place place);


}
