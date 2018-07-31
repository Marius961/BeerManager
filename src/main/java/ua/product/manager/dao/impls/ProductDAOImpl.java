package ua.product.manager.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.product.manager.dao.interfaces.ProductDAO;
import ua.product.manager.models.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        try {
            return jdbcTemplate.query(sql, new ProductMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", productId);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ProductMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertProduct(Product product) {
        String sql = "INSERT INTO products (name, description, active) VALUES (:name, :description, :active)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("description", product.getDescription());
        params.addValue("active", product.isActive());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name=:name, description=:description, active=:active WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("description", product.getDescription());
        params.addValue("id", product.getId());
        params.addValue("active", product.isActive());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeProduct(int productId) {
        String sql = "DELETE FROM products WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", productId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void blockProduct(int productId) {
        String sql = "UPDATE products SET active=0 WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", productId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void unblockProduct(int productId) {
        String sql = "UPDATE products SET active=1 WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", productId);
        jdbcTemplate.update(sql, params);
    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setActive(rs.getBoolean("active"));
            return product;
        }
    }
}
