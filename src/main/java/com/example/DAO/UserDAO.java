package com.example.DAO;

import com.example.models.Course;
import com.example.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User>{
    private JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User u = new User(rs.getInt("UUID"),
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

    public UserDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<User> list() {
        String sql = "SELECT * FROM User";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO User(UUID, birthday, email, username, name, password, city, postalCode) " +
                "values(?,?,?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                user.getUUID(),
                user.getBirthday(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getCity(),
                user.getPostalCode(),
                rowMapper
                );
    }

    @Override
    public Optional<User> get(String id) {
        String sql = "SELECT * FROM User WHERE UUID = ?";
        User u = null;
        try{
            u = jdbcTemplate.queryForObject(sql, rowMapper, id);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(User user, String id) {
        String sql = "UPDATE User SET UUID = ?, bithday = ?, email = ?, username = ?, name = ?, password = ?, city = ?, " +
                "postalCode = ? WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                user.getUUID(),
                user.getBirthday(),
                user.getEmail(),
                user.getUsername(),
                user.getName(),
                user.getPassword(),
                user.getCity(),
                user.getPostalCode(),
                rowMapper
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM User WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql, rowMapper, id);
    }
}
