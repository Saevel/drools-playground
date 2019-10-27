package prv.saevel.drools.playground.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.saevel.drools.playground.persistence.model.User;
import prv.saevel.drools.playground.persistence.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
