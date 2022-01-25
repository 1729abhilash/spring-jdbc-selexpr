package com.abhi.api.resultsetextractor;

import com.abhi.api.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupStudentByAddressExtractor implements ResultSetExtractor<Map<String, List<String>>> {
    @Override
    public Map<String, List<String>> extractData(ResultSet res) throws SQLException, DataAccessException {
        Map<String, List<String>> studentTable=new HashMap<>();

        while(res.next()) {
            String studentName=res.getString("student_name");
            String studentAddress=res.getString("student_address");
          List<String> studentsNameList= studentTable.get(studentAddress);
          if(studentsNameList==null){
              List<String> nameList=new ArrayList<>();
              nameList.add(studentName);
              studentTable.put(studentAddress,nameList);
          }
          else{
            studentsNameList.add(studentName);
          //   studentTable.put(studentAddress,studentsName1);
          }

        }
        return studentTable;
    }
}
