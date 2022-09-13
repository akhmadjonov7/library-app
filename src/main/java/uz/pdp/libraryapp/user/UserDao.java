package uz.pdp.libraryapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    public final JdbcTemplate jdbcTemplate;
    public void create(User user) {
        String sql = "insert into users (full_name,phone_number) values(?,?);";
        jdbcTemplate.update(sql, user.getFullName(), user.getPhoneNumber());
    }

    public List<UserDto> read(){
        String sql = "select id, full_name, phone_number from users";
         return jdbcTemplate.query(sql,(rs, rowNum) ->
             UserDto.builder()
                     .id(rs.getInt(1))
                     .fullName(rs.getString(2))
                     .phoneNumber(rs.getString(3))
                     .build()
         );
    }
    public boolean update(User user) {
        String sql = "update users set full_name = ?, phone_number = ?, is_admin = ? where id = ?;";
        try {
            jdbcTemplate.update(sql, user.getFullName(), user.getPhoneNumber(), user.getIsAdmin(),user.getId());
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    public int delete(int id) {
        String sql = "delete from users where id = ?;";
        try {
            jdbcTemplate.update(sql, id);
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    public User readById(int id) {
        String sql = "select * from users where id = " + id + ";";
        return jdbcTemplate.query(sql, rs -> {
            rs.next();
            return User.builder()
                    .id(rs.getInt(1))
                    .fullName(rs.getString(2))
                    .phoneNumber(rs.getString(3))
                    .isAdmin(rs.getBoolean(4))
                    .password(rs.getString(5))
                    .build();
        });
    }

    public boolean exist(String phoneNumber) {
        String sql = "select id from users where phone_number = '"+ phoneNumber + "';";
        try {
            jdbcTemplate.query(sql,rs -> {rs.next();return rs.getInt(1);});
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }
}
