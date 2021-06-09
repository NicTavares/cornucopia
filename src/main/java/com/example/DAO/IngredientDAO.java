package com.example.DAO;

import com.example.models.Course;
import com.example.models.Ingredient;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class IngredientDAO implements DAO<Ingredient>{
    private JdbcTemplate jdbcTemplate;
    RowMapper<Ingredient> rowMapper = (rs, rowNum) -> {
        Ingredient i = new Ingredient(rs.getString("name"));
        return i;
    };


    public IngredientDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Ingredient> list() {
        String sql = "SELECT * FROM Ingredient";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Ingredient ingredient) {
        String sql = "INSERT INTO Ingredient(name) values(?)";
        int rows = jdbcTemplate.update(sql, rowMapper, ingredient.getName());
    }

    @Override
    public Optional<Ingredient> get(String id) {
        String sql = "SELECT * FROM Ingredient WHERE name = ?";
        Ingredient i = null;
        try{
            i = jdbcTemplate.queryForObject(sql, rowMapper, id);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(i);
    }

    @Override
    public void update(Ingredient ingredient, String id) {
        String sql = "UPDATE Ingredient SET name = ? WHERE name = ?";
        int rows = jdbcTemplate.update(sql, rowMapper, ingredient.getName(), id);
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Ingredient WHERE name = ?";
        int rows = jdbcTemplate.update(sql, rowMapper, id);
    }
}