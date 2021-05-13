package com.kkwStream.simpleDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream流简单示例
 *
 * @author Kevin
 * @version 1.0
 * @date 2021-05-13
 */
public class SimpleUse {

    public static List<Student> initData() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 60));
        students.add(new Student("李四", 80));
        students.add(new Student("王五", 50));
        students.add(new Student("赵六", 70));
        students.add(new Student("孙七", 90));
        students.add(new Student("周八", 30));
        return students;
    }

    public static void main(String[] args) {
        List<Student> studentList = SimpleUse.initData();
        streamImpl(studentList);

        testStream(studentList);
    }

    /**
     * 筛查学生分数小于60的学生
     *
     * @param students
     */
    public static void streamImpl(List<Student> students) {
        List<Student> studentList = students.stream().filter(student -> student.getScore() < 60).collect(Collectors.toList());
        System.out.println(studentList);
    }

    public static void testStream(List<Student> students) {
        Iterator<Student> iterator = students.stream().iterator();
        while (iterator.hasNext()){
            Student next = iterator.next();
            System.out.println(next);
        }
    }
}

