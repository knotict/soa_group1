package hathome.userservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean signup(User user, String password){
        String sql = "INSERT INTO user (email, password, address) values (?,?,?)";
        try {
            this.jdbcTemplate.update(sql, user.getEmail(), password, user.getAddress());
            return true;

        }catch (Exception e){
            throw new UserSignupException(e,  user.getEmail());
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        try {
            String sql = "SELECT email, address, id FROM USER WHERE id=?";
            return this.jdbcTemplate.queryForObject(sql,
                    new Object[]{userId}, new UserRowMapper());
        }catch (Exception exception) {
            throw new UserNotFoundException(userId, exception);
        }
    }
}
