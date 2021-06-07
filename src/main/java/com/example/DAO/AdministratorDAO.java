package com.example.DAO;

import com.example.models.Administrator;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class AdministratorDAO implements DAO<Administrator>{

    private JdbcTemplate jdbcTemplate;
    private static final Logger log = (Logger) LoggerFactory.getLogger(EquipmentDAO.class);

    RowMapper<Administrator> rowMapper = (rs, rowNum) -> {
        Administrator ad = new Administrator(
                rs.getInt("UUID"),
                rs.getInt("adminLevel"),
                rs.getString("password"),
                rs.getString("email"));

        return ad;
    };

    public AdministratorDAO(JdbcTemplate template){
        this.jdbcTemplate = template;
    }

    @Override
    public List<Administrator> list() {
        String sql = "SELECT * from Administrator";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Administrator administrator) {
        String sql = "INSERT into Administrator(UUID, adminLevel, password, email) values(?, ?, ?, ?)";
        int rows = jdbcTemplate.update(
                sql, administrator.getUUID(), administrator.getAdminLevel(),
                administrator.getPassword(), administrator.getEmail());
    }

    @Override
    public Optional<Administrator> get(String id) {
        String sql = "SELECT * from Administrator WHERE UUID = ?";
        Administrator ad = null;
        try {
            ad = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            log.info("Administrator: " + id + " not found");
        }
        return Optional.ofNullable(ad);
    }

    @Override
    public void update(Administrator administrator, String id) {
        String sql = "UPDATE Administrator SET UUID = ?, adminLevel = ?, password = ?, email = ? WHERE UUID = ?";
        int update = jdbcTemplate.update(sql, administrator.getUUID(),
                administrator.getAdminLevel(), administrator.getPassword(), administrator.getEmail(), rowMapper);
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Administrator WHERE UUID = ?";
        int update = jdbcTemplate.update(sql, id, rowMapper);
    }
}
