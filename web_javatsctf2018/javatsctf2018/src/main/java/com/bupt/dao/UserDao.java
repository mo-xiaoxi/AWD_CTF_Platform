package com.bupt.dao;

import com.bupt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private  final static String FIND_USER_SQL = " SELECT * FROM user  " +
            " WHERE username =? and password=? ";

    private  final static String UPDATE_INFO_SQL = " UPDATE user SET " +
            " password=?,score=?,checkcode=?,time=?,pdd=? WHERE id =?";

    private final static String INSERT_USER_SQL = "INSERT INTO user(username, password, email, salt, first,pdd) VALUES(?,?,?,?,?,?)";

    public int updateFirst(final int userId,final int first) {
        final String sql = " UPDATE user SET first=? WHERE id =?";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, first);
                ps.setInt(2, userId);
                return ps;
            }
        });
    }

    public int updateEmail(final int userId,final String email) {
        final String sql = " UPDATE user SET email=? WHERE id =?";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, email);
                ps.setInt(2, userId);
                return ps;
            }
        });
    }

    public User findUserByEmail(final String email) {
        String sql = " SELECT * FROM user WHERE email =?";

        final User user = new User();
        jdbcTemplate.query(sql, new Object[] { email},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setEmail(rs.getString("email"));
                        user.setSalt(rs.getString("salt"));
                        user.setScore(rs.getInt("score"));
                        user.setCheckcode(rs.getString("checkcode"));
                        user.setTime(rs.getString("time"));
                        user.setFirst(rs.getInt("first"));
                        user.setPdd(rs.getString("pdd"));
                    }
                });
        return user;
    }

    public User findUserByUsername(final String username) {
        String sql = " SELECT * FROM user WHERE username =?";

        final User user = new User();
        jdbcTemplate.query(sql, new Object[] { username},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setEmail(rs.getString("email"));
                        user.setSalt(rs.getString("salt"));
                        user.setScore(rs.getInt("score"));
                        user.setCheckcode(rs.getString("checkcode"));
                        user.setTime(rs.getString("time"));
                        user.setFirst(rs.getInt("first"));
                        user.setPdd(rs.getString("pdd"));
                    }
                });
        return user;
    }

    public User findUserByUsernameAndPassword(final String username, final String password) {
        final User user = new User();
        jdbcTemplate.query(FIND_USER_SQL, new Object[] { username, password},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setId(rs.getInt("id"));
                        user.setUsername(username);
                        user.setEmail(rs.getString("email"));
                        user.setSalt(rs.getString("salt"));
                        user.setScore(rs.getInt("score"));
                        user.setCheckcode(rs.getString("checkcode"));
                        user.setTime(rs.getString("time"));
                        user.setFirst(rs.getInt("first"));
                        user.setPdd(rs.getString("pdd"));
                    }
                });
        return user;
    }

    public int addUser(final User user){
        return jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getSalt());
                ps.setInt(5, user.getFirst());
                ps.setString(6,user.getPdd());
                return ps;
            }
        });
    }

    public int updateUser(final User user) {
        return jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_INFO_SQL);
                ps.setString(1, user.getPassword());
                ps.setInt(2, user.getScore());
                ps.setString(3, user.getCheckcode());
                ps.setString(4, user.getTime());
                ps.setString(5,user.getPdd());
                ps.setInt(6, user.getId());
                return ps;
            }
        });
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
