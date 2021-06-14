package com.example.DAO;

import com.example.models.Ingredient;
import com.example.models.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class MessageDAO implements DAO<Message> {

    private JdbcTemplate jdbcTemplate;

    RowMapper<Message> rowMapper = (rs, rowNum) -> {
        Message m = new Message(rs.getInt("UUID"),
                rs.getString("text"),
                rs.getInt("senderUUID"),
                rs.getDate("sentTime"),
                rs.getInt("receiverUUID")
        );
        return m;
    };

    @Override
    public List<Message> list() {
        String sql = "SELECT * FROM Message";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Message message) {
        String sql = "INSERT INTO Message(UUID, text, senderUUID, sentTime, receiverUUID) values(?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql,
                message.getUUID(),
                message.getText(),
                message.getSenderUUID(),
                message.getSentTime(),
                message.getReceiverUUID(),
                rowMapper
        );
    }

    @Override
    public Optional<Message> get(String id) {
        String sql = "SELECT * FROM Message WHERE UUID = ?";
        Message m = null;
        try{
            m = jdbcTemplate.queryForObject(sql, rowMapper, id);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(m);
    }

    @Override
    public void update(Message message, String id) {
        String sql = "UPDATE Message SET UUID = ?, text = ?, senderUUID = ?, sentTime = ?, receiverUUID = ? WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql,
                message.getUUID(),
                message.getText(),
                message.getSenderUUID(),
                message.getSentTime(),
                message.getReceiverUUID(),
                id,
                rowMapper
        );
    }

    @Override
    public int delete(String id) {
        String sql = "DELETE Message WHERE UUID = ?";
        int rows = jdbcTemplate.update(sql, rowMapper, id);
        return rows;
    }
}
