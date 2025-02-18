package com.assignment.fetch.model;

public class PointsDto {

    private int points;

    public PointsDto() {
    }

    public PointsDto(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "PointsDto{" +
                "points=" + points +
                '}';
    }
}
