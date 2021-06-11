package com.example.DAO;

import com.example.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class CommentDAO implements DAO<Comment>{

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Comment> rowMapper = (rs, rowNum) -> {
        Comment c = new Comment(rs.getInt("recipeUUID"),
                rs.getInt("commentNumber"),
                rs.getString("text"),
                rs.getInt("authorUUID"));
        return c;
    };

    @Override
    public List<Comment> list() {
        String sql = "SELECT * FROM Comments";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Comment comment) {
        String sql = "INSERT INTO Comments(recipeUUID, commentNumber, text, authorUUID) values(?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql,
                comment.getRecipeUUID(),
                comment.getCommentNumber(),
                comment.getText(),
                comment.getAuthorUUID()
        );
    }

    @Override
    public Optional<Comment> get(String id) {
        String sql = "SELECT * FROM Comments WHERE commentNumber = ? AND recipeUUID = ?";
        String[] keys = id.split(" ");
        Comment c = null;
        try{
            c = jdbcTemplate.queryForObject(sql, rowMapper, keys[0], keys[1]);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(c);
    }

    @Override
    public void update(Comment comment, String id) {

    }

    @Override
    public void delete(String id) {

    }
}
