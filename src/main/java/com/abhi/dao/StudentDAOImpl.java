package com.abhi.dao;

import com.abhi.api.Student;
import com.abhi.api.resultsetextractor.GroupStudentByAddressExtractor;
import com.abhi.api.resultsetextractor.StudentResultSetExtractor;
import com.abhi.api.row.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentRowMapper rowMapper;
    int updateRowCount=0;


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
//whenever we perform dml opeation we should use update method
    //when we are performing ddl operration we should use execute
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
//        jdbcTemplate.update(sql);//it will work too
        jdbcTemplate.execute(sql);//recommemnded for ddl opearration
        System.out.println("Table cleaned up>>");
    }

    @Override
    public Student findStudentByRollNo(int rollNo) {
        String sql="SELECT * FROM STUDENT WHERE ROLL_NO =?";
     Student student=jdbcTemplate.queryForObject(sql,rowMapper,rollNo);
    return student;
    }

    @Override
    public List<Student> findStudentByName(String name) {
        String sql="SELECT * FROM STUDENT WHERE STUDENT_NAME=?";
        List<Student> students=jdbcTemplate.query(sql,new StudentResultSetExtractor(),name);
   return students;
    }

    @Override
    public Map<String, List<String>> groupStudentByAddress() {
        String sql="SELECT * FROM STUDENT";
        Map<String, List<String>> query= jdbcTemplate.query(sql,new GroupStudentByAddressExtractor());
 return query;
    }

    @Override
    public int updateStudent(Student student) {
    String sql=" update school.Student set student_address=? where roll_no=?";
      int rowsAffected=jdbcTemplate.update(sql,student.getAddress(),student.getRollNo());
        System.out.println("row updated->>>>>>>>");
      return rowsAffected;
    }

    @Override
    //@Transactional
    public int updateStudent(List<Student> studentList) {

        String sql="UPDATE Student SET student_address=? where roll_no=?";
         int [] batchUpdate=jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
             @Override
             public void setValues(PreparedStatement ps, int index) throws SQLException {
//i needt to set the args for the preparedStatement

                 ps.setString(1,studentList.get(index).getAddress());
                   ps.setInt(2,studentList.get(index).getRollNo());
                 System.out.println("inside setValues method");

             }

             @Override
             public int getBatchSize() {
                //in this method we ened to define how many times our query will execute
                 //how many times the setValue() is going to exectue
                 System.out.println("inside getBatchSize() emthod>>>setvalue() method run this many times");
               return studentList.size();
             }
         });

         for(int i=0;i<batchUpdate.length;i++){
               if(batchUpdate[i]==1) {

                   updateRowCount++;
               }
         }

        return updateRowCount;
    }


//    public DataSource getDataSource(){//doing this by now creaintg bean
//        String url="jdbc:mysql://localhost:3306/school";
//        String password="root";
//        String username="root";
//     DataSource dataSource = new DriverManagerDataSource(url,username,password);
//  return dataSource;
//    }
}
