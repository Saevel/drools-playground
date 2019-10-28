package prv.saevel.drools.playground.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.persistence.repositories.UserRepository;
import prv.saevel.drools.playground.services.converters.Converter;
import prv.saevel.drools.playground.services.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private Converter<UserModel, User> userConverter;

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(long id){
        return userRepository.findById(id).map(userConverter::convert);
    }

    public User saveUser(User user) {
        return userConverter.convert(
                userRepository.save(
                        userConverter.reverseConvert(user)
                )
        );
    }
}
