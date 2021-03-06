package com.example.DAO;

import com.example.models.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Component
public class UsrDAO implements DAO<Usr>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Usr> rowMapper = (rs, rowNum) -> {
        Usr u = new Usr(rs.getInt("UUID"),
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
        String sql = "INSERT INTO Usr(UUID, email, username, name, password, city, postalCode) " +
                "values(?,?,?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                usr.getUUID(),
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


    public Optional<String> getPasswordByUsername(String username) {
        String sql = "SELECT password FROM Usr WHERE username = ?";
        String u = null;
        try{
            u = jdbcTemplate.queryForObject(sql, String.class, username);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(u);
    }

    @Override
    public void update(Usr usr, String id) {
        String sql = "UPDATE Usr SET UUID = ?, email = ?, username = ?, name = ?, password = ?, city = ?, postalCode = ? " +
                "WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                usr.getUUID(),
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
    public int delete(String id) {
        String sql = "DELETE FROM Usr WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,  Integer.parseInt(id));
        return rows;
    }
    public int getNextUUID() {
        String sql = "SELECT MAX(UUID) FROM Usr";

        if(jdbcTemplate.queryForObject(sql, Integer.class )==null){
            return 0;
        }
        else return jdbcTemplate.queryForObject(sql, Integer.class )+1;

    }

    public int getUUIDByUsername(String username) {
        String sql = "SELECT UUID FROM Usr where username=?";
        return jdbcTemplate.queryForObject(sql, Integer.class,username);

    }


    public void createFavouriteRecipe(int usrUUID,int recipeUUID) {
        String sql = "INSERT INTO UsrFavouriteRecipe(usrUUID,recipeUUID) VALUES(?,?)";
        int rows = jdbcTemplate.update(sql,usrUUID,recipeUUID);
    }

    public List<Integer> getFavouriteRecipes(int usrUUID) {
        String sql = "SELECT recipeUUID FROM UsrFavouriteRecipe WHERE usrUUID=?";

        try{
            List<Integer> data = jdbcTemplate.query(sql, new RowMapper<Integer>(){
                public Integer mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    return rs.getInt(1);
                }
            },usrUUID);
            return data;
        }catch(DataAccessException e) {}
        return null;
    }

    public List<Map<String,Object>> joinUsrAndTable(String username, String queryTable) {
        String sql = "SELECT u.username, v.name ";
        sql += "FROM Usr u, "+queryTable+ " f, ";

        if (queryTable.equals("UsrFavouriteRecipe")) {
            sql += "Recipe v ";
        } else if (queryTable.equals("UsrTakeCourse")) {
            sql += "Course v ";
        }

        sql += "where u.UUID=f.usrUUID AND u.username='"+username+"' AND v.UUID=f.";

        if (queryTable.equals("UsrFavouriteRecipe")) {
            sql += "recipeUUID";
        } else if (queryTable.equals("UsrTakeCourse")) {
            sql += "courseUUID";
        }
        return jdbcTemplate.queryForList(sql);
    }


}
