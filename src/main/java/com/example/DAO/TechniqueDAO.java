package com.example.DAO;

import com.example.models.Technique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class TechniqueDAO implements DAO<Technique> {
    private static final Logger log = LoggerFactory.getLogger(TechniqueDAO.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Technique> rowMapper = (rs, rowNum) -> {
        Technique eq = new Technique(rs.getString("name"),rs.getInt("difficulty"));
        return eq;
    };

    public TechniqueDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Technique> list() {
        String sql = "SELECT * FROM Technique";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Technique technique) {

        String sql = "INSERT into Technique(name,difficulty) values(?,?)";
        int rows = jdbcTemplate.update(sql, technique.getName(),5);
    }

    @Override
    public Optional get(String name) {
        String sql = "SELECT * FROM Technique WHERE name = ?";
        Technique eq = null;
        try{
            eq = jdbcTemplate.queryForObject(sql, rowMapper, name);
        }catch(DataAccessException e) {
            log.info("Technique: " + name + " not found");
        }
        return Optional.ofNullable(eq);
    }

    @Override
    public void update(Technique technique, String id) {
        String sql = "UPDATE technique SET name = ? WHERE name = ?";
        int update = jdbcTemplate.update(sql, technique.getName());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Technique WHERE name = ?";
        int update = jdbcTemplate.update(sql, id);
    }
}
