package com.abhi.dao;

import com.abhi.api.Student;
import org.springframework.jdbc.core.JdbcTemplate;


public class StudentDAOImpl implements StudentDAO{
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {//for setter injection
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Student student) {
      ;
        //logic
        String sql="INSERT INTO STUDENT VALUES(?,?,?)";

    Object[] arg={student.getRollNo(),student.getName(),student.getAddress()};

        int noOfRowInserted=jdbcTemplate.update(sql,arg);
        System.out.println("No of row inserted "+noOfRowInserted);

    }


//    public DataSource getDataSource(){
//        String url="jdbc:mysql://localhost:3306/school";
//        String password="root";
//        String username="root";
//     DataSource dataSource = new DriverManagerDataSource(url,username,password);
//  return dataSource;
//    }
}
