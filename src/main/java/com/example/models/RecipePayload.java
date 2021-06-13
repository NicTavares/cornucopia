package com.example.models;

public class RecipePayload {
    private int UUID;
    private String name;
    private String text;
    private float averageScore;
    private float estimatedTime;
    private int difficulty;
    private int uploaderUUID;
    private String tags;
    private String ingredients;
    private String equipment;
    private String techniques;

    public RecipePayload() {}

    @Override
    public String toString() {
        return "RecipePayload{" +
                "UUID=" + UUID +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", averageScore=" + averageScore +
                ", estimatedTime=" + estimatedTime +
                ", uploaderUUID=" + uploaderUUID +
                ", tags='" + tags + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", equipment='" + equipment + '\'' +
                ", techniques='" + techniques + '\'' +
                '}';
    }

    public int getUUID() {
        return UUID;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public float getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getUploaderUUID() {
        return uploaderUUID;
    }

    public void setUploaderUUID(int uploaderUUID) {
        this.uploaderUUID = uploaderUUID;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTechniques() {
        return techniques;
    }

    public void setTechniques(String techniques) {
        this.techniques = techniques;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Recipe getRecipe() {
        Recipe r = new Recipe();
        r.setUUID(0);
        r.setEstimatedTime(this.estimatedTime);
        r.setAverageScore(0);
        r.setName(this.name);
        r.setText(this.text);
        r.setUploaderUUID(this.uploaderUUID);
        return r;
    }
}
