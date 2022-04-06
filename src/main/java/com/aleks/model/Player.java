package com.aleks.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Player {
    @SerializedName("_id")
    private final String ID;
    @SerializedName("_name")
    private final String name;
    @SerializedName("_symbol")
    private final Character symbol;
    private transient final ArrayList<Integer> steps = new ArrayList<>();

    public Player(String name, Character symbol, String ID) {
        this.ID = ID;
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSteps(int step) {
        steps.add(step);
    }

    public ArrayList<Integer> getSteps() {
        return steps;
    }

    public String getID() {
        return ID;
    }
}