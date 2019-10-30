package prv.saevel.drools.playground.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.persistence.repositories.UserRepository;
import prv.saevel.drools.playground.services.converters.Converter;
import prv.saevel.drools.playground.services.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final Converter<UserModel, User> userConverter;
    private final UserRepository userRepository;

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

    public Collection<User> allUsers() {
        return userRepository.findAll().stream().map(userConverter::convert).collect(Collectors.toList());
    }
}
