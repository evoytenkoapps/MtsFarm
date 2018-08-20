package com.example.evv.mtsfarm.data;

import java.util.List;

public class Detail {

    private List<Milking> milkings;
    private List<Temperature> temperatures;
    private List<Weight> weights;


    public List<Milking> getMilkings() {
        return milkings;
    }

    public void setMilkings(List<Milking> milkings) {
        this.milkings = milkings;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }
}
