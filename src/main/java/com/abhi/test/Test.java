package com.abhi.test;

import com.abhi.api.Student;

import com.abhi.dao.StudentDAOImpl;
import com.abhi.helper.StudentDAOHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
    ApplicationContext context =new ClassPathXmlApplicationContext("classpath:beans.xml");
        System.out.println("application Context gets loaded");
//          StudentDAOImpl studentDAOImpl= context.getBean("studentDao",StudentDAOImpl.class);

//        Student newStudent1=new Student();
//        newStudent1.setRollNo(006);
//        newStudent1.setName("raihan");
//        newStudent1.setAddress("odissa");
//       studentDAOImpl.insert(newStudent1);


        StudentDAOHelper studentDAOHelper= context.getBean("studentDaoHelper", StudentDAOHelper.class);//dont need to
        // create student class
//setting up the table
        studentDAOHelper.setUpStudentTable();


        //call the findAllStudent()//fetching data form the table
        StudentDAOImpl studentDAOImpl= context.getBean("studentDao",StudentDAOImpl.class);
        List<Student> students=studentDAOImpl.findAllStudents();
         studentDAOHelper.printStudents(students);

         //quering for  a specific object
        System.out.println("Fetchin th student with the roll n");
        System.out.println(studentDAOImpl.findStudentByRollNo(17));
    //clean up the table data
        studentDAOImpl.cleanUp();
    }
}
