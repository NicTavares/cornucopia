package com.example.models;

public class Recipe {


    private int UUID;
    private String text;
    private float average_score;
    private float estimatedTime;
    private int uploaderUUID;

    public Recipe(int UUID,
                  String text,
                  float average_score,
                  float estimatedTime,
                  int uploaderUUID) {
        this.UUID = UUID;
        this.text = text;
        this.average_score = average_score;
        this.estimatedTime = estimatedTime;
        this.uploaderUUID = uploaderUUID;

    }

    public int getUUID() {
        return UUID;
    }

    public float getAverage_score() {
        return average_score;
    }

    public float getEstimatedTime() {
        return estimatedTime;
    }

    public int getUploaderUUID() {
        return uploaderUUID;
    }

    public String getText() {
        return text;
    }

    public void setAverage_score(float average_score) {
        this.average_score = average_score;
    }

}
