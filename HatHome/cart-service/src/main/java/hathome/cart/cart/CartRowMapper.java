package hathome.cart.cart;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by Mark on 3/6/2017.
 */
public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Cart item = new Cart();
        item.setId(resultSet.getInt("id"));
        item.setUser_id(resultSet.getLong("user_id"));
        item.setProduct_id(resultSet.getLong("product_id"));
        item.setAmount(resultSet.getInt("amount"));
        item.setStatus(resultSet.getString("status"));
        return item;
    }
}
