package com.example.models;

public class Technique {

    private String name;
    private int difficulty;

    public Technique(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;

    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
