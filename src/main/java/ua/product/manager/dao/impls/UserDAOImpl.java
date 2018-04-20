package ua.product.manager.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
        return null;
    }

    @Override
    public User getUseById(int userId) {
        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void removeUser(int userId) {

    }

    @Override
    public void blockUser(int userId) {

    }

    @Override
    public void unblockUser(int userId) {

    }

    @Override
    public void setUserGroup(int userId, int groupId) {

    }


    private static final class ShortUserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setTelNumber(rs.getString("tel_number"));
            user.setFullName(rs.getString("full_name"));

            return  user;
        }
    }
}
