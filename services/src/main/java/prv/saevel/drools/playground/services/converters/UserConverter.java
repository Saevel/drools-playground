package prv.saevel.drools.playground.services.converters;

import org.springframework.stereotype.Component;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.services.model.User;

import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<UserModel, User> {

    @Override
    public User convert(UserModel userModel) {
        User user = new User(userModel.getUsername(), userModel.getPassword(), userModel.getAge(), userModel.getCountry());
        user.setId(userModel.getId());
        user.setAccounts(userModel.getAccounts().stream().map(accountModel -> new AccountConverter(userModel).convert(accountModel)).collect(Collectors.toList()));
        return user;
    }

    @Override
    public UserModel reverseConvert(User user) {
        UserModel userModel = new UserModel(user.getUsername(), user.getPassword(), user.getAge(), user.getCountry());
        userModel.setId(user.getId());
        userModel.setAccounts(user.getAccounts().stream().map(account -> new AccountConverter(userModel).reverseConvert(account)).collect(Collectors.toList()));
        return userModel;
    }
}
