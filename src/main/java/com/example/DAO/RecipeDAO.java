package com.example.DAO;

import com.example.models.Recipe;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class RecipeDAO implements DAO<Recipe>{
    private JdbcTemplate jdbcTemplate;

    RowMapper<Recipe> rowMapper = (rs, rowNum) -> {
        Recipe r = new Recipe(rs.getInt("UUID"),
                rs.getString("text"),
                rs.getFloat("averageScore"),
                rs.getFloat("estimatedTime"),
                rs.getInt("uploaderUUID")
        );
        return r;
    };

    public RecipeDAO(JdbcTemplate template){
        this.jdbcTemplate = template;
    }

    @Override
    public List<Recipe> list() {
        String sql = "SELECT * FROM Recipe";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Recipe recipe) {
        String sql = "INSERT INTO Recipe(UUID, text, averageScore, estimatedTime, uploaderUUID) values(?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                recipe.getUUID(),
                recipe.getText(),
                recipe.getAverageScore(),
                recipe.getEstimatedTime(),
                recipe.getUploaderUUID(),
                rowMapper
        );
    }

    @Override
    public Optional<Recipe> get(String id) {
        String sql = "SELECT * FROM Recipe WHERE UUID = ?";
        Recipe r = null;
        try{
            r = jdbcTemplate.queryForObject(sql, rowMapper, id);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(r);
    }

    @Override
    public void update(Recipe recipe, String id) {
        String sql = "UPDATE Recipe SET UUID = ?, text = ?, averageScore = ?, estimatedTime = ?, uploaderUUID = ? WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                recipe.getUUID(),
                recipe.getText(),
                recipe.getAverageScore(),
                recipe.getEstimatedTime(),
                recipe.getUploaderUUID(),
                id,
                rowMapper
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE Recipe WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql, rowMapper, id);
    }
}