package com.example.DAO;

import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UsrDAO implements DAO<Usr>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Usr> rowMapper = (rs, rowNum) -> {
        Usr u = new Usr(rs.getInt("UUID"),
                rs.getDate("birthday"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("city"),
                rs.getString("postalCode")
        );
        return u;
    };

    public UsrDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Usr> list() {
        String sql = "SELECT * FROM Usr";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Usr usr) {
        String sql = "INSERT INTO Usr(UUID, birthday, email, username, name, password, city, postalCode) " +
                "values(?,?,?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                usr.getUUID(),
                usr.getBirthday(),
                usr.getEmail(),
                usr.getUsername(),
                usr.getName(),
                usr.getPassword(),
                usr.getCity(),
                usr.getPostalCode()
        );
    }

    @Override
    public Optional<Usr> get(String id) {
        String sql = "SELECT * FROM Usr WHERE UUID = ?";
        Usr u = null;
        try{
            u = jdbcTemplate.queryForObject(sql, rowMapper, id);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(u);
    }

    @Override
    public void update(Usr usr, String id) {
        String sql = "UPDATE Usr SET UUID = ?, birthday = ?, email = ?, username = ?, name = ?, password = ?, city = ?, postalCode = ? " +
                "WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                usr.getUUID(),
                usr.getBirthday(),
                usr.getEmail(),
                usr.getUsername(),
                usr.getName(),
                usr.getPassword(),
                usr.getCity(),
                usr.getPostalCode(),
                Integer.parseInt(id)
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Usr WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,  Integer.parseInt(id));
    }
    public int geNextUUID() {
        String sql = "SELECT MAX(UUID) FROM Usr";
        return jdbcTemplate.queryForObject(sql, Integer.class )+1;

    }

}
