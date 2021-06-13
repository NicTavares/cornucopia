package com.example.models;

public class Recipe {



    private int UUID;
    private String name;
    private String text;
    private float averageScore;
    private float estimatedTime;
    private int uploaderUUID;

    public Recipe() {}

    public Recipe(int UUID,
                  String name,
                  String text,
                  float averageScore,
                  float estimatedTime,
                  int uploaderUUID) {
        this.UUID = UUID;
        this.name = name;
        this.text = text;
        this.averageScore = averageScore;
        this.estimatedTime = estimatedTime;
        this.uploaderUUID = uploaderUUID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public void setEstimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setUploaderUUID(int uploaderUUID) {
        this.uploaderUUID = uploaderUUID;
    }

    public int getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }

    public float getAverageScore() {
        return averageScore;
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

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }
}
