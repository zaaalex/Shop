package com.zorkoalex.shop.database.users;

import com.zorkoalex.shop.dto.user.User;
import com.zorkoalex.shop.dto.user.Users;
import com.zorkoalex.shop.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository) {this.userRepository=userRepository;}

    @Override
    public void addUser(User user) throws UserExistException {
        if(userRepository.existsByNumber(user.getNumber())) {
            throw new UserExistException("User with NUMBER "+user.getNumber()+ " doesn't exist");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setNumber(user.getNumber());
        userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void deleteUser(String number) throws UserExistException {
        if(!userRepository.existsByNumber(number)) {
            throw new UserExistException("User with NUMBER "+number+ " doesn't exist");
        }
        else {
            userRepository.deleteById( userRepository.findUserEntityByNumber(number).getId());
        }
    }

    @Override
    public Users getUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<User> userList = userEntityList.stream().map(c -> {
            User user=new User();
            user.setNumber(c.getNumber());
            user.setName(c.getName());
            return user;
        }).collect(Collectors.toList());
        Users users = new Users();
        users.setUserList(userList);
        return users;
    }
}
