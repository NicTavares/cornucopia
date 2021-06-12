package com.example.DAO;

import com.example.models.Pantry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class PantryDAO implements DAO<Pantry>{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    RowMapper<Pantry> rowMapper = (rs, rowNum) -> {
        Pantry p = new Pantry(rs.getInt("usrUUID"),rs.getString("name"),rs.getInt("quantity"));
        return p;
    };

    public PantryDAO(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Pantry> list() {
        String sql = "SELECT * FROM UsrHasIngredient";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Pantry pantry) {
        String sql = "INSERT INTO UsrHasIngredient(usrUUID,name,quantity) values(?,?,?)";
        int rows = jdbcTemplate.update(sql, pantry.getUsrUUID(),pantry.getName(),pantry.getQuantity());
    }

    @Override
    //todo
    public Optional<Pantry> get(String id) {
        return Optional.empty();
    }


    public List<Pantry> getUsrPantry(String usrUUID) {
        String sql =  "SELECT * FROM UsrHasIngredient WHERE usrUUID = ?";;

        return jdbcTemplate.query(sql, rowMapper,usrUUID);

    }

    @Override
    //todo
    public void update(Pantry pantry, String id) {

    }

    @Override
    //todo
    public void delete(String id) {

    }


}
