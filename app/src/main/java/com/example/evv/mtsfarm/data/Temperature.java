package com.example.evv.mtsfarm.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Temperature {
    @PrimaryKey(autoGenerate = true)
    public int key;
    public int id;
    public String date;
    public double temperature;
}
