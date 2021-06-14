package com.example.DAO;

import com.example.models.Administrator;
import com.example.models.Course;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CourseDAO implements DAO<Course>{
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = (Logger) LoggerFactory.getLogger(EquipmentDAO.class);

    RowMapper<Course> rowMapper = (rs, rowNum) -> {
        Course cs = new Course(rs.getInt("UUID"),
                rs.getString("text"),
                rs.getInt("length"),
                rs.getString("courseName"),
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

    @Override
    public void create(Course course) {
        String sql = "INSERT into Course(UUID, text, length, courseName, requirementName, creatorUUID) values(?, ?, ?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql,
                course.getUUID(),
                course.getText(),
                course.getLength(),
                course.getCourseName(),
                course.getRequirementName(),
                course.getCreatorUUID(),
                rowMapper
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
        String sql = "UPDATE Course SET UUID = ?, text = ?, length = ?, courseName = ?, requirementName = ?, creatorUUID = ? " +
                "WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                    course.getUUID(),
                    course.getText(),
                    course.getLength(),
                    course.getRequirementName(),
                    course.getCreatorUUID(),
                    id,
                    rowMapper
                );
    }

    @Override
    public int delete(String id) {
        String sql = "DELETE FROM Course WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql, rowMapper, id);
        return rows;
    }
}
