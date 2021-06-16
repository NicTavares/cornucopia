package com.example.models;

public class StatisticsParams {
    private String field;
    private String operator;


    public StatisticsParams() {
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

    @Override
    public String toString() {
        return "StatisticsParams{" +
                "field='" + field + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
