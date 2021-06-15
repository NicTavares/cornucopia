package com.example.DAO;

import com.example.models.Administrator;
import com.example.models.Course;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
@Component
public class CourseDAO implements DAO<Course>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Course> rowMapper = (rs, rowNum) -> {
        Course cs = new Course(rs.getInt("UUID"),
                rs.getString("text"),
                rs.getInt("length"),
                rs.getString("name"),
                rs.getString("requirementName"),
                rs.getInt("creatorUUID"));
        return cs;
    };

    public CourseDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Course> list() {
        String sql = "SELECT * FROM Course";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Map<String,Object>> projection(List<String> fields) {
        String sql = "SELECT ";
        int i;
        for(i = 0; i < fields.size()-1; i++){
            sql += fields.get(i);
            sql += ", ";
        }
        sql += fields.get(i);
        sql += " FROM Course";
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT into Course(UUID, text, length, name, requirementName, creatorUUID) values(?, ?, ?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql,
                course.getUUID(),
                course.getText(),
                course.getLength(),
                course.getName(),
                course.getRequirementName(),
                course.getCreatorUUID()
        );
    }

    @Override
    public Optional<Course> get(String id) {
        String sql = "SELECT * FROM Course WHERE UUID = ?";
        Course cs = null;
        try {
            cs = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(cs);
    }

    @Override
    public void update(Course course, String id) {
        String sql = "UPDATE Course SET UUID = ?, text = ?, length = ?, name = ?, requirementName = ?, creatorUUID = ? " +
                "WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                    course.getUUID(),
                    course.getText(),
                    course.getLength(),
                    course.getName(),
                    course.getRequirementName(),
                    course.getCreatorUUID(),
                    id
                );
    }

    @Override
    public int delete(String id) {
        String sql = "DELETE FROM Course WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql, rowMapper, id);
        return rows;
    }
    public int getNextUUID() {
        String sql = "SELECT MAX(UUID) FROM Course";
        if(jdbcTemplate.queryForObject(sql, Integer.class )==null){
            return 0;
        }
        else {
            return jdbcTemplate.queryForObject(sql, Integer.class )+1;
        }

    }

    public void usrTakeCourse(int usrUUID,int courseUUID) {
        String sql = "INSERT INTO usrTakeCourse(usrUUID,courseUUID) VALUES (?,?) ";
        jdbcTemplate.update(sql, usrUUID, courseUUID);
    }




}
