package ua.product.manager.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ua.product.manager.dao.interfaces.ProductDAO;
import ua.product.manager.models.Product;

import javax.sql.DataSource;

public class ProductDAOImpl implements ProductDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public void insertProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void removeProduct(int productId) {

    }
}
