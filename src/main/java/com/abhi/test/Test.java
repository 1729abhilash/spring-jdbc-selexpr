package com.abhi.test;

import com.abhi.api.Student;

import com.abhi.dao.StudentDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
    ApplicationContext context =new ClassPathXmlApplicationContext("classpath:beans.xml");
        System.out.println("application Context gets loaded");
          StudentDAOImpl studentDAOImpl= context.getBean("studentDao",StudentDAOImpl.class);

        Student newStudent1=new Student();
        newStudent1.setRollNo(005);
        newStudent1.setName("karan");
        newStudent1.setAddress("odissa");
       studentDAOImpl.insert(newStudent1);


    }
}
