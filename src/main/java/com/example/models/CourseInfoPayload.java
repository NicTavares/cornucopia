package com.example.models;

public class CourseInfoPayload {
    private boolean UUID;
    private boolean text;
    private boolean length;
    private boolean name;
    private boolean requirementName;
    private boolean creatorUUID;

    public boolean isUUID() {
        return UUID;
    }

    public void setUUID(boolean UUID) {
        this.UUID = UUID;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isLength() {
        return length;
    }

    public void setLength(boolean length) {
        this.length = length;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isRequirementName() {
        return requirementName;
    }

    public void setRequirementName(boolean requirementName) {
        this.requirementName = requirementName;
    }

    public boolean isCreatorUUID() {
        return creatorUUID;
    }

    public void setCreatorUUID(boolean creatorUUID) {
        this.creatorUUID = creatorUUID;
    }

    @Override
    public String toString() {
        return "CourseInfoPayload{" +
                "UUID=" + UUID +
                ", text=" + text +
                ", length=" + length +
                ", name=" + name +
                ", requirementName=" + requirementName +
                ", creatorUUID=" + creatorUUID +
                '}';
    }
}
