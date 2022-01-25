package com.abhi.dao;

import com.abhi.api.Student;

import java.util.List;
import java.util.Map;

public interface StudentDAO {

    void insert(Student student);
    void insert(List<Student>students);
    boolean deleteByRecordByRollNo(int rollNo);
    int deleteRecordByStudentNameAndStudentAddress(String studentName,String studentAddress);
    List<Student>findAllStudents();
    void cleanUp();
    Student findStudentByRollNo(int rollNo);
    List<Student> findStudentByName(String name);
     Map<String,List<String>> groupStudentByAddress();
    int updateStudent(Student student);
    int updateStudent(List<Student> studentList);
}
