package ua.product.manager.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ua.product.manager.dao.interfaces.UserDAO;
import ua.product.manager.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        try {
            return jdbcTemplate.query(sql, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUseById(int userId) {
        String sql = "SELECT * FROM users WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        try {
            return jdbcTemplate.queryForObject(sql, params, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO users" +
                "(username, password, enabled, email, tel_number, full_name, company_name, company_addr) " +
                "VALUES " +
                "(:username, :password, 1, :email, :telNum, :fullName, :companyName, :companyAddr)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("password", user.getPassword());
        params.addValue("email", user.getEmail());
        params.addValue("telNum", user.getTelNumber());
        params.addValue("fullName", user.getFullName());
        params.addValue("companyName", user.getCompanyName());
        params.addValue("companyAddr", user.getCompanyAddress());
        jdbcTemplate.update(sql, params);
        addUserToGroup(user.getUsername(), 1);
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET " +
                "username=:username, password=:password, enabled=:enabled, email=:email, " +
                "tel_number=:telNum, full_name=:fullName, company_name=:companyName, " +
                "company_addr=:companyAddr " +
                "WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("password", user.getPassword());
        params.addValue("enabled", user.getEnabled());
        params.addValue("email", user.getEmail());
        params.addValue("telNum", user.getTelNumber());
        params.addValue("fullName", user.getFullName());
        params.addValue("companyName", user.getCompanyName());
        params.addValue("companyAddr", user.getCompanyAddress());
        params.addValue("id", user.getId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeUser(int userId) {
        String sql = "DELETE FROM users WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void blockUser(int userId) {
        String sql = "UPDATE users SET enabled=0 WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void unblockUser(int userId) {
        String sql = "UPDATE users SET enabled=1 WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateUserGroup(String username, int groupId) {
        String sql = "UPDATE group_members SET group_id=:groupId WHERE username=:username";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("groupId", groupId);
        params.addValue("username", username);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void addUserToGroup(String username, int groupId) {
        String sql = "INSERT INTO group_members (username, group_id) VALUES (:username, :groupId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        params.addValue("groupId", groupId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeUserFromGroup(String username) {
        String sql = "DELETE FROM group_members WHERE username=:username";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        jdbcTemplate.update(sql, params);
    }


    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEnabled(rs.getInt("enabled"));
            user.setEmail(rs.getString("email"));
            user.setTelNumber(rs.getString("tel_number"));
            user.setFullName(rs.getString("full_name"));
            user.setCompanyName(rs.getString("company_name"));
            user.setCompanyAddress(rs.getString("company_addr"));
            return  user;
        }
    }
}
