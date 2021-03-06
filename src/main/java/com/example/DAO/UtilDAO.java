package com.example.DAO;

import com.example.models.Recipe;
import com.example.models.Usr;
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

        String sql = String.format
                ("select temp.UUID,temp.name,temp.value from (SELECT UUID,name ,count(%s.%s) as value FROM Usr left join %s on usr.UUID= %s.%s group by UUID,name )as temp where value %s %f;", table,attribute2,table, table,attribute,operator,value);

        RowMapper<UsrStatistics> rowMapper = (rs, rowNum) -> {
            UsrStatistics r = new UsrStatistics(rs.getInt("UUID"),
                    rs.getString("name"),
                    rs.getInt("value")
            );
            return r;
        };


        return jdbcTemplate.query(sql, rowMapper);

    }

    public List<Usr> getUsrDashboard(String field) {
        String table;
        String table2;
        String attribute;
        if(field.equals("course")){
            table="Course";
            table2="usrTakeCourse";
            attribute="usrUUID";
        }else{
            table="Recipe";
            table2="Comment";
            attribute="authorUUID";
        }
        RowMapper<Usr> rowMapper = (rs, rowNum) -> {
            Usr u = new Usr(rs.getInt("UUID"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("city"),
                    rs.getString("postalCode")
            );
            return u;
        };
        String sql = String.format("select * from Usr u where not exists (select * from %s c where not exists (select * from %s e where e.%s=u.UUID and e.%sUUID=c.UUID)) ",table,table2,attribute,table);
        return jdbcTemplate.query(sql, rowMapper);
    }



}
