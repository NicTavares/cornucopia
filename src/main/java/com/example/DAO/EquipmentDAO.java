package com.example.DAO;

import com.example.models.Equipment;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class EquipmentDAO implements DAO<Equipment>{

    private static final Logger log = (Logger) LoggerFactory.getLogger(EquipmentDAO.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<Equipment> rowMapper = (rs, rowNum) -> {
        Equipment eq = new Equipment(rs.getString("name"));
        return eq;
    };

    public EquipmentDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Equipment> list() {
        String sql = "SELECT * FROM Equipment";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Equipment equipment) {
        String sql = "INSERT into Equipment(name) values(?)";
        int rows = jdbcTemplate.update(sql, equipment.getName());
        if(rows == 1) {
            log.info("New equipment created: " + equipment.getName());
        }
    }

    @Override
    public Optional get(String id) {
        String sql = "SELECT * FROM Equipment WHERE name = ?";
        Equipment eq = null;
        try{
            eq = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch(DataAccessException e) {
            log.info("Equipment: " + id + " not found");
        }
        return Optional.ofNullable(eq);
    }

    @Override
    public void update(Equipment equipment, String id) {
        String sql = "UPDATE equipment SET name = ? WHERE name = ?";
        int update = jdbcTemplate.update(sql, equipment.getName(), rowMapper);
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Equipment WHERE name = ?";
        int update = jdbcTemplate.update(sql, id, rowMapper);
    }
}
