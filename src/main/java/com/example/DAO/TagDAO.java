package com.example.DAO;

import com.example.models.Course;
import com.example.models.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class TagDAO implements DAO<Tag>{

    private JdbcTemplate jdbcTemplate;
    RowMapper<Tag> rowMapper = (rs, rowNum) -> {
        Tag t = new Tag(rs.getString("name"));
        return t;
    };

    public TagDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Tag> list() {
        String sql = "SELECT * FROM Tag";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Tag tag) {
        String sql = "INSERT INTO Tag(name) values(?)";
        int rows = jdbcTemplate.update(sql, tag.getName());
    }

    @Override
    public Optional<Tag> get(String id) {
        String sql = "SELECT * FROM Tag WHERE name = ?";
        Tag t = null;
        try{
            t = jdbcTemplate.queryForObject(sql, rowMapper, id);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(t);
    }

    @Override
    public void update(Tag tag, String id) {
        String sql = "UPDATE Tag SET name = ? WHERE name = ?";
        int rows = jdbcTemplate.update(sql, tag.getName(), id);
    }

    @Override
    public int delete(String id) {
        String sql = "DELETE TAG Where name = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows;
    }
}
