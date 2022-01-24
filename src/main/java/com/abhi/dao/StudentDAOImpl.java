package com.abhi.dao;

import com.abhi.api.Student;
import com.abhi.api.row.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentRowMapper rowMapper;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {//for setter injection
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public void insert(Student student) {
      ;
        //logic
        String sql="INSERT INTO STUDENT VALUES(?,?,?)";

    Object[] arg={student.getRollNo(),student.getName(),student.getAddress()};

        int noOfRowInserted=jdbcTemplate.update(sql,arg);
        System.out.println("No of row inserted "+noOfRowInserted);

    }

    @Override
    public void insert(List<Student> students) {
        String sql="INSERT INTO STUDENT(roll_no,student_name,student_address) VALUES(?,?,?)";
       ArrayList<Object[]> sqlArgs=new ArrayList<>();
       for(Student tempStudent:students){
           Object[] studentData={tempStudent.getRollNo(),tempStudent.getName(),tempStudent.getAddress()};
           sqlArgs.add(studentData);
       }
   jdbcTemplate.batchUpdate(sql, sqlArgs);
        System.out.println("Batch update completed");
    }

    @Override
    public boolean deleteByRecordByRollNo(int rollNo) {
        String sql="DELETE FROM STUDENT WHERE roll_no=?";

        int noOfRowsDeleted=jdbcTemplate.update(sql,rollNo);

        System.out.println("No of record Got delted is "+noOfRowsDeleted);
        return noOfRowsDeleted==1;
    }

    @Override
    public int deleteRecordByStudentNameAndStudentAddress(String studentName, String studentAddress) {
        String sql="DELETE FROM STUDENT WHERE student_name = ? OR student_address = ?";
        Object [] arguments={studentName,studentAddress};
        int noOfRowsDeleted=jdbcTemplate.update(sql,arguments);

        System.out.println("No of rows Got delted are "+noOfRowsDeleted);
       return noOfRowsDeleted;
    }

    @Override
    public List<Student> findAllStudents() {

        String sql="SELECT * FROM STUDENT";
//        List<Student>studentList=jdbcTemplate.query(sql,rowMapper);
       //or
        List<Student>studentList=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Student>(Student.class));
        return studentList;
    }

    @Override
    public void cleanUp() {
        String sql="TRUNCATE TABLE STUDENT";
        jdbcTemplate.update(sql);
        System.out.println("Table cleaned up>>");
    }

    @Override
    public Student findStudentByRollNo(int rollNo) {
        String sql="SELECT * FROM STUDENT WHERE ROLL_NO =?";
     Student student=jdbcTemplate.queryForObject(sql,rowMapper,rollNo);
    return student;
    }


//    public DataSource getDataSource(){//doing this by now creaintg bean
//        String url="jdbc:mysql://localhost:3306/school";
//        String password="root";
//        String username="root";
//     DataSource dataSource = new DriverManagerDataSource(url,username,password);
//  return dataSource;
//    }
}
