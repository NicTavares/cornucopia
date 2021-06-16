package com.example.DAO;

import com.example.models.Recipe;
import com.example.models.UsrStatistics;
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

    public List<Recipe> searchStatistics(String field, String operator) {
        String sql =String.format( "SELECT * FROM Recipe where %s =(select %s(%s) from Recipe)", field,operator,field);
        RowMapper<Recipe> rowMapper = (rs, rowNum) -> {
            Recipe r = new Recipe(rs.getInt("UUID"),
                    rs.getString("name"),
                    rs.getString("text"),
                    rs.getFloat("averageScore"),
                    rs.getFloat("estimatedTime"),
                    rs.getInt("uploaderUUID")
            );
            return r;
        };


        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<UsrStatistics> getUsrStatistics(String field, String operator, float value) {
        String table;
        String attribute;
        String attribute2;
        if (field.equals("course")) {
            table = "UsrTakeCourse";
            attribute = "usrUUID";
            attribute2="courseUUID";
        } else {
            table = "Comment";
            attribute = "authorUUID";
            attribute2="text";
        }

        String sql = String.format("SELECT UUID,name ,count(%s.%s) as value FROM Usr left join %s on usr.UUID= %s.%s group by UUID,name having count(%s.%s) %s %f;", table,attribute2,table, table,attribute, table,attribute2,operator,value);

        RowMapper<UsrStatistics> rowMapper = (rs, rowNum) -> {
            UsrStatistics r = new UsrStatistics(rs.getInt("UUID"),
                    rs.getString("name"),
                    rs.getInt("value")
            );
            return r;
        };


        return jdbcTemplate.query(sql, rowMapper);
    }
}
