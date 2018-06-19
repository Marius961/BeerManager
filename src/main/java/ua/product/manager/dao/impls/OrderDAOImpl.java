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

    @Override
    public List<OrderItem> getItemsByOrderId(int orderId) {
        String sql = "SELECT * FROM order_items WHERE order_id=:orderId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", orderId);
        try {
            return jdbcTemplate.query(sql, params, new OrderItemMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void addItemToOrder(OrderItem item) {
        String sql = "INSERT INTO order_items (order_id, product_id, volume)" +
                "VALUES (:orderId, :productId, :volume)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", item.getOrderId());
        params.addValue("productId", item.getProductId());
        params.addValue("volume", item.getVolume());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteItem(int itemId) {
        String sql = "DELETE FROM order_items WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", itemId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id=:id ORDER BY 2 DESC ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        try {
            return jdbcTemplate.query(sql, params, new OrderMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Order getOrder(int orderId) {
        String sql = "SELECT * FROM orders WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", orderId);
        try {
            return jdbcTemplate.queryForObject(sql, params, new OrderMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int createOrder(Order order) {
        String sql = "INSERT INTO orders " +
                "(creation_date, creation_time, exec_date, user_id, status_id, price, comment) VALUES " +
                "(CURRENT_DATE, CURRENT_TIME, :execDate, :userId, :statId, :price, :comment)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("execDate", order.getExecDate());
        params.addValue("userId", order.getUserId());
        params.addValue("statId", order.getStatusId());
        params.addValue("price", order.getPrice());
        params.addValue("comment", order.getComment());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public void updateOrder(Order order) {
        String sql =
                "UPDATE orders " +
                "SET " +
                "creation_date=:cDate, " +
                "exec_date=:execDate, " +
                "creation_time=:cTime, " +
                "user_id=:userId, " +
                "status_id=:statId," +
                "price=:price," +
                "comment=:comment" +
                "WHERE" +
                "id=:id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cDate", order.getCreationDate());
        params.addValue("execDate", order.getExecDate());
        params.addValue("cTime", order.getCreationTime());
        params.addValue("userId", order.getUserId());
        params.addValue("statId", order.getStatusId());
        params.addValue("price", order.getPrice());
        params.addValue("comment", order.getComment());
        params.addValue("id", order.getId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeOrder(int orderId) {
        String sql1 = "DELETE FROM order_items WHERE order_id=:id";
        String sql2 = "DELETE FROM orders WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", orderId);
        jdbcTemplate.update(sql1, params);
        jdbcTemplate.update(sql2, params);
    }


    private static final class OrderItemMapper implements RowMapper<OrderItem> {

        @Override
        public OrderItem mapRow(ResultSet rs, int i) throws SQLException {
            OrderItem item = new OrderItem();
            item.setId(rs.getInt("id"));
            item.setOrderId(rs.getInt("order_id"));
            item.setProductId(rs.getInt("product_id"));
            item.setVolume(rs.getDouble("volume"));
            return item;
        }
    }

    private static final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCreationDate(rs.getString("creation_date"));
            order.setCreationTime(rs.getString("creation_time"));
            order.setExecDate(rs.getString("exec_date"));
            order.setUserId(rs.getInt("user_id"));
            order.setStatusId(rs.getInt("status_id"));
            order.setPrice(rs.getDouble("price"));
            order.setComment(rs.getString("comment"));
            return order;
        }
    }
}
