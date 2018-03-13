package ua.product.manager.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.product.manager.dao.interfaces.UserDAO;
import ua.product.manager.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDAOImpl implements UserDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private static final String userTable = "user";
    private static final String userRoleTable = "user_role";
    private static final String rolesTable = "roles";

    @Override
    public User checkUser(int tel_number, String password) {
        String sql = "SELECT * FROM user WHERE tel_number=:tel_number AND password=:password";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tel_number", tel_number);
        params.addValue("password", password);

        try {
            User user;
            user = jdbcTemplate.queryForObject(sql, params, new UserMapper());
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    @Transactional
    @Override
    public int insertUser(String fullName, String companyName, String companyAddress, String email, int telNumber, String password) {
        String sql = "INSERT INTO " + userTable + " (email, tel_number, password, full_name, company_name, company_addr) VALUES (:email, :tel_number, :password, :full_name, :company_name, :company_addr)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("full_name", fullName);
        params.addValue("company_name", companyName);
        params.addValue("company_addr", companyName);
        params.addValue("email", email);
        params.addValue("tel_number", telNumber);
        params.addValue("password", password);
        return jdbcTemplate.update(sql, params);
    }

    @Transactional
    @Override
    public void insertUser(User user) {
        insertUser(user.getFullName(),user.getCompanyName(), user.getCompanyAddress(), user.getEmail(), user.getTelNumber(), user.getPassword());
    }


    @Override
    public User getFullUserData(int userId) {
        return null;
    }


    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {


            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setTelNumber(rs.getInt("tel_number"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("full_name"));
            user.setCompanyName(rs.getString("company_name"));
            user.setCompanyAddress(rs.getString("company_addr"));

            return  user;
        }
    }

    private static final class ShortUserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setTelNumber(rs.getInt("tel_number"));
            return  user;
        }
    }
}
