package com.bupt.dao;

import com.bupt.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProfileDao implements Serializable {


    private JdbcTemplate jdbcTemplate;

    private final static String FIND_PROFILE_SQL = " SELECT * FROM profile  " +
            " WHERE user_id =?";

    private final static String UPDATE_PROFILE_SQL = " UPDATE profile SET " +
            " user_id=?,name=?,gender=?,birthday=?,province=?,nation=?,politics_status=?,IDnumber=?,type=?,school=?,grade=?,address=?,postcode=?,phone=?,height=?,weight=?,mail=?,father_name=?,father_job=?,father_phone=?,mother_name=?,mother_job=?,mother_phone=?,hobby=?,description=?,photo=?" +
            " WHERE id =?";

    private final static String INSERT_PROFILE_SQL = "INSERT INTO profile(user_id,name,gender,birthday,province,nation,politics_status,IDnumber,type,school,grade,address,postcode,phone,height,weight,mail,father_name,father_job,father_phone,mother_name,mother_job,mother_phone,hobby,description, photo) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public List<Profile> findAll() {
        String sql = "SELECT * FROM profile";
        return jdbcTemplate.query(sql, new RowMapper<Profile>() {
            public Profile mapRow(ResultSet rs, int i) throws SQLException {
                Profile profile = new Profile();
                profile.setId(rs.getInt("id"));
                profile.setUser_id(rs.getInt("user_id"));
                profile.setName(rs.getString("name"));
                profile.setGender(rs.getString("gender"));
                profile.setBirthday(rs.getString("birthday"));
                profile.setProvince(rs.getString("province"));
                profile.setNation(rs.getString("nation"));
                profile.setPolitics_status(rs.getString("politics_status"));
                profile.setIDnumber(rs.getString("IDnumber"));
                profile.setType(rs.getString("type"));
                profile.setSchool(rs.getString("school"));
                profile.setGrade(rs.getString("grade"));
                profile.setAddress(rs.getString("address"));
                profile.setPostcode(rs.getString("postcode"));
                profile.setHeight(rs.getString("height"));
                profile.setWeight(rs.getString("weight"));
                profile.setMail(rs.getString("mail"));
                profile.setFather_name(rs.getString("father_name"));
                profile.setFather_job(rs.getString("father_job"));
                profile.setFather_phone(rs.getString("father_phone"));
                profile.setMother_name(rs.getString("mother_name"));
                profile.setMother_job(rs.getString("mother_job"));
                profile.setMother_phone(rs.getString("mother_phone"));
                profile.setHobby(rs.getString("hobby"));
                profile.setDescription(rs.getString("description"));
                profile.setPhone(rs.getString("phone"));
                profile.setPhoto(rs.getString("photo"));
                return profile;
            }
        });
    }



    public Profile findProfileByUserId(final Integer userId) {
        final Profile profile = new Profile();
        jdbcTemplate.query(FIND_PROFILE_SQL, new Object[]{userId},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        profile.setId(rs.getInt("id"));
                        profile.setUser_id(rs.getInt("user_id"));
                        profile.setName(rs.getString("name"));
                        profile.setGender(rs.getString("gender"));
                        profile.setBirthday(rs.getString("birthday"));
                        profile.setProvince(rs.getString("province"));
                        profile.setNation(rs.getString("nation"));
                        profile.setPolitics_status(rs.getString("politics_status"));
                        profile.setIDnumber(rs.getString("IDnumber"));
                        profile.setType(rs.getString("type"));
                        profile.setSchool(rs.getString("school"));
                        profile.setGrade(rs.getString("grade"));
                        profile.setAddress(rs.getString("address"));
                        profile.setPostcode(rs.getString("postcode"));
                        profile.setHeight(rs.getString("height"));
                        profile.setWeight(rs.getString("weight"));
                        profile.setMail(rs.getString("mail"));
                        profile.setFather_name(rs.getString("father_name"));
                        profile.setFather_job(rs.getString("father_job"));
                        profile.setFather_phone(rs.getString("father_phone"));
                        profile.setMother_name(rs.getString("mother_name"));
                        profile.setMother_job(rs.getString("mother_job"));
                        profile.setMother_phone(rs.getString("mother_phone"));
                        profile.setHobby(rs.getString("hobby"));
                        profile.setDescription(rs.getString("description"));
                        profile.setPhone(rs.getString("phone"));
                        profile.setPhoto(rs.getString("photo"));
                    }
                });
        return profile;
    }

    public int addProfile(final Profile profile) {
        return jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_PROFILE_SQL);
                ps.setInt(1, profile.getUser_id());
                ps.setString(2, profile.getName());
                ps.setString(3, profile.getGender());
                ps.setString(4, profile.getBirthday());
                ps.setString(5, profile.getProvince());
                ps.setString(6, profile.getNation());
                ps.setString(7, profile.getPolitics_status());
                ps.setString(8, profile.getIDnumber());
                ps.setString(9, profile.getType());
                ps.setString(10, profile.getSchool());
                ps.setString(11, profile.getGrade());
                ps.setString(12, profile.getAddress());
                ps.setString(13, profile.getPostcode());
                ps.setString(14, profile.getPhone());
                ps.setString(15, profile.getHeight());
                ps.setString(16, profile.getWeight());
                ps.setString(17, profile.getMail());
                ps.setString(18, profile.getFather_name());
                ps.setString(19, profile.getFather_job());
                ps.setString(20, profile.getFather_phone());
                ps.setString(21, profile.getMother_name());
                ps.setString(22, profile.getMother_job());
                ps.setString(23, profile.getMother_phone());
                ps.setString(24, profile.getHobby());
                ps.setString(25, profile.getDescription());
                ps.setString(26, profile.getPhoto());
                return ps;
            }
        });
    }

    public int editProfile(final Profile profile) {
        return jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_PROFILE_SQL);
                ps.setInt(1, profile.getUser_id());
                ps.setString(2, profile.getName());
                ps.setString(3, profile.getGender());
                ps.setString(4, profile.getBirthday());
                ps.setString(5, profile.getProvince());
                ps.setString(6, profile.getNation());
                ps.setString(7, profile.getPolitics_status());
                ps.setString(8, profile.getIDnumber());
                ps.setString(9, profile.getType());
                ps.setString(10, profile.getSchool());
                ps.setString(11, profile.getGrade());
                ps.setString(12, profile.getAddress());
                ps.setString(13, profile.getPostcode());
                ps.setString(14, profile.getPhone());
                ps.setString(15, profile.getHeight());
                ps.setString(16, profile.getWeight());
                ps.setString(17, profile.getMail());
                ps.setString(18, profile.getFather_name());
                ps.setString(19, profile.getFather_job());
                ps.setString(20, profile.getFather_phone());
                ps.setString(21, profile.getMother_name());
                ps.setString(22, profile.getMother_job());
                ps.setString(23, profile.getMother_phone());
                ps.setString(24, profile.getHobby());
                ps.setString(25, profile.getDescription());
                ps.setString(26, profile.getPhoto());
                ps.setInt(27,profile.getId());
                return ps;
            }
        });
    }

    public int updateProfile(final Profile profile) {
        return jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_PROFILE_SQL);
                ps.setInt(1, profile.getUser_id());
                ps.setString(2, profile.getName());
                ps.setString(3, profile.getGender());
                ps.setString(4, profile.getBirthday());
                ps.setString(5, profile.getProvince());
                ps.setString(6, profile.getNation());
                ps.setString(7, profile.getPolitics_status());
                ps.setString(8, profile.getIDnumber());
                ps.setString(9, profile.getType());
                ps.setString(10, profile.getSchool());
                ps.setString(11, profile.getGrade());
                ps.setString(12, profile.getAddress());
                ps.setString(13, profile.getPostcode());
                ps.setString(14, profile.getPhone());
                ps.setString(15, profile.getHeight());
                ps.setString(16, profile.getWeight());
                ps.setString(17, profile.getMail());
                ps.setString(18, profile.getFather_name());
                ps.setString(19, profile.getFather_job());
                ps.setString(20, profile.getFather_phone());
                ps.setString(21, profile.getMother_name());
                ps.setString(22, profile.getMother_job());
                ps.setString(23, profile.getMother_phone());
                ps.setString(24, profile.getHobby());
                ps.setString(25, profile.getDescription());
                ps.setString(26,profile.getPhoto());
                ps.setInt(27, profile.getId());
                return ps;
            }
        });
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
