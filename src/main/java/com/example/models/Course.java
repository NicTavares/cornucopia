package com.example.models;

public class Course {
    private int UUID;
    private String text;
    private int length;
    private String courseName;
    private String requirementName;
    private int creatorUUID;

    public Course(int UUID,
                  String text,
                  int length,
                  String courseName,
                  String requirementName,
                  int creatorUUID) {
        this.UUID = UUID;
        this.text = text;
        this.length = length;
        this.courseName = courseName;
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

    public String getCourseName() {
        return courseName;
    }

    public String getRequirementName() {
        return requirementName;
    }
}
