package job.stafffinder.repository;

import job.stafffinder.model.User;
import job.stafffinder.repository.mapper.StringRowMapper;
import job.stafffinder.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String FIND_BY_ID_QUERY = "select * from T_USER u where u.id = ?";
    private static final String USER_EXISTS_QUERY = "select email from T_USER where email = ?";

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRowMapper stringRowMapper;

    @Override
    public boolean isExist(User user) {
        Object[] params = {user.getEmail()};
        List<String> emailAsList = template.query(USER_EXISTS_QUERY, params, stringRowMapper);
        return !CollectionUtils.isEmpty(emailAsList);
    }

    @Override
    public User findById(Long id) {
        List<User> users = template.query(FIND_BY_ID_QUERY, new Object[]{id}, userMapper);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    @Override
    public void save(User user) {
        Long userId = simpleJdbcInsert.executeAndReturnKey(toNamedParameters(user)).longValue();
        user.setId(userId);
    }

    private Map<String, Object> toNamedParameters(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("first_name", user.getFirstName());
        parameters.put("last_name", user.getLastName());
        parameters.put("password", user.getPassword());
        parameters.put("landline_phone_number", user.getLandlinePhoneNumber());
        parameters.put("mobile_phone_number", user.getMobilePhoneNumber());
        return parameters;
    }

}
