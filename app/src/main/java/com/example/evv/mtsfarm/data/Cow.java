package com.example.evv.mtsfarm.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Cow {
    @PrimaryKey
    public int id;
    public String name;
    public int age;
    public int weight;
    public String status;
    public int herd;
    public String farm;
}
