package com.example.bottomnavigationactivity.ITEM;

import java.util.ArrayList;

public class items {

    String name ; int weight ;String wtimehrs; String wtimemin;
    String stimehrs ; String stimemin;String unit; int id; int taget; int completed;
    ArrayList<String> stringArrayList; ArrayList<Integer> integerArrayList;

    public items(ArrayList<String> stringArrayList, ArrayList<Integer> integerArrayList) {
        this.stringArrayList = stringArrayList;
        this.integerArrayList = integerArrayList;
    }

    public items(String name, int weight, String wtimehrs, String wtimemin,
                 String stimehrs, String stimemin, String unit, int id, int taget, int completed) {
        this.name = name;
        this.weight = weight;
        this.wtimehrs = wtimehrs;
        this.wtimemin = wtimemin;
        this.stimehrs = stimehrs;
        this.stimemin = stimemin;
        this.unit = unit;
        this.id = id;
        this.taget = taget;
        this.completed = completed;
    }

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    public ArrayList<Integer> getIntegerArrayList() {
        return integerArrayList;
    }

    public void setIntegerArrayList(ArrayList<Integer> integerArrayList) {
        this.integerArrayList = integerArrayList;
    }

    public int getTaget() {
        return taget;
    }

    public void setTaget(int taget) {
        this.taget = taget;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public items() {
    }

    public String getWtimehrs() {
        return wtimehrs;
    }

    public void setWtimehrs(String wtimehrs) {
        this.wtimehrs = wtimehrs;
    }

    public String getWtimemin() {
        return wtimemin;
    }

    public void setWtimemin(String wtimemin) {
        this.wtimemin = wtimemin;
    }

    public String getStimehrs() {
        return stimehrs;
    }

    public void setStimehrs(String stimehrs) {
        this.stimehrs = stimehrs;
    }

    public String getStimemin() {
        return stimemin;
    }

    public void setStimemin(String stimemin) {
        this.stimemin = stimemin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
