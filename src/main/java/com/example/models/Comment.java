package com.example.models;

public class Comment {
    private int recipeUUID;
    private int commentNumber;
    private String text;
    private int authorUUID;

    public Comment(int recipeUUID, int commentNumber, String text, int authorUUID) {
        this.recipeUUID = recipeUUID;
        this.commentNumber = commentNumber;
        this.text = text;
        this.authorUUID = authorUUID;
    }

    public int getRecipeUUID() {
        return recipeUUID;
    }

    public void setRecipeUUID(int recipeUUID) {
        this.recipeUUID = recipeUUID;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAuthorUUID() {
        return authorUUID;
    }

    public void setAuthorUUID(int authorUUID) {
        this.authorUUID = authorUUID;
    }
}
