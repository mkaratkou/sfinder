package job.stafffinder.repository;


import job.stafffinder.model.User;

public interface UserRepository {

    boolean isExist(User user);

    User findById(Long id);

    void save(User user);
}
