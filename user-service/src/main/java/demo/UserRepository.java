package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE id=?", new Object[]{id}, new UserRowMapper());
        }catch (Exception exception) {
            throw new UserNotFoundException(id);
        }
    }

    @Transactional(readOnly = true)
    public List<User> findUserinPage(int page, int item_per_page){
        int firstid = (page-1) * item_per_page;
        return this.jdbcTemplate.query("SELECT id, firstname, lastname FROM USERS LIMIT  ? , ?", new Object[]{firstid, item_per_page}, new UserRowMapper());
    }

    @Transactional
    public void save(User user) {
        String sql = "INSERT INTO USERS(id, firstname, lastname) VALUES (?,?,?)";
        this.jdbcTemplate.update(sql, user.getId(), user.getFirstname(), user.getLastname());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM USERS WHERE id=?";
        this.jdbcTemplate.update(sql, id);
    }
    
    public List<User> findAllUser(){
        return this.jdbcTemplate.query("SELECT * FROM USERS;", new UserRowMapper());
    }   
}
