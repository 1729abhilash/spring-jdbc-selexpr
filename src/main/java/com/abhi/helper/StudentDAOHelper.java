package com.abhi.helper;

import com.abhi.api.Student;
import com.abhi.dao.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("studentDaoHelper")
public class StudentDAOHelper {
    @Autowired
    private StudentDAO studentDAOImpl;

    public void setUpStudentTable() {

        Student Student1 = new Student();
        Student1.setRollNo(20);
        Student1.setName("menne");
        Student1.setAddress("odissa");
       // studentDAOImpl.insert(Student1);

        Student Student2 = new Student();
        Student2.setRollNo(17);
        Student2.setName("jada");
        Student2.setAddress("mumbai");

        ArrayList<Student> studentList=new ArrayList<>();
        studentList.add(Student1);
        studentList.add(Student2);
        studentDAOImpl.insert(studentList);


    }

public void printStudents(List<Student> student){
        for(Student temp:student){
            System.out.println(temp);
        }
}




}
