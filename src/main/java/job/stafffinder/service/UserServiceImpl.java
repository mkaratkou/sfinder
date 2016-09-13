package job.stafffinder.service;

import job.stafffinder.model.User;
import job.stafffinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HashService hashService;

    @Override
    public boolean isExist(User user) {
        return userRepository.isExist(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(hashService.hashFor(user.getPassword()));
        userRepository.save(user);
    }
}
