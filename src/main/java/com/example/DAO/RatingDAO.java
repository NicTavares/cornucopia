package com.example.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class RatingDAO {
    private static final Logger log = LoggerFactory.getLogger(EquipmentDAO.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(int usrUUID, int recipeUUID, int score) {
        String sql = "INSERT INTO UsrRateRecipe(usrUUID,score,recipeUUID) values(?,?,?)";
        int rows = jdbcTemplate.update(sql, usrUUID,score,recipeUUID);
    }

    public Integer getScore(int recipeUUID) {
        String sql = "SELECT score FROM UsrRateRecipe WHERE recipeUUID=?";


        try{
            List<Integer> data = jdbcTemplate.query(sql, new RowMapper<Integer>(){
                public Integer mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    return rs.getInt(1);
                }
            },recipeUUID);
            int sum=0;
            for (Integer each : data) {
                sum += each;
            }
            return sum=sum/data.size();

        }catch(DataAccessException e) {}

        return null;
    }
}
