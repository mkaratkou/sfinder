package job.stafffinder.service;

import job.stafffinder.model.User;

public interface UserService {

    boolean isExist(User user);

    User findById(Long i);

    void save(User user);
}
