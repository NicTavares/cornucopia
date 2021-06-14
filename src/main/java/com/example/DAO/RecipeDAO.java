package com.example.DAO;

import com.example.models.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeDAO implements DAO<Recipe>{
    private JdbcTemplate jdbcTemplate;

    RowMapper<Recipe> rowMapper = (rs, rowNum) -> {
        Recipe r = new Recipe(rs.getInt("UUID"),
                rs.getString("name"),
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

    public List<Recipe> searchWhere(String field, String operator, float value) {
        String sql = "SELECT * FROM Recipe WHERE";
        String temp = " ";
        temp += field;
        temp += " ";
        temp += operator;
        temp += " " + Float.toString(value);
        sql += temp;
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Recipe recipe) {
        String sql = "INSERT INTO Recipe(UUID, name,text, averageScore, estimatedTime, uploaderUUID) values(?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                recipe.getUUID(),
                recipe.getName(),
                recipe.getText(),
                recipe.getAverageScore(),
                recipe.getEstimatedTime(),
                recipe.getUploaderUUID()
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
        String sql = "UPDATE Recipe  SET name = ?, text = ?, averageScore = ?, estimatedTime = ? WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                recipe.getName(),
                recipe.getText(),
                recipe.getAverageScore(),
                recipe.getEstimatedTime(),
                Integer.parseInt(id)
        );
    }

    @Override
    public int delete(String id) {
        String sql = "DELETE FROM Recipe WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows;
    }

    public int getNextUUID() {
        String sql = "SELECT MAX(UUID) FROM Recipe";
        if(jdbcTemplate.queryForObject(sql, Integer.class )==null){
            return 0;
        }
        else {
            return jdbcTemplate.queryForObject(sql, Integer.class )+1;
        }

    }


    public void createRecipeTag(int recipeUUID,String tagName) {
        String sql = "INSERT INTO RecipeHasTag(UUID, name) values(?,?)";
        int rows = jdbcTemplate.update(sql,
                recipeUUID,tagName
        );
    }
    public void createRecipeIngredient(int recipeUUID,String ingredientName) {
        String sql = "INSERT INTO RecipeHasIngredient(UUID, name) values(?,?)";
        int rows = jdbcTemplate.update(sql,
                recipeUUID,ingredientName
        );
    }
    public void createRecipeEquipment(int recipeUUID,String equipmentName) {
        String sql = "INSERT INTO RecipeHasEquipment(UUID, name) values(?,?)";
        int rows = jdbcTemplate.update(sql,
                recipeUUID, equipmentName
        );
    }

    public void createRecipeTechnique(int recipeUUID, String techniqueName) {
        String sql = "INSERT INTO RecipeHasTechnique(UUID, name) values(?,?)";
        int rows = jdbcTemplate.update(sql,
                recipeUUID, techniqueName
        );
    }


    public void updateScore(String recipeUUID, int score) {
        String sql = "UPDATE Recipe SET averageScore = ? WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                score,
                Integer.parseInt(recipeUUID)
        );
    }

    RowMapper<RecipeTag> tagMapper = (rs, rowNum) -> {
        RecipeTag t = new RecipeTag();
        t.setName(rs.getString("name"));
        t.setUUID(rs.getInt("UUID"));
        return t;
    };

    public List<RecipeTag> getTagsByRecipe(String UUID) {
        String sql = "SELECT * FROM RecipeHasTags WHERE UUID = ?";
        return jdbcTemplate.query(sql, tagMapper, UUID);
    }

    RowMapper<RecipeIngredient> ingredientMapper = (rs, rowNum) -> {
        RecipeIngredient t = new RecipeIngredient();
        t.setName(rs.getString("name"));
        t.setUUID(rs.getInt("UUID"));
        return t;
    };

    public List<RecipeIngredient> getIngredientsByRecipe(String UUID) {
        String sql = "SELECT * FROM RecipeHasIngredients WHERE UUID = ?";
        return jdbcTemplate.query(sql, ingredientMapper, UUID);
    }

    RowMapper<RecipeEquipment> eqMapper = (rs, rowNum) -> {
        RecipeEquipment t = new RecipeEquipment();
        t.setName(rs.getString("name"));
        t.setUUID(rs.getInt("UUID"));
        return t;
    };

    public List<RecipeEquipment> getEquipmentByRecipe(String UUID) {
        String sql = "SELECT * FROM RecipeHasEquipment WHERE UUID = ?";
        return jdbcTemplate.query(sql, eqMapper, UUID);
    }

    RowMapper<RecipeTechnique> tecMapper = (rs, rowNum) -> {
        RecipeTechnique t = new RecipeTechnique();
        t.setName(rs.getString("name"));
        t.setUUID(rs.getInt("UUID"));
        return t;
    };

    public List<RecipeTechnique> getTechniqueByRecipe(String UUID) {
        String sql = "SELECT * FROM RecipeHasTechnique WHERE UUID = ?";
        return jdbcTemplate.query(sql, tecMapper, UUID);
    }


}
