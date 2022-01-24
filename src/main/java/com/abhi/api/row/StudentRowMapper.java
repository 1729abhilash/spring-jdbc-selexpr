package com.abhi.api.row;

import com.abhi.api.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("rowMapper")
public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
       Student student = new Student();
       student.setRollNo(rs.getInt("roll_no"));
      student.setName(rs.getString("student_name"));
      student.setAddress(rs.getString("student_address"));
        System.out.println("mapRow():called");
      return student;
    }
}
