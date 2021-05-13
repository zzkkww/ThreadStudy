package com.kkwStream.simpleDemo;

import java.util.List;

/**
 * 实体类
 *
 * @author Kevin
 * @version 1.0
 * @date 2021-05-13
 */
class Student {

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生分数
     */
    private double score;

    /**
     * 所学课程
     */
    List<String> course;

    public Student() {}

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Student(String name, double score, List<String> course) {
        this.name = name;
        this.score = score;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
