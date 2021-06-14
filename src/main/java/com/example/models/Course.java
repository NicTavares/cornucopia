package com.example.models;

public class Course {
    private int UUID;
    private String text;
    private int length;
    private String name;
    private String requirementName;
    private int creatorUUID;
    public Course()
    {
        super();
    }
    public Course(int UUID,
                  String text,
                  int length,
                  String name,
                  String requirementName,
                  int creatorUUID) {
        this.UUID = UUID;
        this.text = text;
        this.length = length;
        this.name = name;
        this.requirementName = requirementName;
        this.creatorUUID = creatorUUID;

    }

    public String getText() {
        return text;
    }

    public int getUUID() {
        return UUID;
    }

    public int getCreatorUUID() {
        return creatorUUID;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }
}
