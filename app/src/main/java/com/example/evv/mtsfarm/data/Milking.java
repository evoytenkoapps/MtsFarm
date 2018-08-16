package com.example.evv.mtsfarm.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Milking {
    @PrimaryKey
    public int key;
    public int id;
    public String data;
    public int weight;
}
