package za.ac.cput.service.impl;

/**
 * UserServerImpl.java
 *ServerImpl of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.IUserService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {

        return userRepository.save(user);
    }

    @Override
    public User read(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<User> getAll() {
        return new ArrayList<>(userRepository.findAll());
    }
}
