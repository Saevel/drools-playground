package prv.saevel.drools.playground.services.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prv.saevel.drools.playground.persistence.model.AccountModel;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.services.model.Account;
import prv.saevel.drools.playground.services.model.User;

import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<UserModel, User> {

    @Autowired
    private Converter<AccountModel, Account> accountConverter;

    @Override
    public User convert(UserModel userModel) {
        User user = new User(userModel.getUsername(), userModel.getPassword());
        user.setId(userModel.getId());
        user.setAccounts(userModel.getAccounts().stream().map(accountConverter::convert).collect(Collectors.toList()));
        user.setAge(userModel.getAge());
        return user;
    }

    @Override
    public UserModel reverseConvert(User user) {
        UserModel userModel = new UserModel(user.getUsername(), user.getPassword());
        userModel.setAge(user.getAge());
        userModel.setId(user.getId());
        return userModel;
    }
}
