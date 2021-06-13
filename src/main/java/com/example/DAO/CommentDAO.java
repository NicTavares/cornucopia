package com.example.DAO;

import com.example.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Comment> rowMapper = (rs, rowNum) -> {
        Comment c = new Comment(rs.getInt("recipeUUID"),
                rs.getInt("commentNumber"),
                rs.getString("text"),
                rs.getInt("authorUUID"));
        return c;
    };


    public List<Comment> list() {
        String sql = "SELECT * FROM Comment";
        return jdbcTemplate.query(sql, rowMapper);
    }


    public void create(Comment comment) {
        String sql = "INSERT INTO Comment(recipeUUID, commentNumber, text, authorUUID) values(?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql,
                comment.getRecipeUUID(),
                comment.getCommentNumber(),
                comment.getText(),
                comment.getAuthorUUID()
        );
    }


    public List<Comment> getComment(String recipeUUID) {
        String sql = "SELECT * FROM Comment WHERE recipeUUID = ?";

        List<Comment> c = null;
        try {
            c = jdbcTemplate.query(sql, rowMapper, recipeUUID);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    public int getNextCommentNumber(String recipeUUID) {


        String sql = "SELECT MAX(commentNumber) FROM Comment WHERE recipeUUID = ?";


        if( jdbcTemplate.queryForObject(sql, Integer.class, recipeUUID)==null){
            return 0;
        }
        else{
            return jdbcTemplate.queryForObject(sql, Integer.class, recipeUUID) + 1;
        }


    }

}
