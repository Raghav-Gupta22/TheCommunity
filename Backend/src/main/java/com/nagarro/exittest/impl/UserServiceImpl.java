package com.nagarro.exittest.impl;

import com.nagarro.exittest.models.User;
import com.nagarro.exittest.models.UserRole;
import com.nagarro.exittest.repository.RoleRepository;
import com.nagarro.exittest.repository.UserRepository;
import com.nagarro.exittest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.getUserByEmail(user.getEmail());

        if (local != null) {
            System.out.println("User exists");
            throw new Exception("User already present!");
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
            return local;
        }
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User showUser(String email) {
        return this.userRepository.getUserByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

}
