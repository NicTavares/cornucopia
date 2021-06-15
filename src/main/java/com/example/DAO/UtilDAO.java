package com.example.DAO;

import com.example.models.Pantry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UtilDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UtilDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }


    public List<String> usrNameTakeAllCourse() {
        String sql = "select u.name from Usr u where not exists (select * from Course c where not exists " +
                "(select * from usrTakeCourse e where e.usrUUID=u.UUID and e.courseUUID=c.UUID))";

        try{
            List<String> data = jdbcTemplate.query(sql, new RowMapper<String>(){
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    return rs.getString(1);
                }
            });
            return data;
        }catch(DataAccessException e) {
            return null;
        }
    }
}
