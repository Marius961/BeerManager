package ua.product.manager.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.product.manager.dao.interfaces.OrderDAO;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private static final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {
            Order order = new Order();
//            order.setId(rs.getInt("id"));
//            order.setCreationDate(rs.getString("creation_date"));
//            order.setCreationTime(rs.getString("creation_time"));
//            order.setExecDate(rs.getString("exec_date"));
//            order.setUserId(rs.getInt("user_id"));
//            order.setStatusId(rs.getInt("status_id"));
//            order.setPrice(rs.getDouble("price"));
//            order.setComment(rs.getString("comment"));
            return order;
        }
    }
}
