package com.example.DAO;

import com.example.models.Ingredient;
import com.example.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Component
public class MessageDAO implements DAO<Message> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Message> rowMapper = (rs, rowNum) -> {
        Message m = new Message(rs.getInt("UUID"),
                rs.getString("text"),
                rs.getInt("senderUUID"),
                rs.getString("sentTime"),
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
                message.getReceiverUUID()

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



    public List<Map<String, Object>> getMessageInbox(String id) {
        String sql = "SELECT u1.username as sender, u2.username as receiver, m.text as message, m.sentTime as time ";
        sql += "FROM Message as m, Usr as u1, Usr as u2 ";
        sql += "WHERE u1.UUID=m.senderUUID and u2.UUID=m.receiverUUID ";
        sql += "AND (u1.UUID=? OR u2.UUID=?)";
        List<Map<String, Object>> m = null;
        try{
            m = jdbcTemplate.queryForList(sql, id, id);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return m;
    }

    public List<Map<String, Object>> getMessageInboxByUsername(String username) {
        String sql = "SELECT u1.username as sender, u2.username as receiver, m.text as message, m.sentTime as time ";
        sql += "FROM Message m, Usr u1, Usr u2 ";
        sql += "WHERE m.senderUUID=u1.UUID and m.receiverUUID=u2.UUID ";
        sql += "AND (u1.username=? OR u2.username=?)";

        List<Map<String, Object>> m = null;
        try{
            m = jdbcTemplate.queryForList(sql, username, username);
        }catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return m;
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

    public int getNextUUID() {
        String sql = "SELECT MAX(UUID) FROM Message";
        if(jdbcTemplate.queryForObject(sql, Integer.class )==null){
            return 0;
        }
        else {
            return jdbcTemplate.queryForObject(sql, Integer.class )+1;
        }

    }
}
