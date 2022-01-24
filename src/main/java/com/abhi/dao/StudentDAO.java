package com.abhi.dao;

import com.abhi.api.Student;

import java.util.List;

public interface StudentDAO {

    void insert(Student student);
    void insert(List<Student>students);
    boolean deleteByRecordByRollNo(int rollNo);
    int deleteRecordByStudentNameAndStudentAddress(String studentName,String studentAddress);
    List<Student>findAllStudents();
    void cleanUp();
    Student findStudentByRollNo(int rollNo);


}
