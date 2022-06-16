package ru.dispenker.kontinuum.utilits;

public class PersonPoint implements Comparable<PersonPoint> {
    private Long id;
    private String name;
    private float points;
    private int countLesson;

    public PersonPoint(Long id, String name) {
        this.id = id;
        this.name = name;
        countLesson = 0;
    }

    public String getName() {
        return name;
    }

    public float getPoints() { // Получение среднего балла по всем занятиям
        return (int) (points / countLesson * 100) / 100f;
    }

    public void setPoints(float points) {
        this.points += points;
        countLesson++;
    }

    @Override
    public int compareTo(PersonPoint o) {
        return (int) ((o.getPoints() - getPoints()) * 100);
    }
}
