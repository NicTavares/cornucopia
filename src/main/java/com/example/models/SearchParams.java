package com.example.models;

public class SearchParams {
    private String field;
    private String operator;
    private float value;

    public SearchParams() {}

    @Override
    public String toString() {
        return "SearchParams{" +
                "field=" + field +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getField() {
        return field;
    }

    public String getOperator() {
        return operator;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
